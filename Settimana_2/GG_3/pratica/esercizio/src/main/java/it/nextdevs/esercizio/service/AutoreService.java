package it.nextdevs.esercizio.service;

import com.cloudinary.Cloudinary;
import it.nextdevs.esercizio.DTO.AutoreDTO;
import it.nextdevs.esercizio.exception.AutoreNonTrovatoException;
import it.nextdevs.esercizio.model.Autore;
import it.nextdevs.esercizio.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    //****************************************************************

    public Optional<Autore> getAutoreById(int id) {
        return autoreRepository.findById(id);
    }

    public String saveAutori(AutoreDTO autoreDTO) {
        Autore autore = new Autore();
        autore.setNome(autoreDTO.getNome());
        autore.setCognome(autoreDTO.getCognome());
        autore.setEmail(autoreDTO.getEmail());
        autore.setDataDiNascita(autoreDTO.getDataNascita());
        sendMail(autore.getEmail());
        autore.setAvatar("https://ui-avatars.com/api/?name="+autore.getNome()+"+"+autore.getCognome());
        autoreRepository.save(autore);
        return "Autore creato con l'id " + autore.getId();
    }

    public Page<Autore> getAllAutore(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return autoreRepository.findAll(pageable);
    }

    public Autore updateAutore(int id, AutoreDTO autoreDTO) throws AutoreNonTrovatoException {
        Optional<Autore> autoreOptional = getAutoreById(id);
        if (autoreOptional.isPresent()) {
            Autore autore = autoreOptional.get();
            autore.setNome(autoreDTO.getNome());
            autore.setCognome(autoreDTO.getCognome());
            autore.setEmail(autoreDTO.getEmail());
            autore.setDataDiNascita(autoreDTO.getDataNascita());
            return autoreRepository.save(autore);
        }else {
            throw new AutoreNonTrovatoException("Autore inesistente");
        }
    }

    public String deleteAutore(int id) throws AutoreNonTrovatoException {
        Optional<Autore> autoreOptional = getAutoreById(id);
        if (autoreOptional.isPresent()) {
            autoreRepository.delete(autoreOptional.get());
            return "Autore con l'id numero " + id +" eliminato con successo";
        }else {
            throw new AutoreNonTrovatoException("Autore con l'id numero " + id +" non trovato");
        }
    }

    public String patchAvatarAutore(int id, MultipartFile foto) throws IOException {
        Optional<Autore> autoreOptional = getAutoreById(id);

        if ( autoreOptional.isPresent() ) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Autore autore = autoreOptional.get();
            autore.setAvatar(url);
            autoreRepository.save(autore);

            return "Autore con id " + id + " aggiornata con successo con la foto inviata";
        }else{
            throw new AutoreNonTrovatoException("Autore con id: " + id + " non trovato");
        }
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}

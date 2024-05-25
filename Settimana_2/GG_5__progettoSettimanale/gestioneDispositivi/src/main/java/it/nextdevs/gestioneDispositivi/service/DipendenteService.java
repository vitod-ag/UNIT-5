package it.nextdevs.gestioneDispositivi.service;

import com.cloudinary.Cloudinary;
import it.nextdevs.gestioneDispositivi.DTO.DipendenteDTO;
import it.nextdevs.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.nextdevs.gestioneDispositivi.model.Dipendente;
import it.nextdevs.gestioneDispositivi.repository.DipendenteRepository;
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
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;  // per la foto

    @Autowired
    private JavaMailSenderImpl javaMailSender; // per la mail

    public Optional<Dipendente> getDipendentiById(int id) {
        return dipendenteRepository.findById(id);
    }

    public String saveDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        sendMail(dipendente.getEmail());
        dipendenteRepository.save(dipendente);
        return "dipendente creato con l'id " + dipendente.getId();
    }

    public Page<Dipendente> getAllDipendente(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente updateDipendente(int id, DipendenteDTO dipendenteDTO) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = getDipendentiById(id);
        if (dipendenteOptional.isPresent()) {
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setNome(dipendenteDTO.getNome());
            dipendente.setCognome(dipendenteDTO.getCognome());
            dipendente.setEmail(dipendenteDTO.getEmail());
            return dipendenteRepository.save(dipendente);
        }else {
            throw new DipendenteNonTrovatoException("Dipendente inesistente");
        }
    }

    public String deleteDipendente(int id) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = getDipendentiById(id);
        if (dipendenteOptional.isPresent()) {
            dipendenteRepository.delete(dipendenteOptional.get());
            return "Dipendente con l'id numero " + id +" eliminato con successo";
        }else {
            throw new DipendenteNonTrovatoException("Dipendente con l'id numero " + id +" non trovato");
        }
    }


    public String patchFotoDipendente(int id, MultipartFile foto) throws IOException {
        Optional<Dipendente> dipendenteOptional = getDipendentiById(id);

        if ( dipendenteOptional.isPresent() ) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setFoto(url);
            dipendenteRepository.save(dipendente);

            return "Dipendente con id " + id + " aggiornata con successo con la foto inviata";
        }else{
            throw new DipendenteNonTrovatoException("dipendente con id: " + id + " non trovato");
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

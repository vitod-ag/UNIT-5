package it.epicode.teoria.service;

import com.cloudinary.Cloudinary;
import it.epicode.teoria.DTO.StudenteDTO;
import it.epicode.teoria.exception.AulaNonTrovataException;
import it.epicode.teoria.exception.StudenteNonTrovatoException;
import it.epicode.teoria.model.Aula;
import it.epicode.teoria.model.Studente;
import it.epicode.teoria.repository.Aularepository;
import it.epicode.teoria.repository.StudenteRepository;
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
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private Aularepository aularepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public String saveStudente(StudenteDTO studenteDTO) {

        Studente studente = new Studente();
        studente.setNome(studenteDTO.getNome());
        studente.setCognome(studenteDTO.getCognome());
        studente.setDatanascita(studenteDTO.getDataNascita());
        studente.setEmail(studenteDTO.getEmail());

        Optional<Aula> aulaOptional = aularepository.findById(studenteDTO.getAulaId());

        if (aulaOptional.isPresent()) {
            Aula aula = aulaOptional.get();
            studente.setAula(aula);
            studenteRepository.save(studente);
            sendMail(studente.getEmail());
            return "Studente con matricola " + studente.getMatricola() + " salvato correttamente";
        } else {
            throw new AulaNonTrovataException("Aula con id " + studenteDTO.getAulaId() + " non trovata");
        }
    }

    public Page<Studente> getStudenti(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return studenteRepository.findAll(pageable);
    }

    public Optional<Studente> getStudenteByMatricola(int matricola) {
        return studenteRepository.findById(matricola);
    }

    public Studente updateStudente(int matricola, StudenteDTO studenteDTO) {
        Optional<Studente> studenteOptional = getStudenteByMatricola(matricola);

        if (studenteOptional.isPresent()) {
            Studente studente = studenteOptional.get();

            studente.setNome(studenteDTO.getNome());
            studente.setCognome(studenteDTO.getCognome());
            studente.setDatanascita(studenteDTO.getDataNascita());
            studente.setEmail(studenteDTO.getEmail());

            Optional<Aula> aulaOptional = aularepository.findById(studenteDTO.getAulaId());

            if (aulaOptional.isPresent()) {
                Aula aula = aulaOptional.get();
                studente.setAula(aula);
                studenteRepository.save(studente);
                return studente;
            }else{
                throw new AulaNonTrovataException("Aula con id " + studenteDTO.getAulaId() + " non trovata");
            }
        }else{
            throw new StudenteNonTrovatoException("Studente con matricola: " + matricola + " non trovato");
        }
    }

    public String deleteStudente(int matricola) {
        Optional<Studente> studenteOptional = studenteRepository.findById(matricola);
        if ( studenteOptional.isPresent() ) {
            studenteRepository.delete(studenteOptional.get());
            return "Studente con id " + matricola + " cancellato con successo";
        }else{
            throw new StudenteNonTrovatoException("Studente con matricola: " + matricola + " non trovato");
        }
    }

    public String patchFotoStudente(int matricola, MultipartFile foto) throws IOException {
        Optional<Studente> studenteOptional = getStudenteByMatricola(matricola);

        if ( studenteOptional.isPresent() ) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Studente studente = studenteOptional.get();
            studente.setFoto(url);
            studenteRepository.save(studente);

            return "Studente con matricola " + matricola + " aggiornata con successo con la foto inviata";
        }else{
            throw new StudenteNonTrovatoException("Studente con matricola: " + matricola + " non trovato");
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

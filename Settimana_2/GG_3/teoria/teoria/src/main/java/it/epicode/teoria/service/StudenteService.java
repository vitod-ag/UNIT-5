package it.epicode.teoria.service;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private Aularepository aularepository;

    public String saveStudente(StudenteDTO studenteDTO) {

        Studente studente = new Studente();
        studente.setNome(studenteDTO.getNome());
        studente.setCognome(studenteDTO.getCognome());
        studente.setDatanascita(studenteDTO.getDataNascita());

        Optional<Aula> aulaOptional = aularepository.findById(studenteDTO.getAulaId());

        if (aulaOptional.isPresent()) {
            Aula aula = aulaOptional.get();
            studente.setAula(aula);
            studenteRepository.save(studente);
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
}

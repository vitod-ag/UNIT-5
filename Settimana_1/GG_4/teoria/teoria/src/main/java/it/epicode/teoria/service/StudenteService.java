package it.epicode.teoria.service;

import it.epicode.teoria.bean.Studente;
import it.epicode.teoria.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteService {
    @Autowired
    private StudenteRepository studenteRepository;

    public void inserisciStudente(Studente studente) {
        studenteRepository.save(studente);
    }

    public Studente getStudente(int matricola) {
        return studenteRepository.findById(matricola).get();
    }

    public List<Studente> getStudenti() {
        return studenteRepository.findAll();
    }

    public void cancellaStudente(int matricola){
        studenteRepository.deleteById(matricola);
    }

    //metodi che richiamano la repository per la query

    public List<Studente> getStudentiByNome(String nome) {
        return studenteRepository.findByNome(nome);
    }

    public List<Studente> getStudentiByPartialNome(String nome) {
        return studenteRepository.findByNomeLike(nome);
    }


}

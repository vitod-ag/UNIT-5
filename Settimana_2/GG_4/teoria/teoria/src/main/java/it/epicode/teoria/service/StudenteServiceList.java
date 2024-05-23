package it.epicode.teoria.service;

import it.epicode.teoria.exception.StudenteNonTrovatoException;
import it.epicode.teoria.model.Studente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteServiceList {
    private List<Studente> studenti = new ArrayList<>();

    public Optional<Studente> getStudenteByMatricola(int matricola) {
        return studenti.stream().filter(studente -> studente.getMatricola() == matricola).findFirst();  //tra tutti gli studenti della lista prendo quello con la matricola uguale
    }

    public List<Studente> getAllStudenti() {
        return studenti;
    }

    public String saveStudenti(Studente studente) {
        studenti.add(studente);
        return "Studente creato con questa matricola: " + studente.getMatricola();
    }

    public Studente updateStudente(int matricola, Studente studenteUpdate) throws StudenteNonTrovatoException {
        Optional<Studente> studenteOpt = getStudenteByMatricola(matricola);  // ci restituisce un Optional
        if (studenteOpt.isPresent()) {
            Studente studente = studenteOpt.get();
            studente.setNome(studenteUpdate.getNome());
            studente.setCognome(studenteUpdate.getCognome());
            studente.setDatanascita(studenteUpdate.getDatanascita());
            return studente;
        } else {
            throw new StudenteNonTrovatoException("Studente non trovato");
        }
    }

    public String deleteStudente(int matricola) throws StudenteNonTrovatoException{
        Optional<Studente> studenteOpt = getStudenteByMatricola(matricola);
        if (studenteOpt.isPresent()) {
            studenti.remove(studenteOpt.get());
            return "Studente cancellato";
        }else {
            throw new StudenteNonTrovatoException("Studente non trovato");
        }
    }
}

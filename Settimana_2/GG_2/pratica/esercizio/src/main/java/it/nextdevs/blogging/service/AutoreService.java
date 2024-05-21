package it.nextdevs.blogging.service;

import it.nextdevs.blogging.exception.AutoreNonTrovatoException;
import it.nextdevs.blogging.model.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoreService {
    private List<Autore> autori = new ArrayList<>();

    public Optional<Autore> getAutoreById(int id) {
        return autori.stream().filter(autore -> autore.getId() == id).findFirst();
    }

    public List<Autore> getAllAutore() {
        return autori;
    }

    public String saveAutori(Autore autore) {
        autori.add(autore);
        return "Autore creato con l'id " + autore.getId();
    }

    public Autore updateAutore(int id, Autore autoreUpdate) throws AutoreNonTrovatoException {
        Optional<Autore> autoreOptional = getAutoreById(id);
        if (autoreOptional.isPresent()) {
            Autore autore = autoreOptional.get();
            autore.setNome(autoreUpdate.getNome());
            autore.setCognome(autoreUpdate.getCognome());
            autore.setEmail(autoreUpdate.getEmail());
            autore.setDataDiNascita(autoreUpdate.getDataDiNascita());
            return autore;
        }else {
            throw new AutoreNonTrovatoException("Autore inesistente");
        }
    }

    public String deleteAutore(int id) throws AutoreNonTrovatoException {
        Optional<Autore> autoreOptional = getAutoreById(id);
        if (autoreOptional.isPresent()) {
            autori.remove(autoreOptional.get());
            return "Autore eliminato con successo";
        }else {
            throw new AutoreNonTrovatoException("Autore non trovato");
        }
    }
}

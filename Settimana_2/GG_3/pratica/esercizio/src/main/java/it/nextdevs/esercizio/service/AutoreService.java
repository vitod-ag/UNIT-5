package it.nextdevs.esercizio.service;

import it.nextdevs.esercizio.DTO.AutoreDTO;
import it.nextdevs.esercizio.exception.AutoreNonTrovatoException;
import it.nextdevs.esercizio.model.Autore;
import it.nextdevs.esercizio.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;

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
}

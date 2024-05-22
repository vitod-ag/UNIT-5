package it.nextdevs.esercizio.controller;


import it.nextdevs.esercizio.DTO.AutoreDTO;
import it.nextdevs.esercizio.exception.AutoreNonTrovatoException;
import it.nextdevs.esercizio.model.Autore;
import it.nextdevs.esercizio.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @PostMapping("/api/autori")
    public String saveAutore(@RequestBody AutoreDTO autoreDTO) {
        return autoreService.saveAutori(autoreDTO);
    }

    @GetMapping("/api/autori")
    public Page<Autore> getAllAutori(@RequestParam (defaultValue = "0")int page,
                                     @RequestParam(defaultValue = "15")int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return autoreService.getAllAutore(page, size, sortBy);
    }

    @GetMapping("/api/autori/{id}")
    public Autore getAutoreById(@PathVariable int id) throws AutoreNonTrovatoException {
        Optional<Autore> autoreOptional = autoreService.getAutoreById(id);
        if (autoreOptional.isPresent()) {
            return autoreOptional.get();
        }else {
            throw new AutoreNonTrovatoException("Autore con questo id " + id + " non trovato");
        }
    }

    @PutMapping("/api/autori/{id}")
    public Autore updateAutore(@PathVariable int id,@RequestBody AutoreDTO autoreDTO) throws AutoreNonTrovatoException {
        return autoreService.updateAutore(id,autoreDTO);
    }

    @DeleteMapping("/api/autori/{id}")
    public String deleteAutore(@PathVariable int id) throws AutoreNonTrovatoException {
        return autoreService.deleteAutore(id);
    }
}

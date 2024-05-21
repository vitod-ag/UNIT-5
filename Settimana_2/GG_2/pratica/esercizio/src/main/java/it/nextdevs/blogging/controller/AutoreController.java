package it.nextdevs.blogging.controller;


import it.nextdevs.blogging.exception.AutoreNonTrovatoException;
import it.nextdevs.blogging.model.Autore;
import it.nextdevs.blogging.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @PostMapping("/api/autori")
    public String saveAutore(@RequestBody Autore autore) {
        return autoreService.saveAutori(autore);
    }

    @GetMapping("/api/autori")
    public List<Autore> getAllStudenti() {
        return autoreService.getAllAutore();
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
    public Autore updateAutore(@PathVariable int id,@RequestBody Autore autore) throws AutoreNonTrovatoException {
        return autoreService.updateAutore(id,autore);
    }

    @DeleteMapping("/api/autori/{id}")
    public String deleteAutore(@PathVariable int id) throws AutoreNonTrovatoException {
        return autoreService.deleteAutore(id);
    }
}

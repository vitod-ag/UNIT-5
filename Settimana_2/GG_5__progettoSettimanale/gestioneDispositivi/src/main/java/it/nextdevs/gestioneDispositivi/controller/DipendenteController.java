package it.nextdevs.gestioneDispositivi.controller;

import it.nextdevs.gestioneDispositivi.DTO.DipendenteDTO;
import it.nextdevs.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.nextdevs.gestioneDispositivi.exception.BadRequestException;
import it.nextdevs.gestioneDispositivi.model.Dipendente;
import it.nextdevs.gestioneDispositivi.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/api/dipendenti")
    public String saveDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return dipendenteService.saveDipendente(dipendenteDTO);
    }

    @GetMapping("/api/dipendenti")
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0")int page,
                                         @RequestParam(defaultValue = "15")int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getAllDipendente(page, size, sortBy);
    }

    @GetMapping("/api/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendentiById(id);
        if (dipendenteOptional.isPresent()) {
            return dipendenteOptional.get();
        }else {
            throw new DipendenteNonTrovatoException("Dipendente con questo id " + id + " non trovato");
        }
    }

    @PutMapping("/api/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult bindingResult) throws DipendenteNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return dipendenteService.updateDipendente(id,dipendenteDTO);
    }

    @DeleteMapping("/api/dipendenti/{id}")
    public String deleteDipendente(@PathVariable int id) throws DipendenteNonTrovatoException {
        return dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/api/dipendenti/{id}")
    public String patchFotoDipendente(@RequestBody MultipartFile foto, @PathVariable int id) throws IOException {
        return dipendenteService.patchFotoDipendente(id, foto);
    }
}

package it.nextdevs.GestioneDispositivi.controller;

import it.nextdevs.GestioneDispositivi.DTO.DispositivoDTO;
import it.nextdevs.GestioneDispositivi.exception.BadRequestException;
import it.nextdevs.GestioneDispositivi.exception.DispositivoNonTrovatoException;
import it.nextdevs.GestioneDispositivi.model.Dipendente;
import it.nextdevs.GestioneDispositivi.model.Dispositivo;
import it.nextdevs.GestioneDispositivi.service.DipendenteService;
import it.nextdevs.GestioneDispositivi.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/api/dispositivi")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String saveDispositivo(@RequestBody @Validated DispositivoDTO dispositivoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return dispositivoService.saveDispositivo(dispositivoDTO);
    }

    @GetMapping("/api/dispositivi")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    private Page<Dispositivo> getAllDispositivo(@RequestParam(defaultValue = "0")int page,
                                                @RequestParam(defaultValue = "15")int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return dispositivoService.getAllDispositivo(page, size, sortBy);
    }

    @GetMapping("/api/dispositivi/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Dispositivo getDispositivoById(@PathVariable int id) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = dispositivoService.getDispositivoById(id);
        if (dispositivoOptional.isPresent()){
            return dispositivoOptional.get();
        }else {
            throw new DispositivoNonTrovatoException("Dispositivo con l'id " + id + " non trovato");
        }
    }

    @PutMapping("/api/dispositivi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dispositivo updateDispositivo(@PathVariable int id,@RequestBody @Validated DispositivoDTO dispositivoDTO, BindingResult bindingResult) throws DispositivoNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return dispositivoService.updateDispositivo(id, dispositivoDTO);
    }

    @DeleteMapping("/api/dispositivi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteDispositivo(@PathVariable int id) throws DispositivoNonTrovatoException {
        return dispositivoService.deleteDispositivo(id);
    }

    @PostMapping("/api/dispositivi/{dispositivoId}/assegna/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Dispositivo> assegnaIlDispositivoAlDipendente(@PathVariable int dispositivoId, @PathVariable int dipendenteId) {
        Optional<Dipendente> dipendenteOpt = dipendenteService.getDipendentiById(dipendenteId);
        if (dipendenteOpt.isPresent()) {
            Dipendente dipendente = dipendenteOpt.get();
            try {
                Dispositivo dispositivo = dispositivoService.assegnaDispositivoAlDipendente(dispositivoId, dipendente);
                return new ResponseEntity<>(dispositivo, HttpStatus.OK);
            } catch (DispositivoNonTrovatoException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


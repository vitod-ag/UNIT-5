package it.nextdevs.GestioneDispositivi.controller;


import it.nextdevs.GestioneDispositivi.DTO.DipendenteDTO;
import it.nextdevs.GestioneDispositivi.DTO.DipendenteLoginDTO;
import it.nextdevs.GestioneDispositivi.exception.BadRequestException;
import it.nextdevs.GestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.nextdevs.GestioneDispositivi.service.AuthService;
import it.nextdevs.GestioneDispositivi.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;


    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String salvaDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new it.nextdevs.GestioneDispositivi.exception.BadRequestException(bindingResult.getAllErrors().
                    stream().map(ObjectError::getObjectName).reduce("", ((s, s2) -> s + s2)));
        }
        return dipendenteService.saveDipendente(dipendenteDTO);
    }


    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated DipendenteLoginDTO dipendenteLoginDTO, BindingResult bindingResult) throws DipendenteNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(ObjectError::getObjectName).reduce("", (s, s2) -> s + s2));
        }
        return authService.authenticateDipendenteAndCreateToken(dipendenteLoginDTO);
    }
}


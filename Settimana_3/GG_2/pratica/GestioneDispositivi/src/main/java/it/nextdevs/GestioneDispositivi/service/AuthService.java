package it.nextdevs.GestioneDispositivi.service;

import it.nextdevs.GestioneDispositivi.DTO.DipendenteLoginDTO;
import it.nextdevs.GestioneDispositivi.exception.UnauthorizedException;
import it.nextdevs.GestioneDispositivi.model.Dipendente;
import it.nextdevs.GestioneDispositivi.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public String authenticateUserAndCreateToken(DipendenteLoginDTO dipendenteLoginDto) {
//        Optional<Dipendente> dipendenteOptional = Optional.ofNullable(dipendenteService.getDipendenteByEmail(dipendenteLoginDto.getEmail()));
//
//        if (dipendenteOptional.isEmpty()) {
//            throw new UnauthorizedException("Error in authorization, relogin!");
//        } else {
//            Dipendente dipendente = dipendenteOptional.get();
//            if (passwordEncoder.matches(dipendenteLoginDto.getPassword(), dipendente.getPassword())) {  // cambio qui la password
//                return jwtTool.createToken(dipendente);
//            } else {
//                throw new UnauthorizedException("Error in authorization, relogin!");
//            }
//        }
//    }

    public String authenticateDipendenteAndCreateToken(DipendenteLoginDTO dipendenteLoginDTO) {
        Optional <Dipendente> dipendenteOptional = dipendenteService.getDipendenteByEmail(dipendenteLoginDTO.getEmail());
        if (dipendenteOptional.isEmpty()) {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }else{
            Dipendente dipendente= dipendenteOptional.get();
            if (passwordEncoder.matches(dipendenteLoginDTO.getPassword(),dipendente.getPassword())){
                return jwtTool.createToken(dipendente);
            }else {
                throw new UnauthorizedException("Error in authorization, relogin!");
            }
        }
    }
}


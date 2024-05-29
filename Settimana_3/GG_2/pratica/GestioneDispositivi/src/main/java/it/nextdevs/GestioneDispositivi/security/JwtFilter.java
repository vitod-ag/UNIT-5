package it.nextdevs.GestioneDispositivi.security;

import it.nextdevs.GestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.nextdevs.GestioneDispositivi.exception.UnauthorizedException;
import it.nextdevs.GestioneDispositivi.model.Dipendente;
import it.nextdevs.GestioneDispositivi.service.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtTool jwtTool;
    @Autowired
    private DipendenteService dipendenteService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader= request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new DipendenteNonTrovatoException("Error in authorization,token missing!");
        }
        String token = authHeader.substring(7);

        jwtTool.verifyToken(token);

        int dipendenteId= jwtTool.getIdFromToken(token);

        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendentiById(dipendenteId);

        if (dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            Authentication authentication= new UsernamePasswordAuthenticationToken(dipendente,null, dipendente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            throw new UnauthorizedException("Dipendente not found! Re-login please");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}

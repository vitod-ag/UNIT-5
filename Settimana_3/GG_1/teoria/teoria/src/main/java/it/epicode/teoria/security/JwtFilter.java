package it.epicode.teoria.security;

import it.epicode.teoria.entity.User;
import it.epicode.teoria.exception.UnauthorizedException;
import it.epicode.teoria.exception.UserNotFoundException;
import it.epicode.teoria.service.UserService;
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
    private JwtTool jwtTool;

    @Autowired
    private UserService userService;

    @Override //metodo per verificare che nella richiesta ci sia il token, altrimenti non si è autorizzati
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw  new UnauthorizedException("Error in authorization, token missing!");
        }

        String token = authHeader.substring(7);

        jwtTool.verifyToken(token);

        //decodifichiamo il token
        int userId = jwtTool.getIdFromToken(token);

        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            throw new UserNotFoundException("Error in authorization, user with id " + userId + " not found!");
        }
    }

    @Override //permette di non effettuare l'autenticazione per usare i servizi di autenticazione
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}

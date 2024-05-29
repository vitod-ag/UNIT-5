package it.epicode.teoria.exception;

import it.epicode.teoria.entity.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> DipendenteNonTrovatoHandler(UserNotFoundException e) {
        Error error = new Error();
        error.setMessaggio(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

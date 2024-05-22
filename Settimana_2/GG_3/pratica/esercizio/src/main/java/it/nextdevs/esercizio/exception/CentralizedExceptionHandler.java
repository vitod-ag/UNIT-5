package it.nextdevs.esercizio.exception;

import it.nextdevs.esercizio.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BlogNonTrovatoException.class)
    public ResponseEntity<Object> StudenteNonTronatohandler(BlogNonTrovatoException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDate.now());
        error.setHttpErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

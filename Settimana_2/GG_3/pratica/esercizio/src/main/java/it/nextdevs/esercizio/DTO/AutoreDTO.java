package it.nextdevs.esercizio.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDTO {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
}

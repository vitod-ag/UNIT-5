package it.epicode.teoria.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDTO {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private int aulaId;
}

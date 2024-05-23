package it.epicode.teoria.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDTO {

    @NotNull
    @Size(max = 30)
    private String nome;
    @NotNull
    @Size(max = 30)
    private String cognome;
    private LocalDate dataNascita;
    @Email()
    @NotNull
    private String email;
    @NotNull
    private int aulaId;

}

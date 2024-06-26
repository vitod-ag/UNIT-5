package it.nextdevs.esercizio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDTO {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(max = 50)
    private String nome;
    @NotNull(message = "Il cognome è obbligatorio")
    @Size(max = 50)
    private String cognome;
    @Email
    @NotNull
    private String email;
    private LocalDate dataNascita;
}

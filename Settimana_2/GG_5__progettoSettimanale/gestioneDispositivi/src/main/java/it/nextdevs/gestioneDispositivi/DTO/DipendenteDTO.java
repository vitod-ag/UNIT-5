package it.nextdevs.gestioneDispositivi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DipendenteDTO {
    @NotNull(message = "L'username è obbligatorio")
    @Size(max = 30)
    private String username;
    @NotNull(message = "Il nome è obbligatorio")
    @Size(max = 30)
    private String nome;
    @NotNull(message = "Il cognome è obbligatorio")
    @Size(max = 30)
    private String cognome;
    @Email
    @NotNull
    private String email;
}

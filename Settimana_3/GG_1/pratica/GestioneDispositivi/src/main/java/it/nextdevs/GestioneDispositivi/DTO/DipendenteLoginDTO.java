package it.nextdevs.GestioneDispositivi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DipendenteLoginDTO {
    @Email(message = "L'email non può essere vuota o mancante o composta da soli spazi")
    @NotBlank
    private String email;

    @NotBlank(message = "La password non può essere vuota o mancante o composta da soli spazi")
    private String password;
}

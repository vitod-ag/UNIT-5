package it.epicode.teoria.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String surname;
    @Email
    @NotBlank(message = "Email non può essere vuota, mancante o composta da soli spazi")
    private String email;
    @NotBlank(message = "Password non può essere vuota, mancante o composta da soli spazi")
    private String password;
}

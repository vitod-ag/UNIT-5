package it.epicode.teoria.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    @NotBlank(message = "Name can't be empty or null or only spaces")
    private String name;
    @NotBlank(message = "Surname can't be empty or null or only spaces")
    private String surname;
    @NotNull(message = "BirthDate can't be empty or null or only spaces")
    private LocalDate birthDate;
}

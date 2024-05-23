package it.nextdevs.esercizio.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class BlogDTO {
    @NotNull(message = "Il titolo è obbligatorio")
    @Size(max = 100)
    private String titolo;
    @NotNull(message = "Il contenuto è obbligatorio")
    @Size(max = 100)
    private String contenuto;
    @NotNull(message = "La categoria è obbligatoria")
    @Size(max = 100)
    private String categoria;
    @Max(10)
    @Min(0)
    private int tempoDiLettura;

    @NotNull(message = "L'autore non può essere nullo")
    private int autoreId;
}

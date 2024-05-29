package it.nextdevs.GestioneDispositivi.DTO;

import it.nextdevs.GestioneDispositivi.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoDTO {
    @NotNull(message = "Il nome/modello è obbligatorio")
    private String nomeModello;
    @NotNull(message = "La marca è obbligatoria")
    private String marca;
    @NotNull(message = "Lo stato è obbligatorio")
    private Status stato;
    @NotNull
    private String tipoDispositivo;

    private int dipendenteId;
}

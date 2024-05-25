package it.nextdevs.gestioneDispositivi.DTO;

import it.nextdevs.gestioneDispositivi.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoDTO {
    @NotBlank(message = "Il nome/modello è obbligatorio")
    private String nomeModello;
    @NotBlank(message = "La marca è obbligatoria")
    private String marca;
    @NotNull(message = "Lo stato è obbligatorio")
    private Status stato;
    @NotNull
    private String tipoDispositivo;

    private int dipendenteId;
}

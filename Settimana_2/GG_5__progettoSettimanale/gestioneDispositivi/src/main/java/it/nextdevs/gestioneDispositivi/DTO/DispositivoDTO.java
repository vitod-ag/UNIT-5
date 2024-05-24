package it.nextdevs.gestioneDispositivi.DTO;

import it.nextdevs.gestioneDispositivi.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DispositivoDTO {
    @NotNull(message = "Il nome/modello è obbligatorio")
    @Size(max = 30)
    private String nome_modello;
    @NotNull(message = "La marca è obbligatoria")
    @Size(max = 30)
    private String marca;
    @NotNull(message = "Lo stato è obbligatorio")
    private Status stato;
    private String tipoDispositivo;

    private int dipendente_id;
}

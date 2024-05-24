package it.nextdevs.gestioneDispositivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.nextdevs.gestioneDispositivi.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public abstract class Dispositivo {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome_modello;
    private String marca;
    @Enumerated(EnumType.STRING)
    private Status stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_username")
    @JsonIgnore
    private Dipendente dipendente;

}

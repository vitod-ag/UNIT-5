package it.nextdevs.gestioneDispositivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.nextdevs.gestioneDispositivi.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Dispositivo {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomeModello;
    private String marca;
    @Enumerated(EnumType.STRING)
    private Status stato;
    private String tipoDispositivo;
    @ManyToOne
    @JoinColumn(name = "dipendente_username")
    @JsonIgnore
    private Dipendente dipendente;

}

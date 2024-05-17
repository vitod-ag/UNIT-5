package it.nextdevs.GestionePrenotazioni.bean;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente_username")
    private Utente utente;
    private LocalDate dataPrenotazione;

}

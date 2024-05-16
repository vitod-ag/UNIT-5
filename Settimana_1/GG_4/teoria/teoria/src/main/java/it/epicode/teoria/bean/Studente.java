package it.epicode.teoria.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Studente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricola;
    private String nome;
    private String cognome;
    private String indirizzo;

    @OneToMany(mappedBy = "studente", fetch = FetchType.EAGER)
    private List<Dispositivo> dispositivi;
}

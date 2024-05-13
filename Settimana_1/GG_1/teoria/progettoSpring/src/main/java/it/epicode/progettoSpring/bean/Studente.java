package it.epicode.progettoSpring.bean;

import lombok.Data;

import java.util.List;

@Data
public class Studente {
    private String nome;
    private String cognome;
    private String indirizzo;

    private List<Dispositivo> dispositivi;
}

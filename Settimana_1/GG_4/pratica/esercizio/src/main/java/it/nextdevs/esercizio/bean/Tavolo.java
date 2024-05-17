package it.nextdevs.esercizio.bean;

import it.nextdevs.esercizio.enumerations.StatoTavolo;
import lombok.Data;


@Data
public class Tavolo {
    private Integer numero;
    private Integer numeroClientiMax;
    private StatoTavolo stato;
    private Double costoCoperto;
}

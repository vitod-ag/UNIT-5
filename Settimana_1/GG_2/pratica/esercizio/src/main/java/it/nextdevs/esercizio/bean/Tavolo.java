package it.nextdevs.esercizio.bean;

import it.nextdevs.esercizio.enumerations.StatoTavolo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
public class Tavolo {
    private Integer numero;
    private Integer numeroClientiMax;
    private StatoTavolo stato;
    private Double costoCoperto;
}

package it.nextdevs.esercizio.bean;

import lombok.Data;

@Data
public class Drink extends CaloriePrezzo{
    private String nome;
    private Double litri;
    private Integer gradoAlcool;
}

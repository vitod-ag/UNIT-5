package it.nextdevs.esercizio.DTO;

import lombok.Data;

@Data
public class BlogDTO {
    private String titolo;
    private String contenuto;
    private String categoria;
    private int tempoDiLettura;

    private int autoreId;
}

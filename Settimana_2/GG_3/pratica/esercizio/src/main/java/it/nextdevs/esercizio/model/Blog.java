package it.nextdevs.esercizio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Blog {
//    private static int cont;
    @Id
    @GeneratedValue
    private int id;
    private String categoria;
    private String titolo;
    private String cover = "https://picsum.photos/200/300";
    private String contenuto;
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    @JsonIgnore
    private Autore autore;

//    public Blog(String categoria, String titolo, String contenuto, int tempoDiLettura) {
//        this.categoria = categoria;
//        this.titolo = titolo;
//        this.cover = "https://picsum.photos/200/300";
//        this.contenuto = contenuto;
//        this.tempoDiLettura = tempoDiLettura;
//        cont++;
//        id=cont;
//    }
}

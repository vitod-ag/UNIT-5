package it.nextdevs.blogging.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Autore {

    private int id;
    private static int cont;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
        this.dataDiNascita=dataDiNascita;
        this.avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome;
        cont++;
        id=cont;
    }
}

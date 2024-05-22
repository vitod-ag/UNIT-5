package it.nextdevs.esercizio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Autore {

    @Id
    @GeneratedValue
    private int id;
//    private static int cont;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome;

    @OneToMany(mappedBy = "autore")
    private List<Blog> blogs;

//    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
//        this.nome=nome;
//        this.cognome=cognome;
//        this.email=email;
//        this.dataDiNascita=dataDiNascita;
//        this.avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome;
//        cont++;
//        id=cont;
//    }
}

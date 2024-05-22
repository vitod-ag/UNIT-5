package it.epicode.teoria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Studente {
    // variabile statica per mantenere un valore comune tra tutti gli studenti
    // private static int cont;
    @Id
    @GeneratedValue
    private int matricola;

    private String nome;
    private String cognome;
    private LocalDate datanascita;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;



//    public Studente(String nome, String cognome, LocalDate datanascita) {
//        this.nome = nome;
//        this.cognome = cognome;
//        this.datanascita = datanascita;
//           cont++;  // incremento cont che essendo statica viene incrementata in ogni oggetto della classe
//           matricola=cont; // matricola prenderà il valore di cont, è un passaggio per valore.
//        // matricola non è statica e quindi indipendente da oggetto a oggetto. Il suo valore viene mantenuto
//        // uguale a cont nel momento in cui viene assegnato e non cambia se il valore di cont cambia successivamente
//    }
}

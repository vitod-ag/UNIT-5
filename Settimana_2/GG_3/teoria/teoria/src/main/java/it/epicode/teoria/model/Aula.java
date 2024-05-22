package it.epicode.teoria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Aula {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private int piano;

    @OneToMany(mappedBy = "aula")  //lato inverso e non quello proprietario
    private List<Studente> studenti;
}

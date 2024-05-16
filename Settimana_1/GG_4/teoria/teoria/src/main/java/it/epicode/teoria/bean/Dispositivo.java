package it.epicode.teoria.bean;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // per ogni sottoclasse verrà creata una tabella
public abstract class Dispositivo {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String marca;

    @ManyToOne
    @JoinColumn
    private Studente studente;

    @Override
    public String toString() {
        return "Dispositivo{" +
                "marca='" + marca + '\'' +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}

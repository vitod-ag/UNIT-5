package it.nextdevs.esercizio.bean;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CaloriePrezzo {

    @Id
    @GeneratedValue
    private int id;

    private Integer calories;
    private Double price;

}

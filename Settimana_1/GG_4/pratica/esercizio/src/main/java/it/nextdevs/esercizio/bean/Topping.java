package it.nextdevs.esercizio.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString(callSuper = true)
public class Topping extends CaloriePrezzo{

    private String nome;

    @ManyToMany(mappedBy = "toppings", fetch = FetchType.EAGER)
    private List<Pizza> pizza;

    @Override
    public String toString() {
        return "Topping{" +
                super.toString() +
                "nome='" + nome + '\'' +
                '}';
    }
}

package it.nextdevs.esercizio.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pizza extends CaloriePrezzo{
    private String nome;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza() {}

    public Pizza(String nome, List<Topping> toppings) {
        this.nome = nome;
        this.toppings.addAll(toppings);
    }
}

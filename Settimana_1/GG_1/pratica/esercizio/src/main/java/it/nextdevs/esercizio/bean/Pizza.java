package it.nextdevs.esercizio.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class Pizza extends CaloriePrezzo{
    private String nome;
    private List<Topping> toppings = new ArrayList<>();
}

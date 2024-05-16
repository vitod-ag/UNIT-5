package it.nextdevs.esercizio.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class Menu {
    @Autowired
    private List<Pizza> pizze;
    @Autowired
    private List<Topping> toppings;
    @Autowired
    private List<Drink> drinks;

    public void stampaMenu() {
        System.out.println("\n***Menu***");
        System.out.println("\nDrinks:");
        drinks.forEach(drink -> System.out.println(drink.getNome() +
                " ml: " + drink.getLitri() +
                " Kcal: " + drink.getCalories() +
                " prezzo: " + drink.getPrice()));
        System.out.println();

        System.out.println("Ingredienti:");
        toppings.forEach(topping -> System.out.println(topping.getNome() +
                " Kcal: " +  topping.getCalories() +
                " prezzo: " + topping.getPrice()));
        System.out.println();

        System.out.println("Pizze:");
        pizze.forEach(pizza -> System.out.println(pizza.getNome() +
                " Kcal: " + pizza.getCalories() +
                " prezzo: " + pizza.getPrice()));
    }
}

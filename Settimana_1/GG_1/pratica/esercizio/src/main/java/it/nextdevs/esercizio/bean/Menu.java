package it.nextdevs.esercizio.bean;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private List<Pizza> pizze;
    private List<Topping> toppings;
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

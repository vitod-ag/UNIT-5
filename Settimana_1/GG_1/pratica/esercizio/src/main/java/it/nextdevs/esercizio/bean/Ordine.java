package it.nextdevs.esercizio.bean;

import it.nextdevs.esercizio.enumerations.StatoOrdine;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Ordine {
    private Integer numeroOrdine;
    private StatoOrdine statoOrdine;
    private Integer numeroCoperti;
    private LocalDateTime dataOrdine;
    private Tavolo tavolo;

    private List<Drink> drinks;
    private List<Topping> toppings;
    private List<Pizza> pizze;

    public double prezzoTotale() {
        double totale = drinks.stream().mapToDouble(Drink::getPrice).sum();  // mappo in double il totale prezzo dei drink
        totale+=toppings.stream().mapToDouble(Topping::getPrice).sum();  // mappo in double il totale prezzo dei toppings
        totale+=pizze.stream().mapToDouble(Pizza::getPrice).sum();    // mappo in double il totale prezzo delle pizze
        totale+=tavolo.getCostoCoperto()*numeroCoperti;   // aggiungo al totale il costo del coperto * numero dei coperti
        return totale;
    }

    public Ordine(Integer numeroCoperti, Tavolo tavolo) throws Exception{       // verifico se il num dei coperti sia inferiore del numero massimo dei clienti per quel tavolo
        if(numeroCoperti<=tavolo.getNumeroClientiMax()){
            this.numeroCoperti = numeroCoperti;
            this.tavolo = tavolo;
        }else {
            throw new Exception("Numero coperti superiori al numero del tavolo");
        }
    }

    public void stampaOrdine(){
        System.out.println("\nOrdine n. " + numeroOrdine);
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

        System.out.println();
        System.out.println("\nTotale: " + prezzoTotale());
    }
}

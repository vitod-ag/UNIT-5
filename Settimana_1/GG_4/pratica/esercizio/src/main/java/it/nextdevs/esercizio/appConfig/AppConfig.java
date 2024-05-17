package it.nextdevs.esercizio.appConfig;

import it.nextdevs.esercizio.bean.*;
import it.nextdevs.esercizio.enumerations.StatoTavolo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;

@Configuration
@PropertySource("application.properties")
public class AppConfig {


    @Bean("tomato")
    public Topping getTomato() {
        Topping tomato = new Topping();
        tomato.setNome("tomato");
        tomato.setCalories(70);
        tomato.setPrice(2.5);
        return tomato;
    }

    @Bean("cheese")
    public Topping getCheese() {
        Topping cheese = new Topping();
        cheese.setNome("cheese");
        cheese.setCalories(90);
        cheese.setPrice(3.7);
        return cheese;
    }

    @Bean("ham")
    public Topping getHam() {
        Topping ham = new Topping();
        ham.setNome("ham");
        ham.setCalories(60);
        ham.setPrice(4.5);
        return ham;
    }

    @Bean("onion")
    public Topping getOnion() {
        Topping topping = new Topping();
        topping.setNome("onion");
        topping.setCalories(68);
        topping.setPrice(5.5);
        return topping;
    }

    @Bean("pineapple")
    public Topping getPineapple() {
        Topping topping = new Topping();
        topping.setNome("pineapple");
        topping.setCalories(75);
        topping.setPrice(6.3);
        return topping;
    }
    @Bean("salami")
    public Topping salami() {
        Topping topping = new Topping();
        topping.setNome("salami");
        topping.setCalories(65);
        topping.setPrice(5.0);
        return topping;
    }
    //---------------------------------


    @Bean("Lemonade")
    public Drink getLemonade() {
        Drink lemonade = new Drink();
        lemonade.setNome("Lemonade");
        lemonade.setPrice(1.29);
        lemonade.setCalories(128);
        lemonade.setLitri(0.33);
        lemonade.setGradoAlcool(0);
        return lemonade;
    }

    @Bean("Water")
    public Drink getWater() {
        Drink water = new Drink();
        water.setNome("Water");
        water.setPrice(1.29);
        water.setCalories(0);
        water.setLitri(0.5);
        water.setGradoAlcool(0);
        return water;
    }

    @Bean("Wine")
    public Drink getWine() {
        Drink wine = new Drink();
        wine.setNome("Wine");
        wine.setPrice(7.49);
        wine.setCalories(607);
        wine.setLitri(0.75);
        wine.setGradoAlcool(13);
        return wine;
    }

    //---------METODO BASE PER LA PIZZA BASE----------------------------------

    public Pizza base() {
        Pizza base = new Pizza();
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(getTomato());
        toppings.add(getCheese());
        return base;
    }

    //------------------------------------------

    @Bean("Margherita")
    public Pizza getMargherita() {
        Pizza margherita = base();   // alla Margherita li assegno la pizza base
        margherita.setNome("Margherita");
        margherita.setCalories(1104);
        margherita.setPrice(8.5);
        return margherita;
    }

    @Bean("Hawaian_Pizza")
    public Pizza getHawaian() {
        Pizza hawaian = base();
        hawaian.setNome("Hawaian_Pizza");
        hawaian.getToppings().add(getPineapple());
        hawaian.getToppings().add(getHam());
        hawaian.setCalories(1024);
        hawaian.setPrice(6.29);
        return hawaian;
    }

    @Bean("Salami_Pizza")
    public Pizza getSalami() {
        Pizza salami = base();
        salami.setNome("Salami_Pizza");
        salami.getToppings().add(salami());
        salami.setCalories(1160);
        salami.setPrice(5.99);
        return salami;
    }

    //@Bean
    public Menu menu () {
        Menu menu = new Menu();

        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(getWine());
        drinks.add(getWater());
        drinks.add(getLemonade());

        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(getCheese());
        toppings.add(getCheese());
        toppings.add(getHam());
        toppings.add(getPineapple());
        toppings.add(getOnion());
        toppings.add(salami());

        ArrayList<Pizza> pizze = new ArrayList<>();
        pizze.add(getMargherita());
        pizze.add(getHawaian());
        pizze.add(getSalami());

        menu.setDrinks(drinks);
        menu.setToppings(toppings);
        menu.setPizze(pizze);

        return menu;
    }

    @Bean("tavolo1")
    public Tavolo tavolo1(@Value("${tavolo1.coperto}")Double coperto) {
        Tavolo tavolo = new Tavolo();
        tavolo.setNumero(1);
        tavolo.setNumeroClientiMax(15);
        tavolo.setStato(StatoTavolo.OCCUPATO);
        tavolo.setCostoCoperto(coperto);
        return tavolo;
    }

    @Bean("tavolo2")
    public Tavolo tavolo2(@Value("${tavolo2.coperto}")Double coperto) {
        Tavolo tavolo = new Tavolo();
        tavolo.setNumero(1);
        tavolo.setNumeroClientiMax(7);
        tavolo.setStato(StatoTavolo.OCCUPATO);
        tavolo.setCostoCoperto(coperto);
        return tavolo;
    }

    @Bean("tavolo3")
    public Tavolo tavolo3(@Value("${tavolo3.coperto}")Double coperto) {
        Tavolo tavolo = new Tavolo();
        tavolo.setNumero(1);
        tavolo.setNumeroClientiMax(4);
        tavolo.setStato(StatoTavolo.OCCUPATO);
        tavolo.setCostoCoperto(coperto);
        return tavolo;
    }
}


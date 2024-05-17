package it.nextdevs.esercizio;

import it.nextdevs.esercizio.bean.*;
import it.nextdevs.esercizio.service.CaloriePrezzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CaloriePrezzoService caloriePrezzoService;

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(EsercizioApplication.class);// accesso al contesto

//
//        Menu menu = ctx.getBean(Menu.class);
//        menu.stampaMenu();
//
//        Tavolo tavolo1 = ctx.getBean("tavolo1", Tavolo.class);
//
//        Ordine ordine = new Ordine(5, tavolo1);
//        ordine.setNumeroOrdine(1);
//        ordine.setDataOrdine(LocalDateTime.now());
//        ordine.setStatoOrdine(StatoOrdine.IN_CORSO);
//        ordine.setDrinks(List.of(menu.getDrinks().get(2), menu.getDrinks().get(1)));
//        ordine.setToppings(List.of(menu.getToppings().get(1), menu.getToppings().get(2)));
//        ordine.setPizze(List.of(menu.getPizze().get(2), menu.getPizze().get(1), menu.getPizze().get(0)));
//
//        ordine.stampaOrdine();



        // creo i drink nel DB
        Drink drink1 = ctx.getBean("Lemonade", Drink.class);
        caloriePrezzoService.inserisciCaloriePrezzo(drink1);
        Drink drink2 = ctx.getBean("Water", Drink.class);
        caloriePrezzoService.inserisciCaloriePrezzo(drink2);
        Drink drink3 = ctx.getBean("Wine", Drink.class);
        caloriePrezzoService.inserisciCaloriePrezzo(drink3);


        // creo i topping nel DB
        Topping topping1 = ctx.getBean("salami", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping1);
        Topping topping2 = ctx.getBean("pineapple", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping2);
        Topping topping3 = ctx.getBean("onion", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping3);
        Topping topping4 = ctx.getBean("ham", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping4);
        Topping topping5 = ctx.getBean("cheese", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping5);
        Topping topping6 = ctx.getBean("tomato", Topping.class);
        caloriePrezzoService.inserisciCaloriePrezzo(topping6);

        // creo i pizze nel DB
        Pizza pizza1 = ctx.getBean("Margherita", Pizza.class);
        caloriePrezzoService.inserisciCaloriePrezzo(pizza1);
        Pizza pizza2 = ctx.getBean("Hawaian_Pizza", Pizza.class);
        caloriePrezzoService.inserisciCaloriePrezzo(pizza2);
        Pizza pizza3 = ctx.getBean("Salami_Pizza", Pizza.class);
        caloriePrezzoService.inserisciCaloriePrezzo(pizza3);


    }
}

package it.nextdevs.esercizio;

import it.nextdevs.esercizio.bean.Menu;
import it.nextdevs.esercizio.bean.Ordine;
import it.nextdevs.esercizio.bean.Tavolo;
import it.nextdevs.esercizio.enumerations.StatoOrdine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(EsercizioApplication.class);// accesso al contesto


        Menu menu = ctx.getBean(Menu.class);
        menu.stampaMenu();

        Tavolo tavolo1 = ctx.getBean("tavolo1", Tavolo.class);

        Ordine ordine = new Ordine(5, tavolo1);
        ordine.setNumeroOrdine(1);
        ordine.setDataOrdine(LocalDateTime.now());
        ordine.setStatoOrdine(StatoOrdine.IN_CORSO);
        ordine.setDrinks(List.of(menu.getDrinks().get(2), menu.getDrinks().get(1)));
        ordine.setToppings(List.of(menu.getToppings().get(1), menu.getToppings().get(2)));
        ordine.setPizze(List.of(menu.getPizze().get(2), menu.getPizze().get(1), menu.getPizze().get(0)));

        ordine.stampaOrdine();
    }
}

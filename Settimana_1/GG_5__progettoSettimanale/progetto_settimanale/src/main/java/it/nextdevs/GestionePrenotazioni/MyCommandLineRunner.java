package it.nextdevs.GestionePrenotazioni;

import it.nextdevs.GestionePrenotazioni.bean.Edificio;
import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.bean.Prenotazione;
import it.nextdevs.GestionePrenotazioni.bean.Utente;
import it.nextdevs.GestionePrenotazioni.enumerations.TipoPostazione;
import it.nextdevs.GestionePrenotazioni.service.EdificioService;
import it.nextdevs.GestionePrenotazioni.service.PostazioneService;
import it.nextdevs.GestionePrenotazioni.service.PrenotazioneService;
import it.nextdevs.GestionePrenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("application.properties")
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UtenteService utenteService;

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProgettoSettimanaleApplication.class);

        // Creo gli utenti nel DB
        Utente utente1 = ctx.getBean("utente1", Utente.class);
        Utente utente2 = ctx.getBean("utente2", Utente.class);
        utenteService.inserisciUtente(utente1);
        utenteService.inserisciUtente(utente2);

        // Creo gli edifici nel DB
        Edificio edificio1 = ctx.getBean("edificio1", Edificio.class);
        Edificio edificio2 = ctx.getBean("edificio2", Edificio.class);
        edificioService.inserisciEdificio(edificio1);
        edificioService.inserisciEdificio(edificio2);

        // Creo le postazioni nel DB
        Postazione postazione1 = ctx.getBean("postazione1", Postazione.class);
        Postazione postazione2 = ctx.getBean("postazione2", Postazione.class);
        postazioneService.inserisciPostazione(postazione1);
        postazioneService.inserisciPostazione(postazione2);

        // Creo le prenotazioni nel DB
        Prenotazione prenotazione1 = ctx.getBean("prenotazione1", Prenotazione.class);
        Prenotazione prenotazione2 = ctx.getBean("prenotazione2", Prenotazione.class);
        Prenotazione prenotazione3 = ctx.getBean("prenotazione3", Prenotazione.class);
        Prenotazione prenotazione4 = ctx.getBean("prenotazione4", Prenotazione.class);

        try {
            prenotazioneService.inserisciPrenotazione(prenotazione1);
            System.out.println("Prenotazione 1 effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        try {
            prenotazioneService.inserisciPrenotazione(prenotazione2);
            System.out.println("Prenotazione 2 effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        try {
            prenotazioneService.inserisciPrenotazione(prenotazione3);
            System.out.println("Prenotazione 3 effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        try {
            prenotazioneService.inserisciPrenotazione(prenotazione4);
            System.out.println("Prenotazione 4 effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        List<Postazione> postazioni = postazioneService.getPostazioniByTipoAndCitta(TipoPostazione.PRIVATO, "Roma");
        System.out.println("\nQUERY -> Postazioni trovate: ");
        for (Postazione postazione : postazioni) {
            System.out.println(postazione);
        }
    }
}

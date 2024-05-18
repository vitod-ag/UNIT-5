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
import org.springframework.context.ApplicationContext;
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ProgettoSettimanaleApplication.class);

        //creo gli utenti nel DB
        Utente utente1 = ctx.getBean("utente1", Utente.class);
        utenteService.inserisciUtente(utente1);

        //creo l'edificio nel DB
        Edificio edificio1 = ctx.getBean("edificio1", Edificio.class);
        edificioService.inserisciEdificio(edificio1);

        //creo postazione in DB
        Postazione postazione1 = ctx.getBean("postazione1", Postazione.class);
        postazioneService.inserisciPostazione(postazione1);

        // creo una prenotazione nel DB
        Prenotazione prenotazione1 = null;
        try {
            prenotazione1 = ctx.getBean("prenotazione1", Prenotazione.class);
            prenotazioneService.inserisciPrenotazione(prenotazione1);
            System.out.println("Prenotazione effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        Prenotazione prenotazioneDuplicata = new Prenotazione();
        prenotazioneDuplicata.setPostazione(postazione1);
        prenotazioneDuplicata.setUtente(utente1);
        prenotazioneDuplicata.setDataPrenotazione(prenotazione1.getDataPrenotazione());
        try {
            prenotazioneService.inserisciPrenotazione(prenotazioneDuplicata);
            System.out.println("Seconda prenotazione effettuata");
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        List<Postazione> postazioni = postazioneService.getPostazioniByTipoAndCitta(TipoPostazione.PRIVATO, "Roma");
        System.out.println("\nQUERY -> Postazioni trovate: ");
        for(Postazione postazione: postazioni) {
            System.out.println(postazione);
        }
    }
}

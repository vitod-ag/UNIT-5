package it.nextdevs.GestionePrenotazioni.appConfig;

import it.nextdevs.GestionePrenotazioni.bean.Edificio;
import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.bean.Prenotazione;
import it.nextdevs.GestionePrenotazioni.bean.Utente;
import it.nextdevs.GestionePrenotazioni.enumerations.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class appConfig {

    @Bean("utente1")
    public Utente getUtente1() {
        Utente utente1 = new Utente();
        utente1.setUsername("mario_blonde");
        utente1.setNomeCompleto("Mario Biondi");
        utente1.setMail("mario@biondi.com");
        return utente1;
    }

    @Bean("utente2")
    public Utente getUtente2() {
        Utente utente2 = new Utente();
        utente2.setUsername("anita_frk");
        utente2.setNomeCompleto("Anita Franklin");
        utente2.setMail("anita@franklin.com");
        return utente2;
    }

    @Bean("edificio1")
    public Edificio getEdificio1() {
        Edificio edificio1 = new Edificio();
        edificio1.setNome("Palazzo Reale");
        edificio1.setIndirizzo("via Collissi, 2");
        edificio1.setCitta("Roma");
        return edificio1;
    }

    @Bean("edificio2")
    public Edificio getEdificio2() {
        Edificio edificio2 = new Edificio();
        edificio2.setNome("Locale dei Mercanti");
        edificio2.setIndirizzo("via Adda, 12");
        edificio2.setCitta("Milano");
        return edificio2;
    }

    @Bean("postazione1")
    public Postazione getPostazione1() {
        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Postazione privata nei pressi del municipio");
        postazione1.setTipoPostazione(TipoPostazione.PRIVATO);
        postazione1.setNumeroMaxPartecipanti(10);
        postazione1.setEdificio(getEdificio1());
        return postazione1;
    }

    @Bean("postazione2")
    public Postazione getPostazione2() {
        Postazione postazione2 = new Postazione();
        postazione2.setDescrizione("Postazione aperta in piazza");
        postazione2.setTipoPostazione(TipoPostazione.OPENSPACE);
        postazione2.setNumeroMaxPartecipanti(20);
        postazione2.setEdificio(getEdificio2());
        return postazione2;
    }

    @Bean("prenotazione1")
    public Prenotazione getPrenotazione1() {
        Prenotazione prenotazione1 = new Prenotazione();
        prenotazione1.setPostazione(getPostazione1());
        prenotazione1.setUtente(getUtente1());
        prenotazione1.setDataPrenotazione(LocalDate.now());
        return prenotazione1;
    }

    @Bean("prenotazione2")
    public Prenotazione getPrenotazione2() {
        Prenotazione prenotazione2 = new Prenotazione();
        prenotazione2.setPostazione(getPostazione2());
        prenotazione2.setUtente(getUtente2());
        prenotazione2.setDataPrenotazione(LocalDate.now().plusDays(1));
        return prenotazione2;
    }

    @Bean("prenotazione3")
    public Prenotazione getPrenotazione3() {
        Prenotazione prenotazione3 = new Prenotazione();
        prenotazione3.setPostazione(getPostazione1());
        prenotazione3.setUtente(getUtente2());
        prenotazione3.setDataPrenotazione(LocalDate.now());
        return prenotazione3;
    }

    @Bean("prenotazione4")
    public Prenotazione getPrenotazione4() {
        Prenotazione prenotazione4 = new Prenotazione();
        prenotazione4.setPostazione(getPostazione2());
        prenotazione4.setUtente(getUtente1());
        prenotazione4.setDataPrenotazione(LocalDate.now().plusDays(3));
        return prenotazione4;
    }
}

package it.nextdevs.GestionePrenotazioni.appConfig;

import it.nextdevs.GestionePrenotazioni.bean.Edificio;
import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.bean.Prenotazione;
import it.nextdevs.GestionePrenotazioni.bean.Utente;
import it.nextdevs.GestionePrenotazioni.enumerations.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Bean("edificio1")
    public Edificio getEdificio1() {
        Edificio edificio1 = new Edificio();
        edificio1.setNome("Edificio A");
        edificio1.setIndirizzo("via Collissi, 2");
        edificio1.setCitta("Roma");
        return edificio1;
    }

    @Bean("postazione1")
    public Postazione getPostazione1() {
        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Postazione privata vicino alla finestra");
        postazione1.setTipoPostazione(TipoPostazione.PRIVATO);
        postazione1.setNumeroMaxPartecipanti(10);
        postazione1.setEdificio(getEdificio1());
        return postazione1;
    }

    @Bean("prenotazione1")
    public Prenotazione getPrenotazione1() {
        Prenotazione prenotazione1 = new Prenotazione();
        prenotazione1.setPostazione(getPostazione1());
        prenotazione1.setUtente(getUtente1());
        prenotazione1.setDataPrenotazione(LocalDate.now());
        return prenotazione1;
    }



}

package it.nextdevs.GestionePrenotazioni.repositories;

import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.bean.Prenotazione;
import it.nextdevs.GestionePrenotazioni.bean.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository <Prenotazione, Integer> {

    @Query("SELECT p FROM Prenotazione p WHERE p.utente = :utente AND p.dataPrenotazione = :dataPrenotazione")
    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate dataPrenotazione);

    @Query("SELECT p FROM Prenotazione p WHERE p.postazione = :postazione AND p.dataPrenotazione = :dataPrenotazione")
    List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate dataPrenotazione);
}

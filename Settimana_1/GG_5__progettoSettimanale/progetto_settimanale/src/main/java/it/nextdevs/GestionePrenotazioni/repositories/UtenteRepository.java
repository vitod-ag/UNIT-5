package it.nextdevs.GestionePrenotazioni.repositories;

import it.nextdevs.GestionePrenotazioni.bean.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtenteRepository extends JpaRepository<Utente, String> {
    @Query("SELECT u FROM Utente u WHERE u.username = 'mario_blonde'")
    public Utente findByUsername(String username);
}

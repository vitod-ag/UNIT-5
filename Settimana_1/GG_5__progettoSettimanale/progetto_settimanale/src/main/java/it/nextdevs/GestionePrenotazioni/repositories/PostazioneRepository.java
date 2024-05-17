package it.nextdevs.GestionePrenotazioni.repositories;

import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.enumerations.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository <Postazione, Integer> {
    @Query("SELECT p FROM Postazione p WHERE p.tipoPostazione = :tipoPostazione AND p.edificio.citta = :citta")
    public List<Postazione> findByTipoAndCitta(TipoPostazione tipoPostazione, String citta);
}

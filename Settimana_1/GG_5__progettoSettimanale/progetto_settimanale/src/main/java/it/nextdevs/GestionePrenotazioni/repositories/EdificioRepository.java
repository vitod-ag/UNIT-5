package it.nextdevs.GestionePrenotazioni.repositories;

import it.nextdevs.GestionePrenotazioni.bean.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    @Query("SELECT e FROM Edificio e WHERE e.nome = 'Edificio A'")
    public Edificio findByNome(String nome);
}

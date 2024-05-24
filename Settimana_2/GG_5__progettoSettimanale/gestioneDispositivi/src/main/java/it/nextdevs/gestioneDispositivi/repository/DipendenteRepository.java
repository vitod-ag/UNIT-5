package it.nextdevs.gestioneDispositivi.repository;

import it.nextdevs.gestioneDispositivi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
}

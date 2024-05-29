package it.nextdevs.GestioneDispositivi.repository;

import it.nextdevs.GestioneDispositivi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
    public Optional<Dipendente> findByEmail(String email);
}

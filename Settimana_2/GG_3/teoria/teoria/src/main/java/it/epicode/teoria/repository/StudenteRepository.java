package it.epicode.teoria.repository;

import it.epicode.teoria.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {
}

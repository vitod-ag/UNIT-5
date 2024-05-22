package it.nextdevs.esercizio.repository;

import it.nextdevs.esercizio.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {
}

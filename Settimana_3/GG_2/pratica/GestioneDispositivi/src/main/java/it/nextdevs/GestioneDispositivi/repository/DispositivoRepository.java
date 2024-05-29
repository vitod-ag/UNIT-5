package it.nextdevs.GestioneDispositivi.repository;

import it.nextdevs.GestioneDispositivi.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}

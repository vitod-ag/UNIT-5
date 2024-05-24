package it.nextdevs.gestioneDispositivi.repository;

import it.nextdevs.gestioneDispositivi.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}

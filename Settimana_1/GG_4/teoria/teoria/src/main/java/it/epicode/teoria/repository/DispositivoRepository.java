package it.epicode.teoria.repository;

import it.epicode.teoria.bean.Computer;
import it.epicode.teoria.bean.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
    public List<Computer> findByRamLessThan(int ram);

    // Custom Query
    //      @Query(value = "select d from Dispositivo d order by d.nome desc", nativeQuery = true)
    //   native query per scrivere query in sql
    @Query(value = "SELECT d FROM Dispositivo d ORDER BY d.nome DESC")
    public List<Dispositivo> findAllOrderByNomeDesc();

}

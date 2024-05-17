package it.nextdevs.esercizio.repository;

import it.nextdevs.esercizio.bean.CaloriePrezzo;
import it.nextdevs.esercizio.bean.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaloriePrezzoRepository  extends JpaRepository<CaloriePrezzo, Integer> {

    public List<Pizza> findByPriceLessThan(Double price);

    @Query("SELECT COUNT(p) FROM Pizza p")
    public Integer countPizze();
}

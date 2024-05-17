package it.nextdevs.esercizio.service;

import it.nextdevs.esercizio.bean.CaloriePrezzo;
import it.nextdevs.esercizio.bean.Pizza;
import it.nextdevs.esercizio.repository.CaloriePrezzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CaloriePrezzoService{

    @Autowired
    private CaloriePrezzoRepository caloriePrezzoRepository;

    public void inserisciCaloriePrezzo(CaloriePrezzo caloriePrezzo) {
        caloriePrezzoRepository.save(caloriePrezzo);
    }

    public CaloriePrezzo getCaloriePrezzo(int id){
        return caloriePrezzoRepository.findById(id).get();
    }

    public List<CaloriePrezzo> getCaloriePrezzi() {
        return caloriePrezzoRepository.findAll();
    }

    public void cancellaCaloriePrezzo(int id){
        caloriePrezzoRepository.deleteById(id);
    }

    public List<Pizza> getPizzaByPriceLessThan(Double price) {
        return caloriePrezzoRepository.findByPriceLessThan(price);
    }

    public long countPizze(){
        return caloriePrezzoRepository.countPizze();
    }
}

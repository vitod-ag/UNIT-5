package it.nextdevs.GestionePrenotazioni.service;

import it.nextdevs.GestionePrenotazioni.bean.Edificio;
import it.nextdevs.GestionePrenotazioni.bean.Utente;
import it.nextdevs.GestionePrenotazioni.repositories.EdificioRepository;
import it.nextdevs.GestionePrenotazioni.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public void inserisciUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public Utente getUtente(String username){
        return utenteRepository.findById(username).get();
    }

    public List<Utente> getUtente() {
        return utenteRepository.findAll();
    }

    public void cancellaEdificio(String username){
        utenteRepository.deleteById(username);
    }

}

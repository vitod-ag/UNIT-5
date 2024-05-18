package it.nextdevs.GestionePrenotazioni.service;

import it.nextdevs.GestionePrenotazioni.bean.Prenotazione;
import it.nextdevs.GestionePrenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void inserisciPrenotazione(Prenotazione prenotazione) {

        // Verifico se la prenotazione è duplicata
        List<Prenotazione> prenotazioniDuplicate = prenotazioneRepository.findByPostazioneAndUtenteAndDataPrenotazione(
                prenotazione.getPostazione(), prenotazione.getUtente(), prenotazione.getDataPrenotazione());

        if (!prenotazioniDuplicate.isEmpty()) {
            throw new IllegalArgumentException("Errore: La prenotazione è duplicata");
        }

        // Verifico se l'utente ha già una prenotazione per questa data
        List<Prenotazione> prenotazioniUtentiData = prenotazioneRepository.findByUtenteAndData(prenotazione.getUtente(), prenotazione.getDataPrenotazione());
        if (!prenotazioniUtentiData.isEmpty()) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data");
        }

        // Verifico se la postazione è già prenotata per questa data
        List<Prenotazione> prenotazioniPostazioniData = prenotazioneRepository.findByPostazioneAndData(prenotazione.getPostazione(), prenotazione.getDataPrenotazione());
        if (!prenotazioniPostazioniData.isEmpty()) {
            throw new IllegalArgumentException("La postazione è già prenotata per questa data");
        }

        // Verifico se la postazione ha raggiunto il numero massimo di partecipanti
        Integer numeroMaxPartecipanti = prenotazione.getPostazione().getNumeroMaxPartecipanti();
        if (numeroMaxPartecipanti != null) {
            Integer partecipantiAttuali = prenotazioniPostazioniData.size();
            if (partecipantiAttuali >= numeroMaxPartecipanti) {
                throw new IllegalArgumentException("La postazione ha raggiunto il numero massimo di partecipanti consentiti");
            }
        }

        prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione getPrenotazione(int id) {
        return prenotazioneRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public void cancellaPrenotazione(int id) {
        prenotazioneRepository.deleteById(Math.toIntExact(id));
    }
}

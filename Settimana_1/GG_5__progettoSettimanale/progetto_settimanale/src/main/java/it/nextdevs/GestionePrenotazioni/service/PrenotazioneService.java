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

        // verifiche se un utente può avere più prenotazioni in corso,
        // ma non può prenotare più di una postazione per una particolare data.
        List<Prenotazione> prenotazioniUtentiData = prenotazioneRepository.findByUtenteAndData(prenotazione.getUtente(), prenotazione.getDataPrenotazione());
        if (!prenotazioniUtentiData.isEmpty()) {
            throw new IllegalArgumentException("L'utente ha già prenotata per questa data");
        }

        List<Prenotazione> prenotazioniPostazioniData = prenotazioneRepository.findByPostazioneAndData(prenotazione.getPostazione(), prenotazione.getDataPrenotazione());
        if(!prenotazioniUtentiData.isEmpty()) {
            throw new IllegalArgumentException("La postazione è già prenotata per questa data");
        }

        Integer numeroMaxPartecipanti = prenotazione.getPostazione().getNumeroMaxPartecipanti();
        if (numeroMaxPartecipanti != null) {
            Integer partecipantiAttuali = prenotazioniUtentiData.size();
            if (partecipantiAttuali >= numeroMaxPartecipanti.intValue()) {
                throw new IllegalArgumentException("La postazione ha raggiunto il numero massimo dei partecipanti consentiti");
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

package it.nextdevs.GestionePrenotazioni.service;

import it.nextdevs.GestionePrenotazioni.bean.Postazione;
import it.nextdevs.GestionePrenotazioni.enumerations.TipoPostazione;
import it.nextdevs.GestionePrenotazioni.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public void inserisciPostazione(Postazione postazione) {
        postazioneRepository.save(postazione);
    }

    public Postazione getPostazione(int id){
        return postazioneRepository.findById(id).get();
    }

    public List<Postazione> getPostazioni() {
        return postazioneRepository.findAll();
    }

    public void cancellaPostazione(int id){
        postazioneRepository.deleteById(id);
    }

    public List<Postazione> getPostazioniByTipoAndCitta(TipoPostazione tipoPostazione, String citta) {
        return postazioneRepository.findByTipoAndCitta(tipoPostazione, citta);
    }

}

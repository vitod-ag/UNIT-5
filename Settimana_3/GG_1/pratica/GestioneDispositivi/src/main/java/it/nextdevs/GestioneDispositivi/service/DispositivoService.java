package it.nextdevs.GestioneDispositivi.service;


import it.nextdevs.GestioneDispositivi.DTO.DispositivoDTO;
import it.nextdevs.GestioneDispositivi.enums.Status;
import it.nextdevs.GestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.nextdevs.GestioneDispositivi.exception.DispositivoNonTrovatoException;
import it.nextdevs.GestioneDispositivi.model.*;
import it.nextdevs.GestioneDispositivi.repository.DipendenteRepository;
import it.nextdevs.GestioneDispositivi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Optional<Dispositivo> getDispositivoById(int id) {
        return dispositivoRepository.findById(id);
    }

    public String saveDispositivo(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo = switch (dispositivoDTO.getTipoDispositivo()) {
            case "laptop" -> new Laptop();
            case "smartphone" -> new Smartphone();
            case "tablet" -> new Tablet();
            default -> throw new IllegalArgumentException("Tipo di dispositivo non supportato");
        };

        dispositivo.setTipoDispositivo(dispositivoDTO.getTipoDispositivo());
        dispositivo.setNomeModello(dispositivoDTO.getNomeModello());
        dispositivo.setMarca(dispositivoDTO.getMarca());
        dispositivo.setStato(dispositivoDTO.getStato());

        Optional<Dipendente> dipendenteOpt = dipendenteRepository.findById(dispositivoDTO.getDipendenteId());
        if (dipendenteOpt.isPresent()) {
            Dipendente dipendente = dipendenteOpt.get();
            dispositivo.setDipendente(dipendente);
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id " + dispositivoDTO.getDipendenteId() + " non trovato");
        }

        dispositivoRepository.save(dispositivo);
        return "Dispositivo " + dispositivo.getId() + " salvato con successo";

    }


    public Page<Dispositivo> getAllDispositivo(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dispositivoRepository.findAll(pageable);
    }



    public Dispositivo updateDispositivo(int id, DispositivoDTO dispositivoDTO) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if (dispositivoOptional.isPresent()) {
            Dispositivo dispositivo = dispositivoOptional.get();
            dispositivo.setNomeModello(dispositivoDTO.getNomeModello());
            dispositivo.setMarca(dispositivoDTO.getMarca());
            dispositivo.setStato(dispositivoDTO.getStato());

            Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(dispositivoDTO.getDipendenteId());
            if (dipendenteOptional.isPresent()) {
                Dipendente dipendente = dipendenteOptional.get();
                dispositivo.setDipendente(dipendente);
                dispositivoRepository.save(dispositivo);
                return dispositivo;
            } else {
                throw new DipendenteNonTrovatoException("Dipendente con l'id " + dispositivoDTO.getDipendenteId() + " non trovato");
            }
        }else {
            throw new DispositivoNonTrovatoException("Dispositivo con l'id " + id + " non esiste");
        }
    }

    public String deleteDispositivo(int id) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if (dispositivoOptional.isPresent()) {
            dispositivoRepository.delete(dispositivoOptional.get());
            return "Dispositivo con l'id " + id + "eliminato";
        }else  {
            throw new DispositivoNonTrovatoException("Dispositivo con l'id " + id + "non trovato");
        }
    }

    public Dispositivo assegnaDispositivoAlDipendente(int id, Dipendente dipendente) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if ( dispositivoOptional.isPresent() ) {
            Dispositivo dispositivo = dispositivoOptional.get();
            dispositivo.setDipendente(dipendente);
            dispositivo.setStato(Status.ASSEGNATO);
            return dispositivoRepository.save(dispositivo);
        }else {
            throw new DispositivoNonTrovatoException("Dispositivo non trovato");
        }
    }
}

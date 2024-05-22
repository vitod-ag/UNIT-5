package it.epicode.teoria.service;

import it.epicode.teoria.DTO.AulaDTO;
import it.epicode.teoria.exception.AulaNonTrovataException;
import it.epicode.teoria.model.Aula;
import it.epicode.teoria.repository.Aularepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    @Autowired
    private Aularepository aularepository;

    public String saveAula(AulaDTO aulaDTO) {
        Aula aula = new Aula();
        aula.setNome(aulaDTO.getNome());
        aula.setPiano(aulaDTO.getPiano());

        aularepository.save(aula);
        return "Aula con id " + aula.getId() + " creata con successo";
    }

    public List<Aula> getAule() {
        return aularepository.findAll();
    }

    public Optional<Aula> getAulaById(int id) {
        return aularepository.findById(id);
    }

    public Aula updateAula(int id, AulaDTO aulaDTO) {
        Optional<Aula> aulaOptional = getAulaById(id);

        if( aulaOptional.isPresent() ) {
            Aula aula = aulaOptional.get();
            aula.setNome(aulaDTO.getNome());
            aula.setPiano(aulaDTO.getPiano());

            return aularepository.save(aula);
        }else{
            throw new AulaNonTrovataException("Aula con id " + id + " non trovata");
        }
    }

    public String deleteAula(int id) {
        Optional<Aula> aulaOptional = getAulaById(id);
        if( aulaOptional.isPresent() ) {
            aularepository.delete(aulaOptional.get());
            return "Aula con id " + id + " cancellata";
        }else{
            throw new AulaNonTrovataException("Aula con id " + id + " non trovata");
        }
    }

}

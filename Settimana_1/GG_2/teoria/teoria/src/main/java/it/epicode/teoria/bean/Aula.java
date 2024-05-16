package it.epicode.teoria.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component("aula1")   // permette l'Inversion of Control e crea l'oggetto Aula
@PropertySource("application.properties")
public class Aula {
    @Value("${aula.nome}")
    public String nome;

    @Autowired  //inietta la lista degli studenti prendendoli direttamente dal contesto
    private List<Studente> studenti;
//
//    @Autowired  // inietto gli studenti che sono nel context nella lista del costruttore
//    public Aula(List<Studente> studenti) {
//        this.studenti = studenti;
//    }

//
//    @Autowired
//    public void setStudenti(List<Studente> studenti) {
//        this.studenti = studenti;
//    }

}

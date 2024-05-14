package it.epicode.teoria.appConfig;

import it.epicode.teoria.bean.Aula;
import it.epicode.teoria.bean.Computer;
import it.epicode.teoria.bean.Smartphone;
import it.epicode.teoria.bean.Studente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration  // mettendo questo facciamo capire che questa contiene Beans
public class AppConfig {
    // fondamentale scrivere Bean così Spring capisce
    //@Bean("Carlo")
    @Bean
    @Primary    // significa: "se ci sono due bean ritornami questo che ha Primary
    //@Scope("prototype")  // cosi posso creare un modello che funge da prototipo e mi permette di creare oggetti diversi da questo
    public Studente getStudente(@Value("${studente1.nome}") String nome,@Value("${studente1.cognome}") String cognome){
        Studente studente = new Studente();
        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setIndirizzo("via Roma, 22");
        studente.setDispositivi(List.of(getComputer(),getSmarthphone()));
        return studente;
    }

    //@Bean("Michele") mettendo i nomi ai Bean posso richiamarne piu di uno
    @Bean
    public Studente getStudente2(){
        Studente studente = new Studente();
        studente.setNome("Michele");
        studente.setCognome("Gianbruzzi");
        studente.setIndirizzo("via Napoli, 10");
        return studente;
    }

    @Bean("computer")
    public Computer getComputer() {
        Computer computer = new Computer();
        computer.setNome("XL2");
        computer.setMarca("Hp");
        computer.setCpu("Intel");
        computer.setMonitor(27);
        computer.setRam(16);
        return computer;
    }

    @Bean("smartphone")
    public Smartphone getSmarthphone() {
        Smartphone smartphone = new Smartphone();
        smartphone.setNome("Slider12");
        smartphone.setMarca("Samsung");
        smartphone.setSchermo(7);
        return smartphone;
    }

//    @Bean
//    public Aula getAula() {
//        Aula aula = new Aula();
//        aula.setNome("2N");
//        aula.setStudenti(List.of(getStudente(),getStudente2()));
//        return aula;
//    }
}


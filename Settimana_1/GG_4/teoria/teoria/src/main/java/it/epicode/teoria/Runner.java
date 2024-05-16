package it.epicode.teoria;

import it.epicode.teoria.bean.Computer;
import it.epicode.teoria.bean.Smartphone;
import it.epicode.teoria.bean.Studente;
import it.epicode.teoria.service.DispositivoService;
import it.epicode.teoria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    // referenziamo i service che ci servono
    @Autowired
    private StudenteService studenteService;
    @Autowired
    private DispositivoService dispositivoService;

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TeoriaApplication.class);

//        Studente studente1 = ctx.getBean("Carmelo", Studente.class);
//        studenteService.inserisciStudente(studente1);
//
//        Studente studente2 = ctx.getBean("Michele", Studente.class);
//        studenteService.inserisciStudente(studente2);
//
//        Computer computer = ctx.getBean(Computer.class);
//        computer.setStudente(studente1);  //setto lo studente al pc e non il contrario poiché il lato proprietario è computer
//        dispositivoService.inserisciDispositivo(computer);
//
//        Smartphone smartphone = ctx.getBean(Smartphone.class);
//        smartphone.setStudente(studente2);
//        dispositivoService.inserisciDispositivo(smartphone);

        System.out.println(studenteService.getStudentiByNome("Michele"));
        System.out.println(studenteService.getStudentiByPartialNome("%o%"));

        System.out.println(dispositivoService.getComputerByRamLessThan(20));

        System.out.println(dispositivoService.getDispositiviOrderByNome());

    }
}

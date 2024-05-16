package it.epicode.teoria.bean;

import it.epicode.teoria.TeoriaApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TeoriaApplication.class);

        Aula aula = ctx.getBean(Aula.class);

        Dispositivo dispositivo = ctx.getBean("computer", Dispositivo.class);
        System.out.println();
        System.out.println(dispositivo);

        Scuola scuola = ctx.getBean(Scuola.class);

        System.out.println();
        System.out.println(scuola);

        System.out.println();
		System.out.println(aula);
    }
}

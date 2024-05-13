package it.epicode.progettoSpring;

import it.epicode.progettoSpring.appConfig.AppConfig;
import it.epicode.progettoSpring.bean.Aula;
import it.epicode.progettoSpring.bean.Studente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProgettoSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProgettoSpringApplication.class, args);

		// accediamo al contesto di Spring
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		// recuperiamo il bean creato da Spring
		Studente studente = ctx.getBean(Studente.class);

		// richiedo un altro oggetto non facendo nulla di che
		Studente studente2 = ctx.getBean(Studente.class);

		studente.setCognome("Verdi");
		System.out.println(studente);
		System.out.println("----------------");
		System.out.println(studente2);

		Aula aula = ctx.getBean(Aula.class);
		System.out.println("\n" + aula);




	}
}

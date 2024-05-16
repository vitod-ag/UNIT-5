package it.nextdevs.esercizio;

import it.nextdevs.esercizio.bean.Pizza;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class EsercizioApplicationTests {

	static ApplicationContext ctx;

	@BeforeAll
	public static void accediAlContesto() {
		ctx = new AnnotationConfigApplicationContext(EsercizioApplication.class);
		System.out.println("Accesso avvenuto");
	}

	@Test
	void verificaNomePizza() {
		Pizza pizza = ctx.getBean("Salami", Pizza.class);
		Assertions.assertEquals("Salami",pizza.getNome());
		System.out.println("Verifica nome pizza");
	}

	@AfterAll
	public static void chiudiContesto() {
		ctx = null;
		System.out.println("Contesto chiuso");
	}

}

package it.nextdevs.esercizio;

import it.nextdevs.esercizio.bean.Pizza;
import it.nextdevs.esercizio.bean.Tavolo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class EsercizioApplicationTests {

	private static ApplicationContext ctx;

	@BeforeAll
	public static void accediAlContesto() {
		ctx = new AnnotationConfigApplicationContext(EsercizioApplication.class);
		System.out.println("Accesso avvenuto");
	}

	@Test
	void verificaCostoCopertoTavolo1() {
		Tavolo tavolo = ctx.getBean("tavolo1", Tavolo.class);
		Assertions.assertEquals(5, tavolo.getCostoCoperto());
	}

	@ParameterizedTest
	@CsvSource({"tavolo1,5", "tavolo2,2.5", "tavolo3,3.7"})
	public void verificaCostoCopertoPerTavoli(String tavolo, Double coperto) {
		Tavolo t = ctx.getBean(tavolo, Tavolo.class); // prendo il Bean associato
		Assertions.assertEquals(coperto, t.getCostoCoperto());
	}

	@AfterAll
	public static void chiudiContesto() {
		ctx = null;
		System.out.println("Contesto chiuso");
	}

}

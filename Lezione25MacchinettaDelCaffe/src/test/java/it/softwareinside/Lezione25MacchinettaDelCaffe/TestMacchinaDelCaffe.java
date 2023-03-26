package it.softwareinside.Lezione25MacchinettaDelCaffe;

import org.junit.Test;

import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Bevanda;
import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Ingredienti;
import it.softwareinside.Lezione25MacchinettaDelCaffe.models.MacchinaDelCaffe;
import it.softwareinside.Lezione25MacchinettaDelCaffe.models.MacchinaDelCaffeRicaricaAutomatica;
import junit.framework.TestCase;

public class TestMacchinaDelCaffe extends TestCase {
	@Test
	public void testIngredienti() {
		Bevanda bevanda = new Bevanda("cappuccino", 120, 2, 3, 1, 3);
		Ingredienti ingredienti = new Ingredienti();
		assertTrue(ingredienti.ingredientiSufficienti(bevanda));
		ingredienti.consumaBevanda(bevanda);
		ingredienti.consumaBevanda(bevanda);
		assertTrue(ingredienti.ingredientiSufficienti(bevanda));
		ingredienti.consumaBevanda(bevanda);
		assertFalse(ingredienti.ingredientiSufficienti(bevanda));
	}

	@Test
	public void testAggiungiBevanda() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		Bevanda b1 = new Bevanda("cappuccino", 120, 2, 3, 1, 2);
		assertTrue(m.aggiuntaBevanda(b1));
		Bevanda b2 = new Bevanda("cappuccino", 122, 2, 3, 1, 2);
		assertFalse(m.aggiuntaBevanda(b2));
	}

	public void testModificaBevanda() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		Bevanda b1 = new Bevanda("cappuccino", 120, 2, 3, 1, 2);
		assertTrue(m.aggiuntaBevanda(b1));
		Bevanda b2 = new Bevanda("cappuccino", 122, 2, 3, 1, 2);
		assertTrue(m.modificaBevanda(b2));
		Bevanda b3 = new Bevanda("espresso", 30, 3, 0, 0, 2);
		assertFalse(m.modificaBevanda(b3));
	}

	@Test
	public void testCreditoUtente() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		assertEquals(0, m.getCreditoUtente());
		m.aggiungiCredito(-10);
		assertEquals(0, m.getCreditoUtente());
		m.aggiungiCredito(50);
		assertEquals(50, m.getCreditoUtente());
		assertEquals(50, m.restituisciCreditoUtente());
		assertEquals(0, m.getCreditoUtente());
	}

	@Test
	public void testBevandaNonEsistente() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		m.aggiungiCredito(200);
		m.aggiuntaBevanda(new Bevanda("cappuccino", 120, 2, 3, 1, 2));
		try {
			assertFalse(m.eroga("espresso"));
		} catch (/* RisorseInsufficientiException */Exception e) {
			fail();
		}
	}

	@Test
	public void testCreditoIndufficiente() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		m.aggiungiCredito(10);
		m.aggiuntaBevanda(new Bevanda("cappuccino", 120, 2, 3, 1, 2));
		try {
			assertFalse(m.eroga("cappuccino"));
		} catch (/* RisorseInsufficientiException */Exception e) {
			fail();
		}
	}

	@Test
	public void testIngredientiInsufficienti() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		m.aggiungiCredito(200);
		m.aggiuntaBevanda(new Bevanda("cappuccino", 120, 11, 3, 1, 2));
		try {
			m.eroga("cappuccino");
		} catch (/* RisorseInsufficientiException */Exception e) {
		}
	}

//	public void testQuestionario() {
//		MacchinaDelCaffeRicaricaAutomatica m = new MacchinaDelCaffeRicaricaAutomatica();
//		m.aggiungiCredito(1000);
//		m.aggiuntaBevanda(new Bevanda("cappuccino", 120, 9, 3, 1, 2));
//		m.aggiuntaBevanda(new Bevanda("marocchino", 120, 2, 3, 1, 2));
//		m.aggiuntaBevanda(new Bevanda("caffellatte", 120, 2, 3, 1, 2));
//		m.aggiuntaBevanda(new Bevanda("espresso", 120, 2, 3, 1, 2));
//		m.stampaTutteBevande();
//		assertTrue(m.eroga("cappuccino"));
//		System.out.println(m.toString());
//
//	}
	@Test
	public void testAggiuntivo() {
		MacchinaDelCaffe m = new MacchinaDelCaffe();
		assertTrue(m.aggiuntaBevanda(new Bevanda("cappuccino", 120, 3, 3, 1, 2)));
		assertTrue(m.aggiuntaBevanda(new Bevanda("marocchino", 120, 2, 3, 1, 2)));
		m.aggiungiCredito(240);
		assertTrue(m.eroga("cappuccino"));
		assertTrue(m.eroga("marocchino"));
		assertTrue(m.aggiuntaBevanda(new Bevanda("marocchino", 120, 2, 3, 1, 2)));
		assertFalse(m.eroga("marocchino"));
	}
}
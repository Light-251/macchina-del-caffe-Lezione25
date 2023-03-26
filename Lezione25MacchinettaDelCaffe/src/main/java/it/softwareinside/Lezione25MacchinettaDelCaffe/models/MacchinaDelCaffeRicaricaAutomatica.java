package it.softwareinside.Lezione25MacchinettaDelCaffe.models;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity
public class MacchinaDelCaffeRicaricaAutomatica extends MacchinaDelCaffe {
	private final int MAX_RICARICHE = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int numRicariche;

	public MacchinaDelCaffeRicaricaAutomatica() {
		setNumRicariche(MAX_RICARICHE);
		setCreditoUtente(0);
		this.bevande = new ArrayList<Bevanda>();
		this.ingredienti = new Ingredienti();
	}

	@Override
	public boolean eroga(String nomeBevanda) {
		Bevanda bevandaErogata = isBevandaPresent(nomeBevanda);
		if (super.eroga(nomeBevanda)) {
			if (!ingredienti.ingredientiSufficienti(bevandaErogata) && numRicariche > 0) {
				ingredienti.riempi();
				numRicariche--;
			}
			return true;
		}
		return false;
	}
}

package it.softwareinside.Lezione25MacchinettaDelCaffe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Bevanda;
import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Ingredienti;
import it.softwareinside.Lezione25MacchinettaDelCaffe.repository.IngredientiRepo;

@Service
public class IngredientiService {
	@Autowired
	IngredientiRepo ingredientiRepo;

	Ingredienti ingredienti = new Ingredienti();

	/**
	 * Ritorna gli ingredienti fornendo un id
	 * 
	 * @param id
	 * @return
	 */
	public String getIngredientiById(int id) {
		try {
			return ingredientiRepo.findById(id).get().toString();
		} catch (Exception e) {
			return "ERRORE: " + e;
		}
	}

	/**
	 * Controlla se gli ingredienti attuali sono sufficienti per 
	 * erogare una bevanda passata come parametro
	 * 
	 * @param bevanda
	 * @return
	 */
	public boolean ingredientiSufficienti(Bevanda bevanda) {
		return ingredienti.ingredientiSufficienti(bevanda);
	}

	/**
	 * Aggiunge ingredienti al DB
	 * 
	 * @param ingredienti
	 * @return
	 */
	public boolean addIngredienti(Ingredienti ingredienti) {
		try {
			ingredientiRepo.save(ingredienti);
			return true;
		} catch (Exception e) {
			System.err.println("ERRORE AGGIUNTA INGREDIENTI " + e);
			return false;
		}
	}

	public boolean updateIngredienti(Ingredienti ingredienti) {
		return addIngredienti(ingredienti);
	}

	/**
	 * Rimuovi ingredienti by id
	 * 
	 * @param id
	 * @return
	 */
	public Ingredienti removeIngredientiById(int id) {
		try {
			Ingredienti ingredientiTmp = ingredientiRepo.findById(id).get();
			ingredientiRepo.deleteById(id);
			return ingredientiTmp;
		} catch (Exception e) {
			System.err.println("ERRORE RIMOZIONE INGREDIENTE BY ID " + e);
			return null;
		}
	}

	// rimuovi tutto
	public boolean deleteAllIngredienti() {
		try {
			ingredientiRepo.deleteAll();
			return true;
		} catch (Exception e) {
			System.err.println("ERRORE RIMOZIONE TUTTI INGREDIENTI " + e);
			return false;
		}
	}

}

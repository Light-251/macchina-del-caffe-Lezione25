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

    public String getIngredientiById(int id) {
        Ingredienti ingredienti = ingredientiRepo.findById(id).get();
        try {
            return ingredienti.toString();
        } catch (Exception e) {
            return "ERRORE: " + e;
        }
    }

     public boolean ingredientiSufficienti(Bevanda bevanda) {
        return ingredienti.ingredientiSufficienti(bevanda);
    } 
    
    
    
}

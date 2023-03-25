package it.softwareinside.Lezione25MacchinettaDelCaffe.repository;

import org.springframework.data.repository.CrudRepository;

import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Ingredienti;

public interface IngredientiRepo extends CrudRepository<Ingredienti,Integer> {
    
}

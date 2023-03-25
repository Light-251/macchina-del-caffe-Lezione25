package it.softwareinside.Lezione25MacchinettaDelCaffe.repository;

import org.springframework.data.repository.CrudRepository;

import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Bevanda;

public interface BevandaRepo extends CrudRepository<Bevanda, String> {

}

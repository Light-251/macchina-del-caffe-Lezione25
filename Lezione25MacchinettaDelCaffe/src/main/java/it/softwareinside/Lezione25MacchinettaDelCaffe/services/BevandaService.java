package it.softwareinside.Lezione25MacchinettaDelCaffe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.softwareinside.Lezione25MacchinettaDelCaffe.repository.BevandaRepo;

@Service
public class BevandaService {
    @Autowired
    BevandaRepo bevandaRepo;

    
}

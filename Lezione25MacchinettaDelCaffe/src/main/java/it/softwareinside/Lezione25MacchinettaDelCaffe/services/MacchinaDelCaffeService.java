package it.softwareinside.Lezione25MacchinettaDelCaffe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.softwareinside.Lezione25MacchinettaDelCaffe.repository.MacchinaDelcaffeRepo;

@Service
public class MacchinaDelCaffeService {
    @Autowired
    MacchinaDelcaffeRepo macchinaDelcaffeRepo;
}

package it.softwareinside.Lezione25MacchinettaDelCaffe.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.softwareinside.Lezione25MacchinettaDelCaffe.models.Bevanda;
import it.softwareinside.Lezione25MacchinettaDelCaffe.services.IngredientiService;

@RequestMapping("/api/ingredienti")
@RestController
public class IngredientiRestController {

    @Autowired
    IngredientiService ingredientiService;

    @GetMapping("/list")
    public String getIngredienti(@RequestParam("id") int id) {
        return ingredientiService.getIngredientiById(id);
    }
    
    @PostMapping("/sufficienti")
    public boolean ingredientiSufficienti(@RequestBody Bevanda bevanda) {
    	return ingredientiService.ingredientiSufficienti(bevanda);
    }

}

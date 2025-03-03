package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Nourriture;

import com.example.pidevbackendproject.services.INourritureService;

import com.example.pidevbackendproject.services.NourritureImpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = " Gestion Nouriture des joueur exercice")
@RestController
@AllArgsConstructor
@RequestMapping("/Nouriture")
public class NourritureRestController {

INourritureService nourritureService;
    @Operation(description = "Ajouter un nouriture")
    @PostMapping("/add-nouriture")
    public Nourriture addNourriture(@RequestBody Nourriture nn) {
        return nourritureService.addNourriture(nn);
    }

    @Operation(description = "récupérer toutes les nouriture de la base de données")
    @GetMapping(value = "/retrieve-all-nouriture")
    public List<Nourriture> getAllNourriture() {
        List<Nourriture> nour= nourritureService.getAllNourriture();
        return nour;
    }

    @Operation(description = "récupérer les nouriture de la base de données by ID")
    @GetMapping("/retrieve-nouriture/{nouriture-id}")
    public Nourriture retrieveNourriture(@PathVariable("nouriture-id") int idnoi) {
        Nourriture nou = nourritureService.getNourritureById(idnoi);
        return nou;
    }

    @Operation(description = "Supprimer nouriture by ID")
    @DeleteMapping("/remove-nouriture/{nouriture-id}")
    public void deleteNourriture(@PathVariable("nouriture-id") int idnou) {
        nourritureService.deleteNourriture(idnou);
    }

    @Operation(description = "Modifer nouriture")
    @PutMapping("/modify-nouriture")
    public Nourriture modifyNourriture(@RequestBody Nourriture nn) {
        Nourriture nourriture= nourritureService.modifyNourriture(nn);
        return nourriture;
    }
}

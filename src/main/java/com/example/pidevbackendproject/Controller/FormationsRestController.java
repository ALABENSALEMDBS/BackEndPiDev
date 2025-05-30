//**
package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.services.IFormationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Gestion de Formations")
@RestController
@AllArgsConstructor
@RequestMapping("/Formations")
public class FormationsRestController {
    IFormationsService formationsService;

    @Operation(description = "Ajouter Un Formation")
    @PostMapping("/add-formations")
    public Formations addFormations(@RequestBody Formations formation) {
        return formationsService.addFormations(formation);
    }

    @Operation(description = "Récupérer toutes les formations de la base de données")
    @GetMapping(value = "/retrieve-all-formation")
    public List<Formations> getAllFormations() {
        List<Formations> formation= formationsService.getAllFormations();
        return formation;
    }

    @Operation(description = "Récupérer les formations de la Base de données by ID")
    @GetMapping("/retrieve-formations/{formations-id}")
    public Formations retrieveFormations(@PathVariable("formations-id") int idFormations) {
        Formations formation = formationsService.getFormationsById(idFormations);
        return formation;
    }

    @Operation(description = "Supprimer Formation By ID")
    @DeleteMapping("/remove-formation/{formation-id}")
    public void deleteFormations(@PathVariable("formation-id") int idFormations) {
        formationsService.deleteFormations(idFormations);
    }

    @Operation(description = "Modifier une Formation")
    @PutMapping("/modify-formations/{idFormation}")
    public Formations modifyFormations(@PathVariable ("idFormation") int idFormation, @RequestBody Formations form) {
        return formationsService.modifyFormations(idFormation, form);
    }




    @Operation(description = "Affecter joueur a formation 11")
    @PostMapping("/affecter-JoueurFormation/{joueur-id}/{formation-id}")
    public void affecterJoueurAFormation(@PathVariable("joueur-id") int numjoueur, @PathVariable("formation-id") int idFormation) {
        formationsService.affecterJoueurAFormation(numjoueur,idFormation);
    }



    @Operation(description = "Affecter tactic a formation 11")
    @PostMapping("/affecter-TacticFormation/{tactic-id}/{formation-id}")
    public void affecterTacticAFormation(@PathVariable("tactic-id") int idTactic, @PathVariable("formation-id") int idFormation) {
        formationsService.affecterTacticAFormation(idTactic,idFormation);
    }



    @Operation(description = "Récupérer les joueurs dans un formation")
    @GetMapping("/retrieve-joueurs/{formations-id}")
    public Set<Joueurs> getJoueursInFormation(@PathVariable("formations-id") int idFormations) {
           Set<Joueurs> joueurs = formationsService.getJoueursInFormation(idFormations);
           return joueurs;
    }


    @PostMapping("/affecter-joueursAFormation/{idFormation}")
    public ResponseEntity<Formations> affecterJoueursAFormation(
            @PathVariable ("idFormation") int idFormation,
            @RequestBody List<Integer> joueursIds) {
        Formations formation = formationsService.affecterJoueursAFormation(idFormation, joueursIds);
        return ResponseEntity.ok(formation);
    }

    @Operation(description = "Desaffecter joueur a un formation")
    @PostMapping("/desaffecterJoueurAFormation/{idFormation}/{idJoueur}")
    public void desaffecterJoueurAFormation(@PathVariable("idFormation") int idFormation, @PathVariable("idJoueur") int idJoueur) {
        formationsService.desaffecterJoueurAFormation(idFormation, idJoueur);
    }



    @GetMapping("/compatible-formations")
    public List<Formations> getCompatibleFormations() {
        return formationsService.getAllFormationsWith11PlayersAndCompatiblePositions();
    }

    }


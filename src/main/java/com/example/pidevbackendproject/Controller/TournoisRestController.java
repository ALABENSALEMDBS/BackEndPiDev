package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Tournois;
import com.example.pidevbackendproject.services.ITournoisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Tournois")
@RestController
@AllArgsConstructor
@RequestMapping("/tournois")
public class TournoisRestController {
    ITournoisService tournoisService;

    @Operation(description = "Ajouter un tournoi")
    @PostMapping("/add-tournois")
    public Tournois addTournois(@RequestBody Tournois tr) {
        return tournoisService.addTournois(tr);
    }

    @Operation(description = "récupérer toutes les tournois de la base de données")
    @GetMapping(value = "/retrieve-all-tournois")
    public List<Tournois> getAllTournois() {
        List<Tournois> tournois= tournoisService.getAllTournois();
        return tournois;
    }

    @Operation(description = "récupérer les tournois de la base de données by ID")
    @GetMapping("/retrieve-tournois/{tournois-id}")
    public Tournois retrieveTournois(@PathVariable("tournois-id") int idTournois) {
        Tournois tournois = tournoisService.getTournoisById(idTournois);
        return tournois;
    }

    @Operation(description = "Supprimer tournois by ID")
    @DeleteMapping("/remove-tournois/{tournois-id}")
    public void deleteTournois(@PathVariable("tournois-id") int idTournois) {
        tournoisService.deleteTournois(idTournois);
    }

    @Operation(description = "Modifer tournois")
    @PutMapping("/modify-tournois/{idtournoi}")
    public Tournois modifyTournois(@PathVariable ("idtournoi") int idTournoi, @RequestBody Tournois tr) {
        Tournois tournois= tournoisService.modifyTournois(idTournoi ,tr);
        return tournois;
    }


    @Operation(description = "Affecter match a tournoi")
    @PostMapping("/affecter-matchTournoi/{match-id}/{tournoi-id}")
    public void affecterTacticAFormation(@PathVariable("match-id") int idMatch, @PathVariable("tournoi-id") int idTournoi) {
        tournoisService.affeterMatchATournois(idMatch, idTournoi);
    }
}

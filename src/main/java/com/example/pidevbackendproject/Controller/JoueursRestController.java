package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.services.IJoueursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Joueurs")
@RestController
@AllArgsConstructor
@RequestMapping("/joueurs")
public class JoueursRestController {
    IJoueursService joueursService;

    @Operation(description = "Ajouter un joueur")
    @PostMapping("/add-joueurs")
    public Joueurs addJoueurs(@RequestBody Joueurs j) {
        return joueursService.addJoueurs(j);
    }

    @Operation(description = "récupérer toutes les joueurs de la base de données")
    @GetMapping(value = "/retrieve-all-joueurs")
    public List<Joueurs> getAllJoueur() {
        List<Joueurs> joueur= joueursService.getAllJoueurs();
        return joueur;
    }

    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/retrieve-joueurs/{joueurs-id}")
    public Joueurs retrieveJoueurs(@PathVariable("joueurs-id") int idJoueurs) {
        Joueurs joueur = joueursService.getJoueursById(idJoueurs);
        return joueur;
    }

    @Operation(description = "Supprimer joueurs by ID")
    @DeleteMapping("/remove-joueurs/{joueurs-id}")
    public void deleteJoueurs(@PathVariable("joueurs-id") int idJoueurs) {
        joueursService.deleteJoueurs(idJoueurs);
    }

    @Operation(description = "Modifer joueurs")
    @PutMapping("/modify-joueurs")
    public Joueurs modifyJoueurs(@RequestBody Joueurs j) {
        Joueurs joueur= joueursService.modifyJoueurs(j);
        return joueur;
    }

    @Operation(description = "récupérer toutes les joueurs without fiche de la base de données")
    @GetMapping(value = "/retrieve-all-joueurs-without-fiche")
    public List<Joueurs> getAllJoueurWithoutFiche() {
        List<Joueurs> joueur= joueursService.findJoueursWithoutFicheMedicale();
        return joueur;
    }
}

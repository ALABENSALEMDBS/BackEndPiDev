package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.services.IJoueursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Joueurs")
@RestController
@AllArgsConstructor
@RequestMapping("/joueurs")
public class JoueursRestController {
    IJoueursService joueursService;



    /*
    @GetMapping("joueur-role")
    @PreAuthorize("hasRole('PLAYER')")
    public ResponseEntity<String> test() {
        System.out.println("beeetttttoniii daviiid");
        return ResponseEntity.status(HttpStatus.OK).body("Coaaaaaaach");
    }
     */

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

    @Operation(description = "récupérer toutes les joueurs sans groupe de la base de données")
    @GetMapping(value = "/retrieve-joueurs-Withoutsousgroups")

    public List<Joueurs> getJoueurWithoutsousgroups() {
        List<Joueurs> joueur= joueursService.getJoueurWithoutsousgroups();
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
    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/findJoueursByNumeroJoueur/{numeroJoueur}")
    public Joueurs findJoueursByNumeroJoueur(@PathVariable("numeroJoueur") int numeroJoueur) {
        return joueursService.findJoueursByNumeroJoueur(numeroJoueur);

    }
    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/findJoueursByidRapportnumeroJoueur/{idRapport}/{numeroJoueur}")
    public Rapports findJoueursRapports(@PathVariable("idRapport") int idRapport, @PathVariable("numeroJoueur") int numeroJoueur) {
        return joueursService.findJoueursRapports(idRapport,numeroJoueur);

    }

    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/findJoueursRapportsnumeroJoueurposteJoueur/{numeroJoueur}/{posteJoueur}")
    List<Rapports> findJoueursRapportsnumeroJoueurposteJoueur(@Param("numeroJoueur") int numeroJoueur, @Param("posteJoueur") String posteJoueur) {
        return joueursService.findJoueursRapportsnumeroJoueurposteJoueur(numeroJoueur,posteJoueur);

    }

    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/findJoueursBynameSousGroup/{nameSousGroup}")
    public List<Joueurs> findJoueursBynameSousGroup(@PathVariable("nameSousGroup") String nameSousGroup) {
        return joueursService.findJoueursBynameSousGroup(nameSousGroup);

    }
    @Operation(description = "récupérer les joueurs de la base de données by ID")
    @GetMapping("/findSousGroupesJoueurs/{idExercice}")
    public List<Joueurs> findSousGroupesJoueurs(@PathVariable("nameSousGroup") int idExercice) {
        return joueursService.findSousGroupesJoueurs(idExercice);

    }


    @PutMapping("/maktitulaire/{idjoueur}")
    public void makeTituliare(@PathVariable ("idjoueur") int id) {
            joueursService.maktitulaire(id);
    }
}

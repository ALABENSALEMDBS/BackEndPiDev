package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.services.IRapportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Tag(name = "Gestion De Rapports")
@RestController
@AllArgsConstructor
@RequestMapping("/rapports")
public class RapportsRestController {
    IRapportsService rapportsService;

    @Operation(description = "Ajouter un rapports")
    @PostMapping("/addRapports/{numeroJoueur}/{nameSousGroup}/{titleSeance}")
    public void addRapports(@RequestBody Rapports rapport,@PathVariable("numeroJoueur") int numeroJoueur,@PathVariable("nameSousGroup") String nameSousGroup,@PathVariable("titleSeance") String titleSeance) {
        rapportsService.addRapports(rapport,numeroJoueur,nameSousGroup,titleSeance);
    }

    @Operation(description = "récupérer toutes les rapports de la base de données")
    @GetMapping(value = "/retrieve-all-rapports")
    public List<Rapports> getAllRapports() {
        List<Rapports> rapports= rapportsService.getAllRapports();
        return rapports;
    }

    @Operation(description = "récupérer les rapports de la base de données by ID")
    @GetMapping("/retrieve-rapports/{rapports-id}")
    public Rapports retrieveRapports(@PathVariable("rapports-id") int idRapports) {
        Rapports rapports = rapportsService.getRapportsById(idRapports);
        return rapports;
    }

    @Operation(description = "récupérer les rapports de la base de données by ID")
    @GetMapping("/findByDateRapport/{date}")
    public List<Rapports> findByDateRapport(@PathVariable("date") LocalDate date) {
        return rapportsService.findByDateRapport(date);
    }

    @GetMapping("/show/{numeroJoueur}")
    public List<Rapports> findRapportByNumeroJoueur(@PathVariable int numeroJoueur) {
        return rapportsService.findRapportByNumeroJoueur(numeroJoueur);
    }


    @GetMapping("findRapportBySousGroupe/{nameSousGroup}")
    public List<Rapports> findRapportBySousGroupe(@PathVariable String nameSousGroup) {
        return rapportsService.findRapportBySousGroupe(nameSousGroup);
    }
    @GetMapping("findRapportBytitleSeance/{titleSeance}")
    public List<Rapports> findRapportBytitleSeance(@PathVariable String titleSeance) {
        return rapportsService.findRapportBytitleSeance(titleSeance);
    }

    @Operation(description = "Supprimer rapports by ID")
    @DeleteMapping("/remove-rapports/{rapports-id}")
    public void deleteRapports(@PathVariable("rapports-id") int idRapports) {
        rapportsService.deleteRapports(idRapports);
    }

    @Operation(description = "Modifer rapports")
    @PutMapping("/modify-rapports/{rapports-id}")
    public Rapports modifyRapports(@RequestBody Rapports rapp,@PathVariable("rapports-id") int idRapports) {
        Rapports rapport= rapportsService.modifyRapports(idRapports, rapp);
        return rapport;
    }
    @GetMapping("/meilleur/par-poste-et-seance")
    public Rapports getBestPlayerByPosteAndSeance(@RequestParam String posteJoueur,
                                                  @RequestParam String titleSeance) {
        return rapportsService.getBestPlayerByPosteAndSeance(posteJoueur, titleSeance);
    }

    // 2. Joueur le plus performant par titre de séance uniquement
    @GetMapping("/meilleur/par-seance")
    public  List<Rapports> getBestPlayerBySeance(@RequestParam String titleSeance) {
        return rapportsService.getBestPlayerBySeance(titleSeance);
    }

    // 3. Joueur le plus performant par poste, séance, et sous-groupe
    @GetMapping("/meilleur/par-poste-seance-sousgroupe")
    public Rapports getBestPlayerByPosteSeanceAndSousGroupe(@RequestParam String posteJoueur,
                                                            @RequestParam String titleSeance,
                                                            @RequestParam String nomSousGroupe) {
        return rapportsService.getBestPlayerByPosteSeanceAndSousGroupe(posteJoueur, titleSeance, nomSousGroupe);
    }

    @GetMapping("/bad/poste/{poste}")
    public List<Rapports> getBadReportsByPoste(@PathVariable String poste) {
        return rapportsService.getBadReportsByPoste(poste);
    }

    @GetMapping("/similar")
    public List<Rapports> getSimilarRapports(
            @RequestParam String poste,
            @RequestParam int numero
    ) {
        return rapportsService.getSimilarRapportsByPosteAndPerformance(poste, numero);
    }

    @GetMapping("/suspect/good/{poste}")
    public List<Rapports> getRapportsSuspectEtatGood(@PathVariable String poste) {
        return  rapportsService.detecterRapportsSuspectMaisEtatGood(poste);
    }
}

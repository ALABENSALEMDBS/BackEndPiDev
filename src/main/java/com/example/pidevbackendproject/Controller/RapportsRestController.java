package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.services.IRapportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion De Rapports")
@RestController
@AllArgsConstructor
@RequestMapping("/rapports")
public class RapportsRestController {
    IRapportsService rapportsService;

    @Operation(description = "Ajouter un rapports")
    @PostMapping("/addRapports/{numeroJoueur}")
    public void addRapports(@RequestBody Rapports rapport,@PathVariable("numeroJoueur") int numeroJoueur) {
        rapportsService.addRapports(rapport,numeroJoueur);
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
    @GetMapping("/getRapportsByJoueur/{numeroJoueur}")
    public List<Rapports> getRapportsByJoueur(@PathVariable("numeroJoueur") int numeroJoueur) {
        return rapportsService.getRapportsByJoueur(numeroJoueur);

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



}

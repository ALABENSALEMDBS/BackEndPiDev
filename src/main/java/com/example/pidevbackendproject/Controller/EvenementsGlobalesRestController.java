package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.EvenementsGlobales;
import com.example.pidevbackendproject.services.IEvenementsGlobalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion des Evenements Globale")
@RestController
@AllArgsConstructor
@RequestMapping("/EvenementsGlobales")
public class EvenementsGlobalesRestController {
    IEvenementsGlobalesService evenementsGlobalesService;

    @Operation(description = "Ajouter un Evenement Globale")
    @PostMapping("/add-evenementsGlobales")
    public EvenementsGlobales addEvenementsGlobales(@RequestBody EvenementsGlobales eg) {
        return evenementsGlobalesService.addEvenementsGlobales(eg);
    }

    @Operation(description = "récupérer toutes les Evenements Globale de la base de données")
    @GetMapping(value = "/retrieve-all-evenementsGlobales")
    public List<EvenementsGlobales> getAllEvenementsGlobales() {
        List<EvenementsGlobales> evenementsGlobale= evenementsGlobalesService.getAllEvenementsGlobales();
        return evenementsGlobale;
    }

    @Operation(description = "récupérer les Evenements Globale de la base de données by ID")
    @GetMapping("/retrieve-evenementsGlobales/{evenementsGlobales-id}")
    public EvenementsGlobales retrieveEvenementsGlobales(@PathVariable("evenementsGlobales-id") int idEvenementsGlobales) {
        EvenementsGlobales evenementsGlobale = evenementsGlobalesService.getEvenementsGlobalesById(idEvenementsGlobales);
        return evenementsGlobale;
    }

    @Operation(description = "Supprimer evenements Globales by ID")
    @DeleteMapping("/remove-evenementsGlobales/{evenementsGlobales-id}")
    public void deleteEvenementsGlobales(@PathVariable("evenementsGlobales-id") int idEvenementsGlobales) {
        evenementsGlobalesService.deleteEvenementsGlobales(idEvenementsGlobales);
    }

    @Operation(description = "Modifer evenements Globales")
    @PutMapping("/modify-evenementsGlobales")
    public EvenementsGlobales modifyEvenementsGlobales(@RequestBody EvenementsGlobales eg) {
        EvenementsGlobales evenementsGlobale= evenementsGlobalesService.modifyEvenementsGlobales(eg);
        return evenementsGlobale;
    }
}

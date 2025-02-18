package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.services.IFormationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion de Formations")
@RestController
@AllArgsConstructor
@RequestMapping("/Formations")
public class FormationsRestController {
    IFormationsService formationsService;

    @Operation(description = "Ajouter un Formation")
    @PostMapping("/add-formations")
    public Formations addFormations(@RequestBody Formations formation) {
        return formationsService.addFormations(formation);
    }

    @Operation(description = "récupérer toutes les formations de la base de données")
    @GetMapping(value = "/retrieve-all-formation")
    public List<Formations> getAllFormations() {
        List<Formations> formation= formationsService.getAllFormations();
        return formation;
    }

    @Operation(description = "récupérer les formations de la base de données by ID")
    @GetMapping("/retrieve-formations/{formations-id}")
    public Formations retrieveFormations(@PathVariable("formations-id") int idFormations) {
        Formations formation = formationsService.getFormationsById(idFormations);
        return formation;
    }

    @Operation(description = "Supprimer formation by ID")
    @DeleteMapping("/remove-formation/{formation-id}")
    public void deleteFormations(@PathVariable("formation-id") int idFormations) {
        formationsService.deleteFormations(idFormations);
    }

    @Operation(description = "Modifer formations")
    @PutMapping("/modify-formations")
    public Formations modifyFormations(@RequestBody Formations form) {
        Formations formation= formationsService.modifyFormations(form);
        return formation;
    }
}

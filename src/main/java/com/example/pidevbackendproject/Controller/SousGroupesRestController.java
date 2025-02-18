package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.services.ISousGroupesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion SousGroupes")
@RestController
@AllArgsConstructor
@RequestMapping("/sousGroupes")
public class SousGroupesRestController {
    ISousGroupesService sousGroupesService;

    @Operation(description = "Ajouter un Sous Groupe")
    @PostMapping("/add-sousGroupes")
    public SousGroupes addSousGroupes(@RequestBody SousGroupes sg) {
        return sousGroupesService.addSousGroupes(sg);
    }

    @Operation(description = "récupérer toutes les Sous Groupes de la base de données")
    @GetMapping(value = "/retrieve-all-sousGroupes")
    public List<SousGroupes> getAllSousGroupes() {
        List<SousGroupes> sousGroupes= sousGroupesService.getAllSousGroupes();
        return sousGroupes;
    }

    @Operation(description = "récupérer les Sous Groupes de la base de données by ID")
    @GetMapping("/retrieve-sousGroupes/{sousGroupes-id}")
    public SousGroupes retrieveSousGroupes(@PathVariable("sousGroupes-id") int idSousGroupes) {
        SousGroupes sousGroupes = sousGroupesService.getSousGroupesById(idSousGroupes);
        return sousGroupes;
    }

    @Operation(description = "Supprimer Sous Groupes by ID")
    @DeleteMapping("/remove-sousGroupes/{sousGroupes-id}")
    public void deleteSousGroupes(@PathVariable("sousGroupes-id") int idSousGroupes) {
        sousGroupesService.deleteSousGroupes(idSousGroupes);
    }

    @Operation(description = "Modifer Sous Groupes")
    @PutMapping("/modify-sousGroupes")
    public SousGroupes modifySousGroupes(@RequestBody SousGroupes sg) {
        SousGroupes sousGroupes= sousGroupesService.modifySousGroupes(sg);
        return sousGroupes;
    }
}

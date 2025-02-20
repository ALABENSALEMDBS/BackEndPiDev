//**
package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Tactics;
import com.example.pidevbackendproject.services.ITacticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Tactics")
@RestController
@AllArgsConstructor
@RequestMapping("/tactics")
public class TacticsRestController {
    ITacticsService tacticsService;
    @Operation(description = "Ajouter un Tactic")
    @PostMapping("/add-tactics")
    public Tactics addTactics(@RequestBody Tactics t) {
        return tacticsService.addTactics(t);
    }

    @Operation(description = "Récupérer toutes les tactics de la base de données")
    @GetMapping(value = "/retrieve-all-tactics")
    public List<Tactics> getAllTactics() {
        List<Tactics> tactics= tacticsService.getAllTactics();
        return tactics;
    }

    @Operation(description = "Récupérer les tactics de la base de données by ID")
    @GetMapping("/retrieve-tactics/{tactics-id}")
    public Tactics retrieveTactics(@PathVariable("tactics-id") int idTactics) {
        Tactics tactics = tacticsService.getTacticsById(idTactics);
        return tactics;
    }

    @Operation(description = "Supprimer Tactics by ID")
    @DeleteMapping("/remove-tactics/{tactics-id}")
    public void deleteTactics(@PathVariable("tactics-id") int idTactics) {
        tacticsService.deleteTactics(idTactics);
    }

    @Operation(description = "Modifer Tactics")
    @PutMapping("/modify-tactics")
    public Tactics modifyTactics(@RequestBody Tactics t) {
        Tactics tactics= tacticsService.modifyTactics(t);
        return tactics;
    }
}

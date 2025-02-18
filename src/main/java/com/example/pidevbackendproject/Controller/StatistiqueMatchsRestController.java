package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.StatistiqueMatchs;
import com.example.pidevbackendproject.services.IStatistiqueMatchsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Statistique de Matchs")
@RestController
@AllArgsConstructor
@RequestMapping("/statistiqueMatchs")
public class StatistiqueMatchsRestController {
    IStatistiqueMatchsService statistiqueMatchsService;

    @Operation(description = "Ajouter un Statistique de Matchs")
    @PostMapping("/add- statistiqueMatchs")
    public StatistiqueMatchs addStatistiqueMatchs(@RequestBody  StatistiqueMatchs statam) {
        return statistiqueMatchsService.addStatistiqueMatchs(statam);
    }

    @Operation(description = "récupérer toutes les Statistiques des Matchs de la base de données")
    @GetMapping(value = "/retrieve-all-StatistiqueMatchs")
    public List<StatistiqueMatchs> getAllStatistiqueMatchs() {
        List<StatistiqueMatchs> statistiqueMatchs= statistiqueMatchsService.getAllStatistiqueMatchs();
        return statistiqueMatchs;
    }

    @Operation(description = "récupérer les Statistique Matchs de la base de données by ID")
    @GetMapping("/retrieve-statistiqueMatchs/{statistiqueMatchs-id}")
    public  StatistiqueMatchs retrieveStatistiqueMatchs(@PathVariable("statistiqueMatchs-id") int idStatistiqueMatchs) {
        StatistiqueMatchs statistiqueMatchs = statistiqueMatchsService.getStatistiqueMatchsById(idStatistiqueMatchs);
        return statistiqueMatchs;
    }

    @Operation(description = "Supprimer Statistique Matchs by ID")
    @DeleteMapping("/remove-statistiqueMatchs/{statistiqueMatchs-id}")
    public void deleteStatistiqueMatchs(@PathVariable("statistiqueMatchs-id") int idStatistiqueMatchs) {
        statistiqueMatchsService.deleteStatistiqueMatchs(idStatistiqueMatchs);
    }

    @Operation(description = "Modifer Statistique Matchs")
    @PutMapping("/modify-club")
    public StatistiqueMatchs modifyStatistiqueMatchs(@RequestBody StatistiqueMatchs statm) {
        StatistiqueMatchs statistiqueMatchs= statistiqueMatchsService.modifyStatistiqueMatchs(statm);
        return statistiqueMatchs;
    }
}

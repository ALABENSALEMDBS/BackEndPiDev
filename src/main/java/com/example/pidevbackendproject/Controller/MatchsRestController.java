package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.services.IMatchsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion des Matchs")
@RestController
@AllArgsConstructor
@RequestMapping("/matchs")
public class MatchsRestController {
    IMatchsService matchsService;

    @Operation(description = "Ajouter un Match")
    @PostMapping("/add-matchs")
    public Matchs addMatchs(@RequestBody Matchs m) {
        return matchsService.addMatchs(m);
    }

    @Operation(description = "récupérer toutes les Matchs de la base de données")
    @GetMapping(value = "/retrieve-all-Matchs")
    public List<Matchs> getAllMatchs() {
        List<Matchs> matchs= matchsService.getAllMatchs();
        return matchs;
    }

    @Operation(description = "récupérer les Matchs de la base de données by ID")
    @GetMapping("/retrieve-matchs/{matchs-id}")
    public Matchs retrieveMatchs(@PathVariable("matchs-id") int idMatchs) {
        Matchs matchs = matchsService.getMatchsById(idMatchs);
        return matchs;
    }

    @Operation(description = "Supprimer matchs by ID")
    @DeleteMapping("/remove-matchs/{matchs-id}")
    public void deleteMatchs(@PathVariable("matchs-id") int idMatchs) {
        matchsService.deleteMatchs(idMatchs);
    }

    @Operation(description = "Modifer Matchs")
    @PutMapping("/modify-matchs/{matchs-id}")
    public Matchs modifyMatchs(@RequestBody Matchs mat,@PathVariable("matchs-id") int idMatchs) {
        Matchs matchs= matchsService.modifyMatchs(idMatchs,mat);
        return matchs;
    }

}

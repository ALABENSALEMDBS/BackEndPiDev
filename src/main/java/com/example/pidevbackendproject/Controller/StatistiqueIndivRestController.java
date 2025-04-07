package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.StatistiqueIndiv;
import com.example.pidevbackendproject.services.IStatistiqueIndivService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Gestion Statistique Indiv")
@RestController
@AllArgsConstructor
@RequestMapping("/statistiqueIndiv")
public class StatistiqueIndivRestController {
    IStatistiqueIndivService statistiqueIndivService;

    @Operation(description = "Ajouter un Statistique Indiv")
    @PostMapping("/add-statistiqueIndiv")
    public StatistiqueIndiv addStatistiqueIndiv(@RequestBody StatistiqueIndiv statistiqueIndiv) {
        return statistiqueIndivService.addStatistiqueIndiv(statistiqueIndiv);
    }

    @Operation(description = "récupérer toutes les statistiques indiv de la base de données")
    @GetMapping(value = "/retrieve-all-statistiqueIndiv")
    public List<StatistiqueIndiv> getAllStatistiqueIndiv() {
        List<StatistiqueIndiv> statistiqueIndivs= statistiqueIndivService.getAllStatistiqueIndiv();
        return statistiqueIndivs;
    }

    @Operation(description = "récupérer les statistique indiv de la base de données by ID")
    @GetMapping("/retrieve-statistiqueIndiv/{statistiqueIndiv-id}")
    public StatistiqueIndiv retrieveStatistiqueIndiv(@PathVariable("statistiqueIndiv-id") int idStatistiqueIndiv) {
        StatistiqueIndiv statistiqueIndiv = statistiqueIndivService.getStatistiqueIndivById(idStatistiqueIndiv);
        return statistiqueIndiv;
    }

    @Operation(description = "Supprimer Statistique Indiv by ID")
    @DeleteMapping("/remove-statistiqueIndiv/{statistiqueIndiv-id}")
    public void deleteStatistiqueIndiv(@PathVariable("statistiqueIndiv-id") int idStatistiqueIndiv) {
        statistiqueIndivService.deleteStatistiqueIndiv(idStatistiqueIndiv);
    }

    @Operation(description = "Modifer Statistique Indiv")
    @PutMapping("/modify-StatistiqueIndiv")
    public StatistiqueIndiv modifyStatistiqueIndiv(@RequestBody StatistiqueIndiv stat) {
        StatistiqueIndiv statistiqueIndiv= statistiqueIndivService.modifyStatistiqueIndiv(stat);
        return statistiqueIndiv;
    }


    @GetMapping("/getStatistiqueIndivByJoueurNumero/{numeroJoueur}")
    public List <StatistiqueIndiv> getStatistiqueIndivByJoueurNumero(@PathVariable ("numeroJoueur") int numeroJoueur) {
        return statistiqueIndivService.getStatistiqueIndivByJoueurNumero(numeroJoueur);
    }
}

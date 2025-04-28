package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.services.ISeancesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion de Seances")
@RestController
@AllArgsConstructor
@RequestMapping("/seances")
public class SeancesRestController {
    ISeancesService seancesService;

    @Operation(description = "Ajouter un seance")
    @PostMapping("/add-seances")
    public Seances addSeances(@RequestBody Seances seance) {
        return seancesService.addSeances(seance);
    }

    @Operation(description = "récupérer toutes les seances de la base de données")
    @GetMapping(value = "/retrieve-all-seances")
    public List<Seances> getAllSeances() {
        List<Seances> seances= seancesService.getAllSeances();
        return seances;
    }

    @Operation(description = "récupérer les seances de la base de données by ID")
    @GetMapping("/retrieve-seances/{seances-id}")
    public Seances retrieveSeances(@PathVariable("seances-id") int idSeances) {
        Seances seances = seancesService.getSeancesById(idSeances);
        return seances;
    }

    @Operation(description = "Supprimer Seances by ID")
    @DeleteMapping("/remove-seances/{seances-id}")
    public void deleteSeances(@PathVariable("seances-id") int idSeances) {
        seancesService.deleteSeances(idSeances);
    }

    @Operation(description = "Modifer seances")
    @PutMapping("/modify-seances/{seances-id}")
    public Seances modifySeances(@PathVariable("seances-id") int idSeances,@RequestBody Seances sean) {
       return seancesService.modifySeances(idSeances,sean);

    }
    @Operation(description = "affecter un exercice a seance")
    @PostMapping("/affecter-exerciseaseance/{seances-id}/{exerciceIds}")
    public void affecterexerciseaseance(@PathVariable("seances-id") int idSeances,@PathVariable("exerciceIds") List<Integer> exerciceIds) {
        seancesService.affecterMultipleExercisesToSeance(idSeances,exerciceIds);
    }
    @Operation(description = "Supprimer Sous Groupes by ID")
    @GetMapping("/findSousGroupesJoueurs/{idExercice}")
    public List<Seances> findSousGroupesJoueurs(@PathVariable("idExercice") int idExercice) {
        return  seancesService.findSousGroupesJoueurs(idExercice);
    }

    @Operation(description = "Supprimer Sous Groupes by ID")
    @PostMapping("/desafecterMultipleExercisesToSeance/{idExercice}/{idSeance}")
    public void desafecterMultipleExercisesToSeance(@PathVariable("idExercice") int idExercice,@PathVariable("idSeance") int idSeance) {
        seancesService.desafecterMultipleExercisesToSeance(idExercice,idSeance);
    }

}

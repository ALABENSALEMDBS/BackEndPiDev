package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.services.IExercicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Exercices")
@RestController
@AllArgsConstructor
@RequestMapping("/Exercices")
public class ExercicesRestController {
    IExercicesService exercicesService;

    @Operation(description = "Ajouter un Exercices")
    @PostMapping("/add-exercices")
    public Exercices addExercices(@RequestBody Exercices ex) {
        return exercicesService.addExercices(ex);
    }

    @Operation(description = "récupérer toutes les exercices de la base de données")
    @GetMapping(value = "/retrieve-all-exercices")
    public List<Exercices> getAllExercices() {
        List<Exercices> exercice= exercicesService.getAllExercices();
        return exercice;
    }

    @Operation(description = "récupérer les exercices de la base de données by ID")
    @GetMapping("/retrieve-exercices/{exercices-id}")
    public Exercices retrieveExercices(@PathVariable("exercices-id") int idExercices) {
        Exercices exercice = exercicesService.getExercicesById(idExercices);
        return exercice;
    }

    @Operation(description = "Supprimer exercices by ID")
    @DeleteMapping("/remove-exercices/{exercices-id}")
    public void deleteExercices(@PathVariable("exercices-id") int idExercices) {
        exercicesService.deleteExercices(idExercices);
    }

    @Operation(description = "Modifer Exercices")
    @PutMapping("/modify-exercices")
    public Exercices modifyExercices(@RequestBody Exercices ex) {
        Exercices exercice= exercicesService.modifyExercices(ex);
        return exercice;
    }
}

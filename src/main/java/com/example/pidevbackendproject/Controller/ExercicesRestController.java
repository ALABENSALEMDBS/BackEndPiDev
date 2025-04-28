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
    @PutMapping("/modify-exercices/{exercices-id}")
    public Exercices modifyExercices(@RequestBody Exercices ex,@PathVariable("exercices-id") int idExercices){
        Exercices exercice= exercicesService.modifyExercices(idExercices,ex);
        return exercice;
    }
    @Operation(description = "Modifer Exercices")
    @GetMapping("/findBySeanceExerciceIdSeance/{seanceId}")
    public List<Exercices> findBySeanceExerciceIdSeance(@PathVariable("seanceId") int seanceId){
        return exercicesService.findBySeanceExerciceIdSeance(seanceId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    @Operation(description = "Modifer Exercices")
    @GetMapping("/findBySeanceExercice_IdSeance/{seanceId}")
    public List<Exercices> findBySeanceExercice_IdSeance(@PathVariable("seanceId") int seanceId){
        return exercicesService.findBySeanceExercice_IdSeance(seanceId);
    }
    @Operation(description = "Modifer Exercices")
    @GetMapping("/findExercicesWithNoSeance")
    public List<Exercices> findExercicesWithNoSeance(){
        return exercicesService.findExercicesWithNoSeance();
    }
    @Operation(description = "Modifer Exercices")
    @GetMapping("/findExercicesAssignedToOtherSeances/{seanceId}")
    public List<Exercices> findExercicesAssignedToOtherSeances(@PathVariable("seanceId") int seanceId){
        return exercicesService.findExercicesAssignedToOtherSeances(seanceId);
    }
    @Operation(description = "Modifer Exercices")
    @GetMapping("/findAvailableExercices/{seanceId}")
    public List<Exercices> findAvailableExercices(@PathVariable("seanceId") int seanceId){
        return exercicesService.findAvailableExercices(seanceId);
    }


    @GetMapping("/compatible/{seanceId}")
    public List<Exercices> getCompatibleExercices(@PathVariable int seanceId) {
        return exercicesService.getCompatibleExercisesForSeance(seanceId);
    }
}

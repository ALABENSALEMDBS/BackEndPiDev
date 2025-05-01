package com.example.pidevbackendproject.Controller;
import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import com.example.pidevbackendproject.services.IExerciceRetablissementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Gestion Exercice de Retablissement")
@RestController
@AllArgsConstructor
@RequestMapping("/ExerciceRetablissements")
public class ExerciceRetablissementsRestController {
    IExerciceRetablissementsService exerciceRetablissementsService;

    @Operation(description = "Ajouter un Exercice Retablissement")
    @PostMapping("/add-exerciceRetablissements")
    public ExerciceRetablissements addExerciceRetablissements(@RequestBody ExerciceRetablissements er) {
        return exerciceRetablissementsService.addExerciceRetablissements(er);
    }

    @Operation(description = "récupérer toutes les exercices de retablissement de la base de données")
    @GetMapping(value = "/retrieve-all-exerciceRetablissements")
    public List<ExerciceRetablissements> getAllExerciceRetablissements() {
        List<ExerciceRetablissements> exerciceRetablissement= exerciceRetablissementsService.getAllExerciceRetablissements();
        return exerciceRetablissement;
    }

    @Operation(description = "récupérer les exercices de retablissement de la base de données by ID")
    @GetMapping("/retrieve-exerciceRetablissements/{exerciceRetablissements-id}")
    public ExerciceRetablissements retrieveExerciceRetablissements(@PathVariable("exerciceRetablissements-id") int idExerciceRetablissements) {
        ExerciceRetablissements exerciceRetablissement = exerciceRetablissementsService.getExerciceRetablissementsById(idExerciceRetablissements);
        return exerciceRetablissement;
    }

    @Operation(description = "Supprimer Exercice Retablissements by ID")
    @DeleteMapping("/remove-exerciceRetablissements/{exerciceRetablissements-id}")
    public void deleteExerciceRetablissements(@PathVariable("exerciceRetablissements-id") int idExerciceRetablissements) {

        exerciceRetablissementsService.deleteExerciceRetablissements(idExerciceRetablissements);
    }

    @Operation(description = "Modifer ExerciceRetablissements")
    @PutMapping("/modify-exerciceRetablissements")
    public ExerciceRetablissements modifyExerciceRetablissements(@RequestBody ExerciceRetablissements er) {

        int id = er.getIdExerciceRetablissement();
        ExerciceRetablissements exerciceRetablissement= exerciceRetablissementsService.modifyExerciceRetablissements(er);
        return exerciceRetablissement;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("Le fichier est vide.");
        }

        // Traitez le fichier ici
        // Par exemple, sauvegardez-le sur le serveur ou analysez-le.

        return ResponseEntity.ok("Fichier téléchargé avec succès : " + file.getOriginalFilename());
    }


}

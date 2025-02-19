package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.EvenementInternes;
import com.example.pidevbackendproject.services.IEvenementInternesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion des Evenement Internes")
@RestController
@AllArgsConstructor
@RequestMapping("/EvenementInternes")
public class EvenementInternesRestController {
    IEvenementInternesService evenementInternesService;

    @Operation(description = "Ajouter un Evenement Interne")
    @PostMapping("/add-EvenementInternes")
    public EvenementInternes addEvenementInternes(@RequestBody EvenementInternes e) {
        return evenementInternesService.addEvenementInternes(e);
    }

    @Operation(description = "récupérer toutes les Evenements Interne de la base de données")
    @GetMapping(value = "/retrieve-all-EvenementsInterne")
    public List<EvenementInternes> getAllEvenementInternes() {
        List<EvenementInternes> evenementInterne= evenementInternesService.getAllEvenementInternes();
        return evenementInterne;
    }

    @Operation(description = "récupérer les Evenements Interne de la base de données by ID")
    @GetMapping("/retrieve-EvenementInternes/{evenementInternes-id}")
    public EvenementInternes retrieveEvenementInternes(@PathVariable("evenementInternes-id") int idEvenementInterne) {
        EvenementInternes evenementInterne = evenementInternesService.getEvenementInternesById(idEvenementInterne);
        return evenementInterne;
    }

    @Operation(description = "Supprimer Evenement Interne by ID")
    @DeleteMapping("/remove-evenementInternes/{evenementInternes-id}")
    public void deleteClubs(@PathVariable("evenementInternes-id") int idEvenementInternes) {

        evenementInternesService.deleteEvenementInternes(idEvenementInternes);
    }

    @Operation(description = "Modifer Evenement Interne")
    @PutMapping("/modify-evenementInternes")
    public EvenementInternes modifyEvenementInternes(@RequestBody EvenementInternes e) {
        EvenementInternes evenementInterne= evenementInternesService.modifyEvenementInternes(e);
        return evenementInterne;
    }
}

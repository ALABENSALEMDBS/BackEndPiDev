package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
import com.example.pidevbackendproject.entities.FicheMedicales;
import com.example.pidevbackendproject.services.IFicheMedicalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Gestion Fiche Medicales")
@RestController
@AllArgsConstructor
@RequestMapping("/FicheMedicales")
public class FicheMedicalesRestController {
    IFicheMedicalesService ficheMedicalesService;


    //ubdate methode creation :
    @PostMapping("/{name}/{prenom}/{idexrcice}")
    public ResponseEntity<FicheMedicales> createFicheMedicale(
            @PathVariable int idexrcice,
            @PathVariable String name,
            @PathVariable String prenom,
            @RequestBody FicheMedicales ficheMedicales) {
        FicheMedicales createdFiche = ficheMedicalesService.createFicheMedicale(idexrcice,name, prenom, ficheMedicales);
        return ResponseEntity.ok(createdFiche);
    }

    @Operation(description = "Ajouter une Fiche Medicale")
    @PostMapping("/add-FicheMedicales/{idPlayer}/{idexrcice}")
    public FicheMedicales addFicheMedicales(@RequestBody FicheMedicales fm, @PathVariable int idPlayer,
            @PathVariable int idexrcice) {
        return ficheMedicalesService.addFicheMedicales(fm, idPlayer, idexrcice);
    }

   /* @PostMapping("/addfichebyplayer/{idPlayer}")
    public FicheMedicales addFicheMedicalByIdJoueurs( @RequestBody FicheMedicales ficheMedicales,  @PathVariable int idPlayer) {
        return ficheMedicalesService.addFicheMedicalByIdJoueurs(ficheMedicales,idPlayer);
    }*/

   /* @PostMapping("/ajouter/{joueurId}")
    public ResponseEntity<FicheMedicales> ajouterFicheMedicale(
            @PathVariable int joueurId,
            @RequestBody FicheMedicales ficheMedical) {
        System.out.println("ID Joueur reçu: " + joueurId);  // Vérifier l'ID
        FicheMedicales nouvelleFiche = ficheMedicalesService.addFicheMedicalByIdJoueurs(joueurId, ficheMedical);
        return ResponseEntity.ok(nouvelleFiche);
    }*/

    @Operation(description = "récupérer toutes les Fiches Medicales de la base de données")
    @GetMapping(value = "/retrieve-all-ficheMedicales")
    public List<ficheMedicaleDto> getAllFicheMedicales() {
            List<ficheMedicaleDto> ficheMedicale= ficheMedicalesService.findAllWithJoueurFullName();
        return ficheMedicale;
    }

    @Operation(description = "récupérer les Fiches Medicale de la base de données by ID")
    @GetMapping("/retrieve-ficheMedicales/{ficheMedicales-id}")
    public ficheMedicaleDto retrieveFicheMedicales(@PathVariable("ficheMedicales-id") int idFicheMedicales) {
        FicheMedicales ficheMedicales = ficheMedicalesService.getFicheMedicalesById(idFicheMedicales);

        ficheMedicaleDto ficheMedicaleDto = new ficheMedicaleDto(
                ficheMedicales.getIdFicheMedicale(),
                ficheMedicales.getPoidsFicheMedicale(),
                ficheMedicales.getTailleFicheMedicale(),
                ficheMedicales.getDateBlessure(),
                ficheMedicales.getGravite(),
                ficheMedicales.getType(),
                ficheMedicales.getJoueurficheMedicale() == null
                        ? null
                        : ficheMedicales.getJoueurficheMedicale().getIdUser(),
                ficheMedicales.getExerciceRetablissements() == null
                        ? 0
                        : ficheMedicales.getExerciceRetablissements().getIdExerciceRetablissement()
        );

        return ficheMedicaleDto;
    }

    @Operation(description = "Supprimer Fiche Medicale by ID")
    @DeleteMapping("/remove-ficheMedicales/{ficheMedicales-id}")
    public void deleteFicheMedicales(@PathVariable("ficheMedicales-id") int idFicheMedicales) {
        ficheMedicalesService.deleteFicheMedicales(idFicheMedicales);
    }

    @Operation(description = "Modifer Fiche Medicales")
    @PutMapping("/modify-ficheMedicales/{idex}")
    public FicheMedicales modifyFicheMedicales(
                                               @RequestBody FicheMedicales fm ,@PathVariable int idex) {
        FicheMedicales ficheMedicale= ficheMedicalesService.modifyFicheMedicales(fm,idex);
        return ficheMedicale;
    }

    @GetMapping("/count-by-gravite")
    public List<Object[]> getCountByGravite() {
        return ficheMedicalesService.countInjuredPlayersByGravite();
    }

}




package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.FicheMedicales;
import com.example.pidevbackendproject.services.IFicheMedicalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Fiche Medicales")
@RestController
@AllArgsConstructor
@RequestMapping("/FicheMedicales")
public class FicheMedicalesRestController {
    IFicheMedicalesService ficheMedicalesService;

    @Operation(description = "Ajouter une Fiche Medicale")
    @PostMapping("/add-FicheMedicales")
    public FicheMedicales addFicheMedicales(@RequestBody FicheMedicales fm) {
        return ficheMedicalesService.addFicheMedicales(fm);
    }

    @Operation(description = "récupérer toutes les Fiches Medicales de la base de données")
    @GetMapping(value = "/retrieve-all-ficheMedicales")
    public List<FicheMedicales> getAllFicheMedicales() {
        List<FicheMedicales> ficheMedicale= ficheMedicalesService.getAllFicheMedicales();
        return ficheMedicale;
    }

    @Operation(description = "récupérer les Fiches Medicale de la base de données by ID")
    @GetMapping("/retrieve-ficheMedicales/{ficheMedicales-id}")
    public FicheMedicales retrieveFicheMedicales(@PathVariable("ficheMedicales-id") int idFicheMedicales) {
        FicheMedicales ficheMedicales = ficheMedicalesService.getFicheMedicalesById(idFicheMedicales);
        return ficheMedicales;
    }

    @Operation(description = "Supprimer Fiche Medicale by ID")
    @DeleteMapping("/remove-ficheMedicales/{ficheMedicales-id}")
    public void deleteFicheMedicales(@PathVariable("ficheMedicales-id") int idFicheMedicales) {
        ficheMedicalesService.deleteFicheMedicales(idFicheMedicales);
    }

    @Operation(description = "Modifer Fiche Medicales")
    @PutMapping("/modify-ficheMedicales")
    public FicheMedicales modifyFicheMedicales(@RequestBody FicheMedicales fm) {
        FicheMedicales ficheMedicale= ficheMedicalesService.modifyFicheMedicales(fm);
        return ficheMedicale;
    }
}

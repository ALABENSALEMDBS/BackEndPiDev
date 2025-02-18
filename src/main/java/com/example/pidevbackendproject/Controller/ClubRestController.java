package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.services.IClubsServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Clubs")
@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubRestController {
    IClubsServise clubsServise;

    @Operation(description = "Ajouter un Club")
    @PostMapping("/add-club")
    public Clubs addClub(@RequestBody Clubs c) {
        //Clubs club= clubsServise.addClubs(c);
        return clubsServise.addClubs(c);
    }

    @Operation(description = "récupérer toutes les club de la base de données")
    @GetMapping(value = "/retrieve-all-club")
    public List<Clubs> getAllClubs() {
        List<Clubs> listClub= clubsServise.getAllClubs();
        return listClub;
    }

    @Operation(description = "récupérer les club de la base de données by ID")
    @GetMapping("/retrieve-club/{club-id}")
    public Clubs retrieveClub(@PathVariable("club-id") int idClub) {
        Clubs club = clubsServise.getClubsById(idClub);
        return club;
    }

    @Operation(description = "Supprimer club by ID")
    @DeleteMapping("/remove-club/{club-id}")
    public void deleteClubs(@PathVariable("club-id") int idClub) {

        clubsServise.deleteClubs(idClub);
    }

    @Operation(description = "Modifer club")
    @PutMapping("/modify-club")
    public Clubs modifyClub(@RequestBody Clubs c) {
        Clubs club= clubsServise.modifyClubs(c);
        return club;
    }
}


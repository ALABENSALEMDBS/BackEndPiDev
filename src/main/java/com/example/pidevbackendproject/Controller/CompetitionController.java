package com.example.pidevbackendproject.Controller;


//import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.CompetitionRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.services.CompetitionImplService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion des Matchs")
@RestController
@AllArgsConstructor
@RequestMapping("/competition")
public class CompetitionController {

    private final MatchsRepo matchsRepo;
    CompetitionRepo competitionRepo;
    CompetitionImplService competitionServise;



    @GetMapping("allCompetition")
    public ResponseEntity<List<Competition>> getCompetitions() {
        List<Competition> competitionList = competitionRepo.findAll();
        return ResponseEntity.ok(competitionList);
    }



    @Operation(description = "Add a Competition")
    @PostMapping("/add-Competition")
    public Competition addClub(@RequestBody Competition c) {
        //Clubs club= clubsServise.addClubs(c);
        return competitionServise.addCompetition(c);
    }


    @GetMapping(value = "/retrieve-all-competition")
    public List<Competition> getAllCompetition() {
        return competitionServise.getAllCompetition();
    }

    @Operation(description = "récupérer les club de la base de données by ID")
    @GetMapping("/retrieve-competition/{competition-id}")
    public Competition retrieveCompetition(@PathVariable("competition-id") int idCompetition) {
        return competitionRepo.getById(idCompetition);
    }

    @Operation(description = "Delete Competition by Id")
    @DeleteMapping("/remove-club/{competition-id}")
    public void deleteCompetitions(@PathVariable("competition-id") int idCompetition) {
        competitionServise.deleteCompetition(idCompetition);
    }

    @Operation(description = "Modifer club")
    @PutMapping("/modify-club")
    public Competition modifyClub(@RequestBody Competition c) {
        return competitionServise.modifyCompetition(c);
    }


    @PostMapping("/{idCompetition}/affecter-match/{idMatch}")
    public ResponseEntity<String> affecterMatchACompetition(@PathVariable int idCompetition, @PathVariable int idMatch) {
        competitionServise.assignMatchToCompetition(idMatch, idCompetition);
        return ResponseEntity.ok("Match assigned to competition " + idCompetition);
    }


    @GetMapping("/getMatchsOfCompetition/{idMatch}")
    public ResponseEntity<List<Matchs>> getMatchsOfCompetition(@PathVariable int idCompetition) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(matchsRepo.MatchsOfCompetition(idCompetition));
    }



    
}

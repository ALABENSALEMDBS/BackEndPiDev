package com.example.pidevbackendproject.Controller;


//import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.TypeCompetition;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CompetitionRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.services.CompetitionImplService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Gestion des Matchs")
@RestController
@AllArgsConstructor
@RequestMapping("/competition")
public class CompetitionController {

    private final MatchsRepo matchsRepo;
    CompetitionRepo competitionRepo;
    CompetitionImplService competitionServise;
    ClubsRepo clubsRepo;


    @GetMapping("allCompetition")
    public ResponseEntity<List<Competition>> getCompetitions() {
        List<Competition> competitionList = competitionRepo.findAll();
        return ResponseEntity.ok(competitionList);
    }


    /*@Operation(description = "Add a Competition")
    @PostMapping("/add-Competition")
    public Competition addClub(@RequestBody Competition c) {
        //Clubs club= clubsServise.addClubs(c);
        return competitionServise.addCompetition(c);
    }*/


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


    /*@PostMapping("/{idCompetition}/affecter-match/{idMatch}")
    public ResponseEntity<String> affecterMatchACompetition(@PathVariable int idCompetition, @PathVariable int idMatch) {
        competitionServise.assignMatchToCompetition(idMatch, idCompetition);
        return ResponseEntity.ok("Match assigned to competition " + idCompetition);
    }*/


    /*@GetMapping("/getMatchsOfCompetition/{idCompetition}")
    public ResponseEntity<List<Matchs>> getMatchsOfCompetition(@PathVariable int idCompetition) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(matchsRepo.MatchsOfCompetition(idCompetition));
    }*/


    @GetMapping("/getMatchsOfCompetition/{idCompetition}")
    public ResponseEntity<List<Matchs>> getMatchsOfCompetition(@PathVariable int idCompetition) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(matchsRepo.MatchsOfCompetition(idCompetition));
    }


    @GetMapping("/getParticipatedClubsNames/{idCompetition}")
    public ResponseEntity<List<String>> getParticipatedClubsNames(@PathVariable int idCompetition) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        Set<Clubs> partClubs = competitionServise.ClubsOfCompetition(idCompetition);
        List<String> clubsName = partClubs.stream().map(Clubs::getNameClub).toList();
        return ResponseEntity.ok(clubsName);
    }


    @GetMapping("/getParticipatedClubs/{idCompetition}")
    public ResponseEntity<Set<Clubs>> getParticipatedClubs(@PathVariable int idCompetition) {
        Set<Clubs> partClubs = competitionServise.ClubsOfCompetition(idCompetition);
        //List<String> clubsName = partClubs.stream().map(Clubs::getNameClub).toList();
        return ResponseEntity.ok(partClubs);
    }


    /*@PostMapping("/{idCompetition}/affecter-matches")
    public ResponseEntity<String> assignMatchesToCompetition(
            @PathVariable int idCompetition,
            @RequestBody List<Integer> matchIds) {
        try {
            competitionServise.assignManyMatchesToCompetition(matchIds, idCompetition);
            return ResponseEntity.ok("Matches successfully assigned to competition " + idCompetition);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }*/


    @PostMapping("/createCompetition")
    public ResponseEntity<String> createCompetition(@RequestBody Map<String, Object> requestData) {
        String name = (String) requestData.get("nameCompetition");
        String typeString = (String) requestData.get("typeC");
        List<Integer> clubIds = (List<Integer>) requestData.get("clubIds");

        // Convert string to enum
        TypeCompetition type = TypeCompetition.valueOf(typeString.toUpperCase());

        // Load clubs from DB
        List<Clubs> clubs = clubsRepo.findAllById(
                clubIds.stream().map(Integer::valueOf).toList()
        );

        // Create and save competition
        Competition competition = Competition.builder()
                .nameCompetition(name)
                .TypeC(type)
                .build();
        competition = competitionRepo.save(competition);
        // Generate all match combinations
        Set<Matchs> matches = new HashSet<>();
        for (int i = 0; i < clubs.size(); i++) {
            for (int j = i + 1; j < clubs.size(); j++) {
                Matchs match = Matchs.builder()
                        .club1(clubs.get(i))
                        .club2(clubs.get(j))
                        .competition(competition)
                        .build();
                matches.add(match);
            }
        }
        // Save all matches
        matchsRepo.saveAll(matches);
        return ResponseEntity.ok("Competition and matches created successfully.");
    }

}
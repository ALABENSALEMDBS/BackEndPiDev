package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Cup;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CupRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.services.CupImplService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Gestion des Coupes")
@RestController
@AllArgsConstructor
@RequestMapping("/cup")
public class CupController {


    private final ClubsRepo clubsRepo;
    private final CupRepo cupRepo;
    private final CupImplService cupService;
    MatchsRepo matchsRepo;


    @PostMapping("/createCup")
    public ResponseEntity<String> createCup(@RequestBody Map<String, Object> requestData) {
        String name = (String) requestData.get("name");
        List<Integer> clubIds = (List<Integer>) requestData.get("clubIds");
        return cupService.generateInitialMatches(name, clubIds);
    }


    @PostMapping("/next-round/{cupId}")
    public ResponseEntity<String> generateNextRound(@PathVariable Integer cupId) {
        return cupService.generateNextRoundMatches(cupId);
    }


    /*@GetMapping("/getParticipatingClubs/{idCup}")
    public ResponseEntity<List<Matchs>> getMatchsOfCup(@PathVariable int idCup) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(matchsRepo.matchsOfCup(idCup));
    }*/

    @GetMapping("/getParticipatingClubs/{idCup}")
    public ResponseEntity<Set<Clubs>> getClubsOfCup(@PathVariable int idCup) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(cupService.ClubsOfCup(idCup));
    }

    @GetMapping("/getCup/{idCup}")
    public ResponseEntity<Cup> getCupById(@PathVariable int idCup) {
        return ResponseEntity.ok(cupRepo.findById(idCup).get());
    }


    /*@GetMapping("/getAllCups")
    public ResponseEntity<List<Cup>> getAllCups() {
        return ResponseEntity.ok(cupRepo.findAll());
    }*/


    @GetMapping("/getAllCups")
    public ResponseEntity<List<Cup>> getAllCups() {
        List<Cup> cups = cupRepo.findAll();
        return ResponseEntity.ok().body(cups);
    }




    @GetMapping("/cups/{cupId}/matches-by-round")
    public ResponseEntity<Map<String, List<Matchs>>> getMatchesGroupedByRound(@PathVariable Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }


        Map<String, List<Matchs>> grouped = allMatches.stream()
                .filter(m -> m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Define logical round order
        List<String> roundOrder = List.of("Round 1", "Round 2", "Quarter Final", "Semi Final", "Final");

        // Sort rounds
        LinkedHashMap<String, List<Matchs>> sorted = new LinkedHashMap<>();
        for (String round : roundOrder) {
            if (grouped.containsKey(round)) {
                sorted.put(round, grouped.get(round));
            }
        }

        return ResponseEntity.ok(sorted);
    }

    @DeleteMapping("/deleteCup/{cupId}")
    public ResponseEntity<String> deleteCup(@PathVariable int cupId) {
        cupRepo.deleteById(cupId);
        return ResponseEntity.ok("Cup deleted.");
    }











    /*

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
    }*/
    
}




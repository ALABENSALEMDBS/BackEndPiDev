package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
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


    /*@PostMapping("/createCup")
    public ResponseEntity<String> createCup(@RequestBody Map<String, Object> requestData) {
        String name = (String) requestData.get("name");
        List<Integer> clubIds = (List<Integer>) requestData.get("clubIds");
        return cupService.generateInitialMatches(name, clubIds);
    }*/
    @PostMapping("/createCup")
    public ResponseEntity<Map<String, String>> createCup(@RequestBody Map<String, Object> requestData) {
        String name = (String) requestData.get("name");
        List<Integer> clubIds = (List<Integer>) requestData.get("clubIds");
        return cupService.generateInitialMatches(name, clubIds);
    }

    @PostMapping("/next-round/{cupId}")
    public ResponseEntity<Map<String, String>> generateNextRound(@PathVariable Integer cupId) {
        return cupService.generateNextRoundMatches(cupId);
    }


    @GetMapping("/getParticipatingClubs/{idCup}")
    public ResponseEntity<Set<Clubs>> getClubsOfCup(@PathVariable int idCup) {
        //return matchsRepo.MatchsOfCompetition(idCompetition);
        return ResponseEntity.ok(cupService.ClubsOfCup(idCup));
    }


    //mrigla
    @GetMapping("/getCup/{idCup}")
    public ResponseEntity<Cup> getCupById(@PathVariable int idCup) {
        return ResponseEntity.ok(cupRepo.findById(idCup).get());
    }

    @GetMapping("/getCupWinner/{idCup}")
    public Integer getCupWinnerId(@PathVariable int idCup) {
        Matchs finalMatch = matchsRepo.finalMatch(idCup);
        if (finalMatch != null && finalMatch.getWinner() != null) {
            return finalMatch.getWinner().getIdClub();
        }
        else
            return null;
    }


    @GetMapping("/getMatchesOfCup/{idCup}")
    public ResponseEntity<List<Matchs>> getMatchesOfCup(@PathVariable int idCup) {
        return ResponseEntity.ok(matchsRepo.matchsOfCup(idCup));
    }

    //mrigla
    @GetMapping("/getAllCups")
    public ResponseEntity<List<Cup>> getAllCups() {
        List<Cup> cups = cupRepo.findAll();
        return ResponseEntity.ok().body(cups);
    }

    //mrigla
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
        List<String> roundOrder = List.of("Round of ", "Quarter-Final", "Semi-Final", "Final");

        // Sort rounds
        LinkedHashMap<String, List<Matchs>> sorted = new LinkedHashMap<>();
        for (String round : roundOrder) {
            if (grouped.containsKey(round)) {
                sorted.put(round, grouped.get(round));
            }
        }
        return ResponseEntity.ok(sorted);
    }

    @PutMapping("/modify-cup/{cup-id}")
    public Cup modifyCompetition(@PathVariable("cup-id") int idCup , @RequestBody Cup cup) {
        Cup matchs= cupService.modifyCup(idCup, cup);
        return matchs;
    }


    //mrigla
    @DeleteMapping("/deleteCup/{cupId}")
    public void deleteCup(@PathVariable int cupId) {
        cupRepo.deleteById(cupId);
    }
    
}




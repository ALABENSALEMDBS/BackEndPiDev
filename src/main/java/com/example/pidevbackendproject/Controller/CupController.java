package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Cup;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CupRepo;
import com.example.pidevbackendproject.services.CupImplService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Gestion des Coupes")
@RestController
@AllArgsConstructor
@RequestMapping("/cup")
public class CupController {


    private final ClubsRepo clubsRepo;
    private final CupRepo cupRepo;
    private final CupImplService cupService;

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






    /*@GetMapping("/getMatchsOfCup/{idCup}")
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
    }*/














}




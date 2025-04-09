package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Standings;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.StandingsRepo;
import com.example.pidevbackendproject.services.StandingImplService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/standing")
public class StandingController {

    private final StandingsRepo standingsRepo;
    private final MatchsRepo matchsRepo;
    StandingImplService standingService;


    @PostMapping("/saveHoleInfos/{idCompetition}")
    public void SaveHoleInfos(@PathVariable int idCompetition) {
        standingService.saveStandingsData(idCompetition);
    }


    @PostMapping("/saveStandingsOfCompetition/{idCompetition}")
    public void SaveStandings(@PathVariable int idCompetition) {
        standingService.saveStandings(idCompetition);
    }

    @PostMapping("/saveStanding")
    public void SaveStanding(@RequestBody Standings standings) {
        standingsRepo.save(standings);
    }

    @GetMapping("/getAllStandings")
    public List<Standings> getAllStandings() {
        return standingsRepo.findAll();
    }

    @GetMapping("/goalsHome/{idCompetition}/{idClub}")
    public Integer goalsHome(@PathVariable int idCompetition , @PathVariable int idClub) {
        return standingService.goalsHome(idCompetition, idClub);
    }


    @GetMapping("/goalsAway/{idCompetition}/{idClub}")
    public Integer goalsAway(@PathVariable int idCompetition , @PathVariable int idClub) {
        return matchsRepo.goalsAway(idCompetition, idClub);
    }

    @GetMapping("/totalGoals/{idCompetition}/{idClub}")
    public Integer totalGoals(@PathVariable int idCompetition , @PathVariable int idClub) {
        return matchsRepo.totalGoals(idCompetition, idClub);
    }

    @GetMapping("/totalWins/{idCompetition}/{idClub}")
    public Integer totalWins(@PathVariable int idCompetition , @PathVariable int idClub) {
        return matchsRepo.totalWins(idCompetition, idClub);
    }


    @PostMapping("/givePoints/{idCompetition}")
    public void givePoints(@PathVariable int idCompetition) {
        standingService.givePoints(idCompetition);
    }






}

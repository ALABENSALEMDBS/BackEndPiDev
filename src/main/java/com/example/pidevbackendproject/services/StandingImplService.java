package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.Standings;
import com.example.pidevbackendproject.repositories.CompetitionRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.StandingsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StandingImplService {

    private final MatchsRepo matchsRepo;
    private final MatchsImpService matchsService;
    private final CompetitionImplService competitionService;
    private final CompetitionRepo competitionRepo;
    private final StandingsRepo standingsRepo;



    //public void saveT

    public void saveStandings(int idCompetition){
        Set<Clubs> particClubs = competitionService.ClubsOfCompetition(idCompetition);

        for (Clubs club : particClubs) {
            Standings standing = new Standings();
            standing.setClub(club);
            standing.setCompetition(competitionRepo.findById(idCompetition).get());
            standingsRepo.save(standing);
        }
    }


   /* public void saveStandingsData(int idCompetition){
        Set<Clubs> particClubs = competitionService.ClubsOfCompetition(idCompetition);

        for (Clubs club : particClubs) {
            Standings standing = new Standings();
            standing.setClub(club);
            Competition theCompetition = competitionRepo.findById(idCompetition).get();
            standing.setCompetition(theCompetition);
            int wins = matchsRepo.totalWins(club.getIdClub(), theCompetition.getIdCompetition());
            standing.setWins(wins);
            int draws = matchsRepo.totalDraws(club.getIdClub(), theCompetition.getIdCompetition());
            standing.setDraws(draws);
            standing.setMatchesPlayed(matchsRepo.playedMatchs(club.getIdClub(), theCompetition.getIdCompetition()));
            standing.setPoints(3*wins + draws);
            standing.setGoalsAgainst(matchsRepo.againstGoals(club.getIdClub(), theCompetition.getIdCompetition()));
            standing.setGoalsFor(matchsRepo.totalGoals(club.getIdClub(), theCompetition.getIdCompetition()));
            standing.setLosses(matchsRepo.playedMatchs(club.getIdClub(), theCompetition.getIdCompetition())-(wins+draws));
            standing.setGoalDifference(matchsRepo.totalGoals(club.getIdClub(), theCompetition.getIdCompetition())- matchsRepo.againstGoals(club.getIdClub(), theCompetition.getIdCompetition()));
            standingsRepo.save(standing);
        }
    }*/


    public void saveStandingsData(int idCompetition) {
        // Fetch competition once
        Competition theCompetition = competitionRepo.findById(idCompetition).get();
        Set<Clubs> particClubs = competitionService.ClubsOfCompetition(idCompetition);

        for (Clubs club : particClubs) {
            Standings standing = new Standings();
            standing.setClub(club);
            standing.setCompetition(theCompetition);

            // Fetch stats once for the current club and competition
            int wins = matchsRepo.totalWins(club.getIdClub(), idCompetition);
            int draws = matchsRepo.totalDraws(club.getIdClub(), idCompetition);
            int playedMatches = matchsRepo.playedMatchs(club.getIdClub(), idCompetition);
            int goalsFor = matchsRepo.totalGoals(club.getIdClub(), idCompetition);
            int goalsAgainst = matchsRepo.againstGoals(club.getIdClub(), idCompetition);

            // Set the fields
            standing.setWins(wins);
            standing.setDraws(draws);
            standing.setMatchesPlayed(playedMatches);
            standing.setPoints(3 * wins + draws);
            standing.setGoalsFor(goalsFor);
            standing.setGoalsAgainst(goalsAgainst);
            standing.setLosses(playedMatches - (wins + draws));
            standing.setGoalDifference(goalsFor - goalsAgainst);

            // Log data for debugging
            System.out.println("Club: " + club.getIdClub());



            System.out.println("Wins: " + wins);
            System.out.println("Draws: " + draws);
            System.out.println("Played Matches: " + playedMatches);
            System.out.println("Goals For: " + goalsFor);
            System.out.println("Goals Against: " + goalsAgainst);
            System.out.println("Losses: " + standing.getLosses());
            System.out.println("Goal Difference: " + standing.getGoalDifference());

            // Save standings data
            standingsRepo.save(standing);
        }
    }








    public void givePoints(Integer idCompetition) {
        Optional<Competition> competition = Optional.ofNullable(competitionRepo.findById(idCompetition).orElseThrow(() -> new RuntimeException("competition is not found")));
        for (Matchs match : matchsRepo.MatchsOfCompetition(idCompetition)) {
            if(match.getWinner()!=null) {
                //int winnerId = match.getWinner().getIdClub();
                Standings stand = standingsRepo.findByClub(match.getWinner());
                stand.setPoints(stand.getPoints()+3);
                standingsRepo.save(stand);
            }
            else {
                Standings stand1 = standingsRepo.findByClub(match.getClub1());
                stand1.setPoints(stand1.getPoints()+1);
                standingsRepo.save(stand1);
                Standings stand2 = standingsRepo.findByClub(match.getClub2());
                stand2.setPoints(stand2.getPoints()+1);
                standingsRepo.save(stand2);
            }
        }
    }

    public Integer goalsHome(int idCompetition , int idClub) {
        return matchsRepo.goalsHome(idCompetition, idClub);
    }


    /*public Integer numbWins(int idCompetition , int idClub) {

    }*/


}

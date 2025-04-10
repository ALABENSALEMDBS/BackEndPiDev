package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.Standings;
import com.example.pidevbackendproject.repositories.ClubsRepo;
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
    private final ClubsRepo clubsRepo;


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


    public void saveStandingsData(int idCompetition) {
        Competition theCompetition=competitionRepo.findById(idCompetition).get();
        Set<Clubs> particClubs=competitionService.ClubsOfCompetition(idCompetition);

        for (Clubs club : particClubs) {
            Standings standing;

            Optional<Standings> existing = standingsRepo.findByClubAndCompetition(club,theCompetition);

            if(existing.isPresent()){
                standing = existing.get();
            }
            else{
                standing = new Standings();
                standing.setClub(club);
                standing.setCompetition(theCompetition);
            }
            //if(!standingsRepo.existsByClubAndCompetition(club,theCompetition))


            int wins=matchsRepo.totalWins(idCompetition , club.getIdClub());
            int draws=matchsRepo.totalDraws(idCompetition , club.getIdClub());
            int playedMatches=matchsRepo.playedMatchs(idCompetition , club.getIdClub());
            int goalsFor=matchsRepo.totalGoals( idCompetition , club.getIdClub());
            int goalsAgainst=matchsRepo.againstGoals(idCompetition , club.getIdClub());


            standing.setWins(wins);
            standing.setDraws(draws);
            standing.setMatchesPlayed(playedMatches);
            standing.setPoints(3 * wins + draws);
            standing.setGoalsFor(goalsFor);
            standing.setGoalsAgainst(goalsAgainst);
            standing.setLosses(playedMatches - (wins + draws));
            standing.setGoalDifference(goalsFor - goalsAgainst);

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

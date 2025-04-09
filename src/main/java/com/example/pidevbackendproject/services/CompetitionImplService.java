package com.example.pidevbackendproject.services;


//import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.Standings;
import com.example.pidevbackendproject.repositories.CompetitionRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.StandingsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CompetitionImplService {

    private final StandingsRepo standingsRepo;
    CompetitionRepo competitionRepo;
    MatchsRepo matchsRepo;


    public Competition addCompetition(Competition competition) {
        return competitionRepo.save(competition);
    }

    public void deleteCompetition(int idCompetition) {
        competitionRepo.deleteById(idCompetition);
    }

    public Competition modifyCompetition(Competition competition) {
        return competitionRepo.save(competition);
    }

    public List<Competition> getAllCompetition() {
        List<Competition> competitions = competitionRepo.findAll();
        return competitions;
    }

    public Competition getClubsById(int idCompetition) {
        return competitionRepo.findById(idCompetition).get();
    }


    public void assignMatchToCompetition(int idMatch , int idCompetition) {
        Matchs match = matchsRepo.findById(idMatch).orElseThrow(() -> new RuntimeException(("Match is not found")));
        Competition competition = competitionRepo.findById(idCompetition).orElseThrow(() -> new RuntimeException(("Competition is not found")));
        match.setCompetition(competition);
        matchsRepo.save(match);
    }


    public List<Matchs> MatchsOfCompetition(int idCompetition) {
        return matchsRepo.MatchsOfCompetition(idCompetition);
    }


    //thisss is the function of getting the Clubs of each
    public Set<Clubs> ClubsOfCompetition(int idCompetition) {
        List<Matchs> ListMatchs = matchsRepo.MatchsOfCompetition(idCompetition);
        Set<Clubs> participatedClubs = new HashSet<>();
        for (Matchs match : ListMatchs) {
            Clubs club1 = match.getClub1();
            Clubs club2 = match.getClub2();
            participatedClubs.add(club1);
            participatedClubs.add(club2);
        }
        return participatedClubs;
    }


    /*public void givePoints(int idCompetition) {
        Competition competition = competitionRepo.findById(idCompetition).get();
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
    }*/




}

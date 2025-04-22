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


    public Competition modifyCompetition(int idCompetition, Competition competition) {
        Competition existingMatchs = competitionRepo.findById(idCompetition)
                .orElseThrow(() -> new RuntimeException("Competition non trouvé"));

        //existingMatchs.setResultatMatch(match.getResultatMatch());
        existingMatchs.setNameCompetition(competition.getNameCompetition());
        //existingMatchs.setTypeC(competition.getTypeC());

        return competitionRepo.save(existingMatchs);
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


    public void assignManyMatchesToCompetition(List<Integer> matchIds, int idCompetition) {
        Competition competition = competitionRepo.findById(idCompetition)
                .orElseThrow(() -> new RuntimeException("Competition is not found"));

        for (int idMatch : matchIds) {
            // Retrieve the match object for each match ID
            Matchs match = matchsRepo.findById(idMatch)
                    .orElseThrow(() -> new RuntimeException("Match with ID " + idMatch + " is not found"));
            match.setCompetition(competition);

            matchsRepo.save(match);
        }
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



    //cup competition:
    /*
    public void createRandomFirstRoundMatches(Competition competition) {
    Set<Club> clubs = competition.getClubs();  // Retrieve clubs from the competition

    List<Club> clubList = new ArrayList<>(clubs);
    Collections.shuffle(clubList); // Shuffle clubs to randomize the draw

    List<Match> roundMatches = new ArrayList<>();

    for (int i = 0; i < clubList.size(); i += 2) {
        Club club1 = clubList.get(i);
        Club club2 = clubList.get(i + 1);

        Match match = new Match();
        match.setCompetition(competition);
        match.setClub1(club1);
        match.setClub2(club2);
        match.setRound("Round 1");  // Round 1 or whatever you want to name it
        matchRepo.save(match);  // Save the match to the database
    }
}

     */


    //get the winners ya maehr


    /*
    public List<Club> getWinnersForNextRound(Competition competition, String round) {
    List<Match> matches = matchRepo.findByCompetitionAndRound(competition, round);
    List<Club> winners = new ArrayList<>();

    for (Match match : matches) {
        if (match.getGoals1() > match.getGoals2()) {
            winners.add(match.getClub1());
        } else if (match.getGoals2() > match.getGoals1()) {
            winners.add(match.getClub2());
        }
        // In case of a draw, handle it accordingly (optional)
    }
    return winners;
}

     */


    //generate next round:
    /*
    public void createNextRoundMatches(Competition competition, String currentRound) {
    List<Club> winners = getWinnersForNextRound(competition, currentRound);

    Collections.shuffle(winners);  // Randomize the winners

    String nextRound = getNextRound(currentRound);

    // Create matches for the next round
    for (int i = 0; i < winners.size(); i += 2) {
        Club club1 = winners.get(i);
        Club club2 = winners.get(i + 1);

        Match match = new Match();
        match.setCompetition(competition);
        match.setClub1(club1);
        match.setClub2(club2);
        match.setRound(nextRound);
        matchRepo.save(match);  // Save the match to the database
    }
}

     */
}

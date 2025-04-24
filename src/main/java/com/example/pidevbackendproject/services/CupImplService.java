package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Cup;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CupRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class CupImplService {


    private final MatchsRepo matchsRepo;
    private final ClubsRepo clubsRepo;
    private final CupRepo cupRepo;



    public String getRoundName(int teamNumber) {
        return switch (teamNumber){
            case 2 -> "Final";
            case 4 -> "Semi-Final";
            case 8 -> "Quarter-Final";
            case 16 -> "Round of 16";
            default -> "Round of " + teamNumber;
        };
    }




    private boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0 && n > 0;
    }


    /*public Cup createCup(String name ) {
        Cup cup = Cup.builder().name(name).build();
        return cupRepo.save(cup);
    }


    public ResponseEntity<String> createCupInitialMatches(String cupName , List<Integer> clubIds){

        List<Clubs> clubs = clubsRepo.findAllByIdClub(clubIds);
        Collections.shuffle(clubs);

        if(!isPowerOfTwo(clubIds.size())){
            return ResponseEntity.badRequest().body("Number of clubs must be a power of two");
        }

        Cup cup = createCup( cupName );
        String round = getRoundName(clubIds.size());

        for(int i= 0 ; i <clubs.size(); i+=2){
            Matchs match = Matchs.builder()
                    .club1(clubs.get(i))
                    .club2(clubs.get(i+1))
                    .roundName(round)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)//todo status Cup nraka7ha n7otha coupe
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok().body("Cup and initial matches created");
    }


    public ResponseEntity<String> generateNextRoundMatches(int cupId){
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(()-> new RuntimeException("Cup not found"));

        List<Matchs> completedMatches = cup.getMatchs()
                .stream().filter(m->m.getWinner() !=null && m.getRoundName() !=null)
                .toList();

        int currentRoundSize = completedMatches.size();

        if((currentRoundSize - 1 != 0 || (!isPowerOfTwo(currentRoundSize)))){
            return ResponseEntity.badRequest().body("Invalid round number");
        }

        List<Clubs> winners = completedMatches.stream()
                .map(Matchs::getWinner).toList();

        Collections.shuffle(winners);

        String nextRound = getRoundName(winners.size());

        for(int i= 0 ; i <winners.size(); i+=2){
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i+1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)//todo status Cup
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok().body("Next round is generated" + nextRound);
    }*/




    //hedhi 5edmet chat chsala7ali:

        public Cup createCup(String name) {
        Cup cup = Cup.builder().name(name).build();
        return cupRepo.save(cup);
    }

    public ResponseEntity<String> generateInitialMatches(String cupName, List<Integer> clubIds) {
        List<Clubs> clubs = clubsRepo.findAllByIdClubIn(clubIds);
        Collections.shuffle(clubs);

        if ((clubs.size() & (clubs.size() - 1)) != 0) {
            return ResponseEntity.badRequest().body("Number of teams must be a power of 2");
        }

        Cup cup = createCup(cupName);
        String round = getRoundName(clubs.size());

        for (int i = 0; i < clubs.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(clubs.get(i))
                    .club2(clubs.get(i+1))
                    .roundName(round)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)//todo status Cup
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok("Cup and initial matches created successfully.");
    }


    public ResponseEntity<String> generateNextRoundMatches(Integer cupId) {
        // Step 1: Retrieve the Cup
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        // Step 2: Get all matches and filter for completed ones with a valid round
        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body("No matches found for this cup.");
        }

        // Step 3: Group matches by round
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Step 4: Find the latest round (based on number of matches)
        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body("Could not determine the latest round.");
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.size() == 0) {
            return ResponseEntity.badRequest().body("No matches found in the latest round.");
        }

        // Step 5: Validate round size (must be power of 2)
        int currentRoundSize = latestRoundMatches.size();
        if ((currentRoundSize & (currentRoundSize - 1)) != 0) {
            return ResponseEntity.badRequest().body("Current round size is not a power of 2.");
        }

        // Step 6: Extract winners
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body("Tournament is already complete.");
        }

        // Step 7: Ensure the winners list is complete and shuffled
        Collections.shuffle(winners);

        // Check if we have already generated the next round
        if (allMatches.stream().anyMatch(m -> m.getRoundName().equals(getRoundName(winners.size())))) {
            return ResponseEntity.badRequest().body("Next round has already been generated.");
        }

        // Step 8: Generate next round matches
        String nextRound = getRoundName(winners.size());

        for (int i = 0; i < winners.size(); i += 2) {
            Clubs club1 = winners.get(i);
            Clubs club2 = winners.get(i + 1);

            Matchs match = Matchs.builder()
                    .club1(club1)
                    .club2(club2)
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null) // Add status logic if needed
                    .lieuMatch(null)
                    .build();

            matchsRepo.save(match);
        }

        return ResponseEntity.ok("Next round generated: " + nextRound);
    }



    /// todo badelha
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







    /*public ResponseEntity<String> generateNextRoundMatches(Integer cupId) {
        // Step 1: Retrieve the Cup
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        // Step 2: Get all matches and filter for completed ones with a valid round
        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body("No matches found for this cup.");
        }

        List<Matchs> completedMatches = allMatches.stream()
                .filter(m -> m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.toList());

        if (completedMatches.isEmpty()) {
            return ResponseEntity.badRequest().body("No completed matches to generate next round.");
        }

        // Step 3: Group matches by round
        Map<String, List<Matchs>> roundMap = completedMatches.stream()
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Step 4: Find the latest round (based on number of matches)
        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body("Could not determine the latest round.");
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.size() == 0) {
            return ResponseEntity.badRequest().body("No matches found in the latest round.");
        }

        // Step 5: Validate round size (must be power of 2)
        int currentRoundSize = latestRoundMatches.size();
        if ((currentRoundSize & (currentRoundSize - 1)) != 0) {
            return ResponseEntity.badRequest().body("Current round size is not a power of 2.");
        }

        // Step 6: Extract winners
        //lpartie hedhi commentiha
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .toList();



        List<Clubs> winners = new ArrayList<>(latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body("Tournament is already complete.");
        }

        // Convert the list to a mutable ArrayList before shuffling


// Shuffle the mutable list
        Collections.shuffle(winners);


        // Step 7: Shuffle and generate next round
        Collections.shuffle(winners);
        String nextRound = getRoundName(winners.size());

        for (int i = 0; i < winners.size(); i += 2) {
            Clubs club1 = winners.get(i);
            Clubs club2 = winners.get(i + 1);

            Matchs match = Matchs.builder()
                    .club1(club1)
                    .club2(club2)
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null) // TODO: Add status logic if needed
                    .lieuMatch(null)
                    .build();

            matchsRepo.save(match);
        }

        return ResponseEntity.ok("Next round generated: " + nextRound);
    }*/







    /*public ResponseEntity<String> generateNextRoundMatches(int cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        List<Matchs> completedMatches = allMatches.stream()
                .filter(m -> m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.toList());

        Map<String, List<Matchs>> roundMap = completedMatches.stream()
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        int currentRoundSize = latestRoundMatches.size();

        if (currentRoundSize == 0 || (currentRoundSize & (currentRoundSize - 1)) != 0) {
            return ResponseEntity.badRequest().body("Incomplete or invalid current round.");
        }

        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .toList();

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body("Tournament is already complete.");
        }

        Collections.shuffle(winners);
        String nextRound = getRoundName(winners.size());

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i+1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)//todo status Cup
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok("Next round generated: " + nextRound);
    }*/








}

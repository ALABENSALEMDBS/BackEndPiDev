package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Competition;
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



    /*public String getRoundName(int teamNumber) {
        return switch (teamNumber){
            case 2 -> "Final";
            case 4 -> "Semi-Final";
            case 8 -> "Quarter-Final";
            case 16 -> "Round of 16";
            default -> "Round of " + teamNumber;
        };
    }*/

    /*public String getRoundName(int teamNumber) {
        return switch (teamNumber) {
            case 2 -> "Final";
            case 4 -> "Semi-Final";
            case 8 -> "Quarter-Final";
            case 16 -> "Round of 16";
            default -> "Round of " + teamNumber;
        };
    }*/

    private String getRoundName(int numWinners) {
        switch (numWinners) {
            case 2: return "Final";
            case 4: return "Semi-Final";
            case 8: return "Quarter-Final";
            case 16: return "Round of 16";
            case 32: return "Round of 32";
            // Add more if needed
            default:
                throw new IllegalArgumentException("Unsupported number of winners: " + numWinners);
        }
    }




    private static final Map<String, Integer> ROUND_ORDER = Map.of(
            "Final", 1,
            "Semi-Final", 2,
            "Quarter-Final", 3,
            "Round of 16", 4
            // Add more rounds here if needed
    );


    public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // Group matches by round name where result and winner are set
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getResultatMatch() != null && m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Get the latest round by order using ROUND_ORDER map
        String latestRound = roundMap.keySet().stream()
                .min(Comparator.comparingInt(r -> ROUND_ORDER.getOrDefault(r, Integer.MAX_VALUE)))
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest completed round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No valid matches in the latest round."));
        }

        // Collect winners only from the latest completed round
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tournament is already complete."));
        }

        // Determine next round name
        String nextRound;
        try {
            nextRound = getRoundName(winners.size());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

        // Check if next round already exists
        boolean nextRoundExists = allMatches.stream()
                .anyMatch(m -> nextRound.equalsIgnoreCase(m.getRoundName()));

        if (nextRoundExists) {
            return ResponseEntity.badRequest().body(Map.of("error", nextRound + " has already been generated."));
        }

        // Shuffle and create matches
        Collections.shuffle(winners);

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }



    public Cup modifyCup(int idCup, Cup cup) {
        Cup existingMatchs = cupRepo.findById(idCup)
                .orElseThrow(() -> new RuntimeException("Cup non trouvé"));
        existingMatchs.setName(cup.getName());

        return cupRepo.save(existingMatchs);
    }








    /*public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // Group by round name
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Determine the latest round using ROUND_ORDER
        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> ROUND_ORDER.getOrDefault(name, 100)))
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);

        // Ensure all matches in the latest round are completed
        boolean isIncomplete = latestRoundMatches.stream()
                .anyMatch(m -> m.getWinner() == null || m.getResultatMatch() == null);

        if (isIncomplete) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not all matches in the latest round are completed."));
        }

        // Get winners from the latest round only
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .collect(Collectors.toList());

        if (winners.size() < 2) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not enough winners to create the next round."));
        }

        // Check if the next round already exists
        String nextRound = getRoundName(winners.size());
        boolean nextRoundExists = allMatches.stream()
                .anyMatch(m -> nextRound.equalsIgnoreCase(m.getRoundName()));

        if (nextRoundExists) {
            return ResponseEntity.badRequest().body(Map.of("error", "Next round has already been generated."));
        }

        // Handle final separately
        if (winners.size() == 2) {
            Matchs finalMatch = Matchs.builder()
                    .club1(winners.get(0))
                    .club2(winners.get(1))
                    .roundName("Final")
                    .cup(cup)
                    .build();

            matchsRepo.save(finalMatch);
            return ResponseEntity.ok(Map.of("message", "Final match generated."));
        }

        // Generate next round matches
        Collections.shuffle(winners); // Optional: random pairing

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }*/


    /*public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // ✅ Check if Final is already played
        Optional<Matchs> finalPlayed = allMatches.stream()
                .filter(m -> "Final".equalsIgnoreCase(m.getRoundName()))
                .filter(m -> m.getWinner() != null && m.getResultatMatch() != null)
                .findFirst();

        if (finalPlayed.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Cup is already completed."));
        }

        // ✅ Group only completed matches (having both result and winner)
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getResultatMatch() != null && m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest completed round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No valid matches in the latest round."));
        }

        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tournament is already complete."));
        }

        // ✅ Handle Final generation
        if (winners.size() == 2) {
            boolean finalAlreadyExists = allMatches.stream()
                    .anyMatch(m -> "Final".equalsIgnoreCase(m.getRoundName()));

            if (finalAlreadyExists) {
                return ResponseEntity.badRequest().body(Map.of("error", "Final match has already been generated."));
            }

            Matchs finalMatch = Matchs.builder()
                    .club1(winners.get(0))
                    .club2(winners.get(1))
                    .roundName("Final")
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(finalMatch);
            return ResponseEntity.ok(Map.of("message", "Final match generated."));
        }

        // ✅ Validate power of 2
        if ((winners.size() & (winners.size() - 1)) != 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Number of winners is not a power of 2."));
        }

        Collections.shuffle(winners);
        String nextRound = getRoundName(winners.size());

        boolean nextRoundExists = allMatches.stream()
                .anyMatch(m -> nextRound.equalsIgnoreCase(m.getRoundName()));

        if (nextRoundExists) {
            return ResponseEntity.badRequest().body(Map.of("error", nextRound + " has already been generated."));
        }

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }*/






//hedhi arja3
    /*public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // Group matches by round name where result and winner are set
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getResultatMatch() != null && m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Get the latest round by number of matches (assuming bigger round = earlier stage)
        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest completed round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No valid matches in the latest round."));
        }

        // Collect winners only from the latest completed round
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tournament is already complete."));
        }

        // If there are exactly 2 winners, attempt to create the Final match
        if (winners.size() == 2) {
            boolean finalAlreadyExists = allMatches.stream()
                    .anyMatch(m -> "Final".equalsIgnoreCase(m.getRoundName()));

            if (finalAlreadyExists) {
                return ResponseEntity.badRequest().body(Map.of("error", "Final match has already been generated."));
            }

            Matchs finalMatch = Matchs.builder()
                    .club1(winners.get(0))
                    .club2(winners.get(1))
                    .roundName("Final")
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(finalMatch);
            return ResponseEntity.ok(Map.of("message", "Final match generated."));
        }

        // Validate number of winners is a power of 2
        if ((winners.size() & (winners.size() - 1)) != 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Number of winners is not a power of 2."));
        }

        // Shuffle and generate the next round
        Collections.shuffle(winners);
        String nextRound = getRoundName(winners.size());

        boolean nextRoundExists = allMatches.stream()
                .anyMatch(m -> nextRound.equalsIgnoreCase(m.getRoundName()));

        if (nextRoundExists) {
            return ResponseEntity.badRequest().body(Map.of("error", nextRound + " has already been generated."));
        }

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }*/







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




    /*public ResponseEntity<String> generateInitialMatches(String cupName, List<Integer> clubIds) {
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
    }*/

    public ResponseEntity<Map<String, String>> generateInitialMatches(String cupName, List<Integer> clubIds) {
        List<Clubs> clubs = clubsRepo.findAllByIdClubIn(clubIds);
        Collections.shuffle(clubs);

        Map<String, String> response = new HashMap<>();

        if ((clubs.size() & (clubs.size() - 1)) != 0) {
            response.put("message", "Number of teams must be a power of 2");
            return ResponseEntity.badRequest().body(response);
        }

        Cup cup = createCup(cupName);
        String round = getRoundName(clubs.size());

        for (int i = 0; i < clubs.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(clubs.get(i))
                    .club2(clubs.get(i + 1))
                    .roundName(round)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        response.put("message", "Cup and initial matches created successfully.");
        response.put("cupId", String.valueOf(cup.getIdCup()));
        return ResponseEntity.ok(response);
    }




































/*
    public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // Group matches by round name
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found in the latest round."));
        }

        // ✅ Ensure all matches in the current round are completed
        boolean allCompleted = latestRoundMatches.stream()
                .allMatch(m -> m.getWinner() != null && m.getResultatMatch() != null);
        if (!allCompleted) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not all matches in the current round are completed."));
        }

        int currentRoundSize = latestRoundMatches.size();
        if ((currentRoundSize & (currentRoundSize - 1)) != 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Current round size is not a power of 2."));
        }

        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tournament is already complete."));
        }

        // Check if final match already exists
        boolean finalMatchExists = allMatches.stream()
                .anyMatch(m -> "Final".equals(m.getRoundName()));

        if (winners.size() == 2) {
            if (finalMatchExists) {
                return ResponseEntity.badRequest().body(Map.of("error", "Final match has already been generated."));
            }

            Matchs finalMatch = Matchs.builder()
                    .club1(winners.get(0))
                    .club2(winners.get(1))
                    .roundName("Final")
                    .cup(cup)
                    .build();
            matchsRepo.save(finalMatch);
            return ResponseEntity.ok(Map.of("message", "Final match generated."));
        }

        Collections.shuffle(winners);
        String nextRound = getRoundName(winners.size());

        // Check if next round already exists
        if (roundMap.containsKey(nextRound)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Next round has already been generated."));
        }

        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }*/








    //hedhi juste lmatchouwet y3awed ygeneriha normal
    /*public ResponseEntity<Map<String, String>> generateNextRoundMatches(Integer cupId) {
        Cup cup = cupRepo.findById(cupId)
                .orElseThrow(() -> new RuntimeException("Cup not found"));

        List<Matchs> allMatches = cup.getMatchs();
        if (allMatches == null || allMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found for this cup."));
        }

        // Group matches by round name
        Map<String, List<Matchs>> roundMap = allMatches.stream()
                .filter(m -> m.getWinner() != null && m.getRoundName() != null)
                .collect(Collectors.groupingBy(Matchs::getRoundName));

        // Find the latest round
        String latestRound = roundMap.keySet().stream()
                .sorted(Comparator.comparingInt(name -> roundMap.get(name).size()).reversed())
                .findFirst()
                .orElse(null);

        if (latestRound == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Could not determine the latest round."));
        }

        List<Matchs> latestRoundMatches = roundMap.get(latestRound);
        if (latestRoundMatches == null || latestRoundMatches.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No matches found in the latest round."));
        }

        int currentRoundSize = latestRoundMatches.size();
        if ((currentRoundSize & (currentRoundSize - 1)) != 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Current round size is not a power of 2."));
        }

        // Get winners from the latest round
        List<Clubs> winners = latestRoundMatches.stream()
                .map(Matchs::getWinner)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (winners.size() <= 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tournament is already complete."));
        }

        // Check if a final match already exists
        boolean finalMatchExists = allMatches.stream()
                .anyMatch(m -> "Final".equals(m.getRoundName()));

        // Handle the case where only two clubs remain, and generate the final match
        if (winners.size() == 2) {
            if (finalMatchExists) {
                return ResponseEntity.badRequest().body(Map.of("error", "Final match has already been generated."));
            }

            Matchs finalMatch = Matchs.builder()
                    .club1(winners.get(0))
                    .club2(winners.get(1))
                    .roundName("Final")
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(finalMatch);
            return ResponseEntity.ok(Map.of("message", "Final match generated."));
        }

        // Handle the case where there are more than two clubs, proceed with next round
        Collections.shuffle(winners);

        String nextRound = getRoundName(winners.size());

        // Ensure next round hasn't already been generated
        if (roundMap.containsKey(nextRound)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Next round has already been generated."));
        }

        // Create new matches for the next round
        for (int i = 0; i < winners.size(); i += 2) {
            Matchs match = Matchs.builder()
                    .club1(winners.get(i))
                    .club2(winners.get(i + 1))
                    .roundName(nextRound)
                    .cup(cup)
                    .dateMatch(null)
                    .goals1(null)
                    .goals2(null)
                    .resultatMatch(null)
                    .arbitre(null)
                    .typeMatch(null)
                    .winner(null)
                    .statusMatch(null)
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok(Map.of("message", "Next round generated: " + nextRound));
    }*/












    /*
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
     */







    /// todo badelha
    public Set<Clubs> ClubsOfCup(int idCup) {
        List<Matchs> ListMatchs = matchsRepo.matchsOfCup(idCup);
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

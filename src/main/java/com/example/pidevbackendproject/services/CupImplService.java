package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Cup;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CupRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CupImplService {


    private final MatchsRepo matchsRepo;
    ClubsRepo clubsRepo;
    CupRepo cupRepo;

    public CupImplService(MatchsRepo matchsRepo) {
        this.matchsRepo = matchsRepo;
    }


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


    public Cup createCup(String name ) {
        Cup cup = Cup.builder().name(name).build();
        return cupRepo.save(cup);
    }


    public ResponseEntity<String> createCup(String cupName , List<Integer> clubIds){

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
                    .statusMatch(null)//todo status Cup
                    .lieuMatch(null)
                    .build();
            matchsRepo.save(match);
        }

        return ResponseEntity.ok().body("Cup and initial matches created");





    }







}

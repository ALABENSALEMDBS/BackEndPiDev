package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Cup;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.CupRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CupImplService {


    ClubsRepo clubsRepo;
    CupRepo cupRepo;




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


    /*public ResponseEntity<String> createCup(@RequestBody Map<String, Object> requestData){
        String name = (String) requestData.get("name");
        List<Integer> clubsIds = (List<Integer>) requestData.get("clubIds");

        if(!isPowerOfTwo(clubsIds.size())){
            return ResponseEntity.badRequest().body("Number of clubs must be a power of two");
        }

        List<Clubs> clubs = clubsRepo.findAllByIdClub(clubsIds);
        Collections.shuffle(clubs);



        List<Matchs> matches  = new ArrayList<>();



    }*/







}

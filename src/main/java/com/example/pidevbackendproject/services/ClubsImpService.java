package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubsImpService implements IClubsServise {
    ClubsRepo clubsRepo;

    public Clubs addClubs(Clubs club) {
        return clubsRepo.save(club);
    }

    public void deleteClubs(int idClub) {
        clubsRepo.deleteById(idClub);

    }

    public Clubs modifyClubs(Clubs club) {
        return clubsRepo.save(club);
    }

    public List<Clubs> getAllClubs() {
        List<Clubs> clubs = clubsRepo.findAll();
        return clubs;
    }

    public Clubs getClubsById(int idClub) {
        return clubsRepo.findById(idClub).get();
    }
}

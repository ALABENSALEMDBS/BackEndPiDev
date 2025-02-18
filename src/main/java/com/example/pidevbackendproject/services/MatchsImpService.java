package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchsImpService implements IMatchsService {
    MatchsRepo matchsRepo;
    public Matchs addMatchs(Matchs match) {
        return matchsRepo.save(match);
    }

    public void deleteMatchs(int idMatch) {
     matchsRepo.deleteById(idMatch);
    }

    public Matchs modifyMatchs(Matchs match) {
        return matchsRepo.save(match);
    }

    public List<Matchs> getAllMatchs() {
        return matchsRepo.findAll();
    }

    public Matchs getMatchsById(int idMatch) {
        return matchsRepo.findById(idMatch).get();
    }
}

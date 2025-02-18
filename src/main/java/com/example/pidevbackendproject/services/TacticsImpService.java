package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Tactics;
import com.example.pidevbackendproject.repositories.TacticsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacticsImpService implements ITacticsService {
    TacticsRepo tacticsRepo;

    public Tactics addTactics(Tactics tactic) {
        return tacticsRepo.save(tactic);
    }

    public void deleteTactics(int idTactic) {
     tacticsRepo.deleteById(idTactic);
    }

    public Tactics modifyTactics(Tactics tactic) {
        return tacticsRepo.save(tactic);
    }

    public List<Tactics> getAllTactics() {
        return tacticsRepo.findAll();
    }

    public Tactics getTacticsById(int idTactic) {
        return tacticsRepo.findById(idTactic).get();
    }
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Tactics;
import com.example.pidevbackendproject.repositories.FormationsRepo;
import com.example.pidevbackendproject.repositories.TacticsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TacticsImpService implements ITacticsService {
    //***************
    TacticsRepo tacticsRepo;
    FormationsRepo formationsRepo;

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

    public Tactics addTacticAndAssignTacticToFormation(Tactics tactic, int idFormation) {
        Formations formation = formationsRepo.findById(idFormation).get();
        // Vérifie si la formation contient 11 joueurs
        if (formation.getJoueurs().size() == 11) {
            tactic.setFormation(formation);
            tacticsRepo.save(tactic);
            return tactic;
        } else {
            throw new IllegalStateException("La formation doit contenir exactement 11 joueurs.");
        }
    }
}

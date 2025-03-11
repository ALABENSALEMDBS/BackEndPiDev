package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Tactics;

import java.util.List;

public interface ITacticsService {
    Tactics addTactics(Tactics tactic);
    void deleteTactics(int idTactic);
    Tactics modifyTactics(Tactics tactic);
    List<Tactics> getAllTactics();
    Tactics getTacticsById(int idTactic);

    public Tactics addTacticAndAssignTacticToFormation(Tactics tactic, int idFormation);
}

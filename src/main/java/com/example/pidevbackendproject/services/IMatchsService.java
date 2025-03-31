package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Matchs;

import java.util.List;
import java.util.Optional;

public interface IMatchsService {
    Matchs addMatchs(Matchs match);
    void deleteMatchs(int idMatch);
    Matchs modifyMatchs(int idMatch,Matchs match);
    List<Matchs> getAllMatchs();
    Matchs getMatchsById(int idMatch);
    void affectTwoClubs(Matchs match,int idClub1, int idClub2);
    Optional<Matchs> updateGoals(int idMatch, Integer goal1 , Integer goal2);
}

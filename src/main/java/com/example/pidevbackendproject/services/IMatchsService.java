package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Matchs;

import java.util.List;

public interface IMatchsService {
    Matchs addMatchs(Matchs match);
    void deleteMatchs(int idMatch);
    Matchs modifyMatchs(Matchs match);
    List<Matchs> getAllMatchs();
    Matchs getMatchsById(int idMatch);
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.StatistiqueMatchs;

import java.util.List;

public interface IStatistiqueMatchsService {
    StatistiqueMatchs addStatistiqueMatchs(StatistiqueMatchs statistiqueMatch);
    void deleteStatistiqueMatchs(int idStatistiqueMatch);
    StatistiqueMatchs modifyStatistiqueMatchs(StatistiqueMatchs statistiqueMatch);
    List<StatistiqueMatchs> getAllStatistiqueMatchs();
    StatistiqueMatchs getStatistiqueMatchsById(int idStatistiqueMatch);
}

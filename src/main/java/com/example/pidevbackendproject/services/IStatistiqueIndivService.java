package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.StatistiqueIndiv;

import java.util.List;
import java.util.Optional;

public interface IStatistiqueIndivService {
    StatistiqueIndiv addStatistiqueIndiv(StatistiqueIndiv statistiqueIndiv);
    void deleteStatistiqueIndiv(int idStatistiqueIndiv);
    StatistiqueIndiv modifyStatistiqueIndiv(StatistiqueIndiv statistiqueIndiv);
    List<StatistiqueIndiv> getAllStatistiqueIndiv();
    StatistiqueIndiv getStatistiqueIndivById(int idStatistiqueIndiv);

    List<StatistiqueIndiv> getStatistiqueIndivByJoueurNumero (int numeroJoueur);
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Tournois;

import java.util.List;

public interface ITournoisService {
    Tournois addTournois(Tournois tournoi);
    void deleteTournois(int idTournoi);
    Tournois modifyTournois(int idTournoi, Tournois tournoi);
    List<Tournois> getAllTournois();
    Tournois getTournoisById(int idTournoi);
    void affeterMatchATournois(int idMatch,int idTournoi);
}

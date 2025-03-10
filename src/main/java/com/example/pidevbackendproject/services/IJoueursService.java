package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;

import java.util.List;

public interface IJoueursService {
    Joueurs addJoueurs(Joueurs joueur);
    void deleteJoueurs(int numeroJoueur);
    Joueurs modifyJoueurs(Joueurs joueur);
    List<Joueurs> getAllJoueurs();
    Joueurs getJoueursById(int numeroJoueur);


    List<Joueurs> findJoueursWithoutFicheMedicale();

}

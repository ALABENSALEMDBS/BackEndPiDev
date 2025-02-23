package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;

import java.util.List;

public interface IFormationsService {
    Formations addFormations(Formations formation);
    void deleteFormations(int idFormation);
    Formations modifyFormations(int idFormation ,Formations formation);
    List<Formations> getAllFormations();
    Formations getFormationsById(int idFormation);
    void affecterJoueurAFormation(int numjoueur, int idFormation);
    void  affecterTacticAFormation(int idTactic, int idFormation);

}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Joueurs;

import java.util.List;
import java.util.Set;

public interface IFormationsService {
    Formations addFormations(Formations formation);
    void deleteFormations(int idFormation);
    Formations modifyFormations(int idFormation ,Formations formation);
    List<Formations> getAllFormations();
    Formations getFormationsById(int idFormation);
    void affecterJoueurAFormation(int numjoueur, int idFormation);
    void  affecterTacticAFormation(int idTactic, int idFormation);

    Set<Joueurs> getJoueursInFormation(int idFormation);

    public Formations affecterJoueursAFormation(int idFormation, List<Integer> joueursIds);

    public void desaffecterJoueurAFormation(int idFormation, int idJoueur);


    public List<Formations> getAllFormationsWith11PlayersAndCompatiblePositions();
}

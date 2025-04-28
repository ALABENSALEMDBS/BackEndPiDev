package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJoueursService {
    Joueurs addJoueurs(Joueurs joueur);
    void deleteJoueurs(int numeroJoueur);
    Joueurs modifyJoueurs(Joueurs joueur);
    List<Joueurs> getAllJoueurs();
    Joueurs getJoueursById(int numeroJoueur);
     List<Joueurs> getJoueurWithoutsousgroups();
    Joueurs findJoueursByNumeroJoueur(int numeroJoueur);
    Rapports findJoueursRapports(int idRapport, int numeroJoueur);
    List<Rapports> findJoueursRapportsnumeroJoueurposteJoueur(int numeroJoueur,String posteJoueur);
    List<Joueurs> findSousGroupesJoueurs(@Param("idExercice") int idExercice);
    List<Joueurs> findJoueursBynameSousGroup(String nameSousGroup);

    List<Joueurs> findJoueursWithoutFicheMedicale();

    void maktitulaire(int joueurid);

}

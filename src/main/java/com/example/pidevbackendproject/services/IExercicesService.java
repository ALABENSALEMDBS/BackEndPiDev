package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Exercices;

import java.util.List;

public interface IExercicesService {
    Exercices addExercices(Exercices exercice);
    void deleteExercices(int idExercice);
    Exercices modifyExercices(int idExercice,Exercices exercice);
    List<Exercices> getAllExercices();
    Exercices getExercicesById(int idExercice);
    List<Exercices> findBySeanceExerciceIdSeance(int seanceId);
    List<Exercices> findBySeanceExercice_IdSeance(int idSeance);
    List<Exercices> findExercicesWithNoSeance();
    List<Exercices> findExercicesAssignedToOtherSeances( int idSeance);
    List<Exercices> findAvailableExercices( int idSeance);
    public List<Exercices> getCompatibleExercisesForSeance(int seanceId);
}

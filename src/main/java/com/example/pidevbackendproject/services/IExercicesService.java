package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Exercices;

import java.util.List;

public interface IExercicesService {
    Exercices addExercices(Exercices exercice);
    void deleteExercices(int idExercice);
    Exercices modifyExercices(Exercices exercice);
    List<Exercices> getAllExercices();
    Exercices getExercicesById(int idExercice);
}

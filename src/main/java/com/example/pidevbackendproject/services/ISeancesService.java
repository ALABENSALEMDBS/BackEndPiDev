package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Seances;

import java.util.List;

public interface ISeancesService {
    Seances addSeances(Seances seance);
    void deleteSeances(int idSeance);
    Seances modifySeances(Seances seance);
    List<Seances> getAllSeances();
    Seances getSeancesById(int idSeance);
    void affecterexerciseaseance(int idExercice, int idSeance);
}

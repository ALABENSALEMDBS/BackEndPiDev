package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.entities.SousGroupes;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISeancesService {
    Seances addSeances(Seances seance);
    void deleteSeances(int idSeance);
    Seances modifySeances(int idSeance,Seances seance);
    List<Seances> getAllSeances();
    Seances getSeancesById(int idSeance);
    public void affecterMultipleExercisesToSeance(int idSeance, List<Integer> idExercices);
    List<Seances> findSousGroupesJoueurs(@Param("idExercice") int idExercice);
    void desafecterMultipleExercisesToSeance(int idExercice, int idSeance);

}

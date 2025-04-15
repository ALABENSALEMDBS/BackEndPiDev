package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IExerciceRetablissementsService {
    ExerciceRetablissements addExerciceRetablissements(ExerciceRetablissements exerciceRetablissement);
    void deleteExerciceRetablissements(int idExerciceRetablissement);
    ExerciceRetablissements modifyExerciceRetablissements(ExerciceRetablissements exerciceRetablissement);
    List<ExerciceRetablissements> getAllExerciceRetablissements();
    ExerciceRetablissements getExerciceRetablissementsById(int idExerciceRetablissement);
//import



}

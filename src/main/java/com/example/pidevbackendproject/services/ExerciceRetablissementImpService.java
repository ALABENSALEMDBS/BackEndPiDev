package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import com.example.pidevbackendproject.repositories.ExerciceRetablissementsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceRetablissementImpService implements IExerciceRetablissementsService {
    ExerciceRetablissementsRepo exerciceRetablissementsRepo;
    public ExerciceRetablissements addExerciceRetablissements(ExerciceRetablissements exerciceRetablissement) {
        return exerciceRetablissementsRepo.save(exerciceRetablissement);
    }

    public void deleteExerciceRetablissements(int idExerciceRetablissement) {
            exerciceRetablissementsRepo.deleteById(idExerciceRetablissement);
    }

    public ExerciceRetablissements modifyExerciceRetablissements(ExerciceRetablissements exerciceRetablissement) {
        return exerciceRetablissementsRepo.save(exerciceRetablissement);
    }

    public List<ExerciceRetablissements> getAllExerciceRetablissements() {
        List<ExerciceRetablissements> exerciceRetablissements = exerciceRetablissementsRepo.findAll();
        return exerciceRetablissements;
    }

    public ExerciceRetablissements getExerciceRetablissementsById(int idExerciceRetablissement) {
        return exerciceRetablissementsRepo.findById(idExerciceRetablissement).get();
    }
}

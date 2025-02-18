package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.repositories.ExercicesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExercicesImpService implements IExercicesService{
    ExercicesRepo exercicesRepo;
    public Exercices addExercices(Exercices exercice) {
        return exercicesRepo.save(exercice);
    }

    public void deleteExercices(int idExercice) {
        exercicesRepo.deleteById(idExercice);
    }

    public Exercices modifyExercices(Exercices exercice) {
        return exercicesRepo.save(exercice);
    }

    public List<Exercices> getAllExercices() {
        return exercicesRepo.findAll();
    }

    public Exercices getExercicesById(int idExercice) {
        return exercicesRepo.findById(idExercice).get();
    }
}

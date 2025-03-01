package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.repositories.ExercicesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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



    public Exercices modifyExercices(int idExercice, Exercices exercice) {
        Optional<Exercices> optionalExercices = exercicesRepo.findById(idExercice);
        if (!optionalExercices.isPresent()) {
            throw new RuntimeException("exercice non trouvé");
        }
        Exercices existingExercices = optionalExercices.get();
        existingExercices.setNameExercice(exercice.getNameExercice());
        existingExercices.setDescriptionExercice(exercice.getDescriptionExercice());
        existingExercices.setPhotoExercice(exercice.getPhotoExercice());
        existingExercices.setVideoExercice(exercice.getVideoExercice());

        return exercicesRepo.save(existingExercices);
    }
    public List<Exercices> getAllExercices() {
        return exercicesRepo.findAll();
    }

    public Exercices getExercicesById(int idExercice) {
        return exercicesRepo.findById(idExercice).get();
    }
}

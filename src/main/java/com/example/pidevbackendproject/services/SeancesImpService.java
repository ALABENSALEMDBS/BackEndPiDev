package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.repositories.ExercicesRepo;
import com.example.pidevbackendproject.repositories.SeancesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeancesImpService implements ISeancesService {
    SeancesRepo seancesRepo;
    ExercicesRepo exercicesRepo;
    public Seances addSeances(Seances seance) {
        return seancesRepo.save(seance)  ;
    }

    public void deleteSeances(int idSeance) {
    Seances seance = seancesRepo.findById(idSeance).get();
        for (Exercices exercices : seance.getExercises()) {
            exercices.setSeanceExercice(null);
            exercicesRepo.save(exercices);
        }
            seancesRepo.deleteById(idSeance);
    }

    public Seances modifySeances(Seances seance) {
        return seancesRepo.save(seance);
    }

    public List<Seances> getAllSeances() {
        return seancesRepo.findAll();
    }

    public Seances getSeancesById(int idSeance) {
        return seancesRepo.findById(idSeance).get();
    }

    public void affecterexerciseaseance(int idExercice, int idSeance) {
        Seances seance = seancesRepo.findById(idSeance).get();
        Exercices exercices = exercicesRepo.findById(idExercice).get();
        exercices.setSeanceExercice(seance);
        exercicesRepo.save(exercices);
    }
}

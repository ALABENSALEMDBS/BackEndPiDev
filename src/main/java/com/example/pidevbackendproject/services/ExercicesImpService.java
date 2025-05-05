package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.ExerciseType;
import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.repositories.ExercicesRepo;
import com.example.pidevbackendproject.repositories.SeancesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExercicesImpService implements IExercicesService{
    ExercicesRepo exercicesRepo;
    SeancesRepo seancesRepo;
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
        //existingExercices.setPhotoExercice(exercice.getPhotoExercice());
        existingExercices.setVideoExercice(exercice.getVideoExercice());

        return exercicesRepo.save(existingExercices);
    }
    public List<Exercices> getAllExercices() {
        return exercicesRepo.findAll();
    }

    public Exercices getExercicesById(int idExercice) {
        return exercicesRepo.findById(idExercice).get();
    }
    public List<Exercices> findBySeanceExerciceIdSeance(int seanceId) {
        return exercicesRepo.findBySeanceExerciceIdSeance(seanceId);
    }

    public List<Exercices> findBySeanceExercice_IdSeance(int idSeance) {
        return exercicesRepo.findBySeanceExercice_IdSeance(idSeance);
    }

    public List<Exercices> findExercicesWithNoSeance() {
        return exercicesRepo.findExercicesWithNoSeance();
    }

    public List<Exercices> findExercicesAssignedToOtherSeances(int idSeance) {
        return exercicesRepo.findExercicesAssignedToOtherSeances(idSeance);
    }

    public List<Exercices> findAvailableExercices(int idSeance) {
        return exercicesRepo.findAvailableExercices(idSeance);
    }
    public List<Exercices> getCompatibleExercisesForSeance(int seanceId) {
        Seances seance = seancesRepo.findById(seanceId)
                .orElseThrow(() -> new IllegalArgumentException("Séance introuvable"));

        int intensity = seance.getIntensityLevel();
        int duration = seance.getDurationMinutes();

        return exercicesRepo.findAll().stream()
                .filter(ex -> ex.getTypeExercice() != null) // ✅ Filtrer les exercices sans type
                .filter(ex -> isCompatible(ex.getTypeExercice(), intensity, duration))
                .collect(Collectors.toList());
    }


    private boolean isCompatible(ExerciseType type, int intensity, int duration) {
        if (type == null) return false;
        return switch (type) {
            case STRETCHING, MOBILITY, BREATHING -> intensity <= 3;
            case DRIBBLE, PASSING, AGILITY -> intensity > 3 && intensity <= 6 && duration >= 30;
            case ENDURANCE, STRENGTH, SPEED, TACTICAL -> intensity >= 7 && duration >= 45;
            case HIGH_SPEED, SPRINTS, ANAEROBIC -> intensity >= 8 && duration >= 60;
            default -> false;
        };
    }
}

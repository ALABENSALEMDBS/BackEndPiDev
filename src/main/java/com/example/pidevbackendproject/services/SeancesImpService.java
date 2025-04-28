package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.*;
import com.example.pidevbackendproject.repositories.ExercicesRepo;
import com.example.pidevbackendproject.repositories.RapportsRepo;
import com.example.pidevbackendproject.repositories.SeancesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeancesImpService implements ISeancesService {
    SeancesRepo seancesRepo;
    RapportsRepo rapportsRepo;
    ExercicesRepo exercicesRepo;
    public Seances addSeances(Seances seance) {
        return seancesRepo.save(seance)  ;
    }

    public void deleteSeances(int idSeance) {
        Seances seance = seancesRepo.findById(idSeance)
                .orElseThrow(() -> new IllegalArgumentException("Séance introuvable"));

        // Détacher tous les exercices liés à cette séance
        for (Exercices exercice : seance.getExercises()) {
            exercice.setSeanceExercice(null);
            exercicesRepo.save(exercice);
        }

        // Trouver tous les rapports liés à cette séance
        List<Rapports> rapports = rapportsRepo.findBySeancesrapport(seance);
        for (Rapports rapport : rapports) {
            rapport.setSeancesrapport(null);
            rapportsRepo.save(rapport);
        }

        // Supprimer la séance
        seancesRepo.deleteById(idSeance);
    }



    public Seances modifySeances(int idSeance, Seances seance) {

        Optional<Seances> optionalSeances = seancesRepo.findById(idSeance);
        if (!optionalSeances.isPresent()) {
            throw new RuntimeException("Seance non trouvé");
        }
        Seances existingSeances = optionalSeances.get();
        existingSeances.setTitleSeance(seance.getTitleSeance());
        existingSeances.setJourSeance(seance.getJourSeance());
        existingSeances.setDescription(seance.getDescription());
        existingSeances.setTypeSeance(seance.getTypeSeance());
        existingSeances.setJourSeance(seance.getJourSeance());
        existingSeances.setDurationMinutes(seance.getDurationMinutes());
        existingSeances.setHeureDebut(seance.getHeureDebut());
        existingSeances.setHeureFin(seance.getHeureFin());
        existingSeances.setLocation(seance.getLocation());
        existingSeances.setIntensityLevel(seance.getIntensityLevel());
        return seancesRepo.save(existingSeances);
    }

    public List<Seances> getAllSeances() {
        return seancesRepo.findAll();
    }

    public Seances getSeancesById(int idSeance) {
        return seancesRepo.findById(idSeance).get();
    }

    public void affecterMultipleExercisesToSeance(int idSeance, List<Integer> idExercices) {
        Seances seance = seancesRepo.findById(idSeance)
                .orElseThrow(() -> new IllegalArgumentException("Séance introuvable"));

        int intensity = seance.getIntensityLevel();
        int duration = seance.getDurationMinutes();

        List<Exercices> exercices = exercicesRepo.findAllById(idExercices);

        // Filtrage basé sur l'intensité et la durée
        List<Exercices> filtered = exercices.stream()
                .filter(ex -> {
                    switch (ex.getTypeExercice()) {
                        case STRETCHING, MOBILITY, BREATHING -> {
                            return intensity <= 3;
                        }
                        case DRIBBLE, PASSING, AGILITY -> {
                            return intensity > 3 && intensity <= 6 && duration >= 30;
                        }
                        case ENDURANCE, STRENGTH, SPEED, TACTICAL -> {
                            return intensity >= 7 && duration >= 45;
                        }
                        case HIGH_SPEED, SPRINTS, ANAEROBIC -> {
                            return intensity >= 8 && duration >= 60;
                        }
                        default -> {
                            return false;
                        }
                    }
                }).toList();

        for (Exercices ex : filtered) {
            ex.setSeanceExercice(seance);
            exercicesRepo.save(ex);
        }
    }


    // Méthode pour vérifier si l'exercice correspond au type
    private boolean exerciceCorrespondAuxTypes(Exercices exercice, List<String> allowedTypes) {
        String name = exercice.getNameExercice() != null ? exercice.getNameExercice().toLowerCase() : "";
        String description = exercice.getDescriptionExercice() != null ? exercice.getDescriptionExercice().toLowerCase() : "";

        for (String type : allowedTypes) {
            String typeLower = type.toLowerCase();
            if (name.contains(typeLower) || description.contains(typeLower)) {
                return true;
            }
        }
        return false;
    }

    // Liste des types par niveau d'intensité
    private List<String> getAllowedExerciseTypes(int intensityLevel) {
        if (intensityLevel == 1) {
            return Arrays.asList("Cardio", "Stretching", "Mobilité", "Respiration");
        } else if (intensityLevel == 2) {
            return Arrays.asList("Cardio", "Dribble", "Endurance légère", "Coordination");
        } else if (intensityLevel == 3) {
            return Arrays.asList("Dribble", "Headshot", "Agility", "Réactivité", "Passe");
        } else if (intensityLevel == 4 || intensityLevel == 5 || intensityLevel == 6) {
            return Arrays.asList("Endurance", "Strength", "Tactical", "Puissance", "Explosivité", "Vitesse");
        } else if (intensityLevel >= 7) {
            return Arrays.asList("Speed", "Explosiveness", "HighIntensity", "Power", "Résistance", "Sprints", "Anaérobie");
        } else {
            return Arrays.asList(); // aucun type autorisé
        }
    }


    public List<Seances> findSousGroupesJoueurs(int idExercice) {
        return seancesRepo.findSousSeances(idExercice);
    }

    public void desafecterMultipleExercisesToSeance(int idExercice, int idSeance) {
        Optional<Exercices> exerciceOpt = exercicesRepo.findById(idExercice);

        if (exerciceOpt.isPresent()) {
            Exercices exercice = exerciceOpt.get();
            Seances seance = exercice.getSeanceExercice();

            if (seance != null && seance.getIdSeance() == idSeance) {
                exercice.setSeanceExercice(null);
                exercicesRepo.save(exercice);
            } else {
                throw new RuntimeException("Cet exercice n'est pas lié à la séance spécifiée.");
            }

        } else {
            throw new RuntimeException("Exercice avec ID " + idExercice + " non trouvé.");
        }
    }

}

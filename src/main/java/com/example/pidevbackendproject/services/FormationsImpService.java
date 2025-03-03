package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.entities.Tactics;
import com.example.pidevbackendproject.repositories.FormationsRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.TacticsRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FormationsImpService implements IFormationsService {
    //******************
    FormationsRepo formationsRepo;
    JoueursRepo joueursRepo;
    TacticsRepo tacticsRepo;
    public Formations addFormations(Formations formation) {
        return formationsRepo.save(formation);
    }

//    public void deleteFormations(int idFormation)
//    {
//        Formations formation = formationsRepo.findById(idFormation).get();
//        for(Joueurs joueur : formation.getJoueurs()) {
//            joueur.setFormation(null);
//            joueursRepo.save(joueur);
//        }
//     formationsRepo.deleteById(idFormation);
//    }


    public void deleteFormations(int idFormation) {
        Optional<Formations> optionalFormation = formationsRepo.findById(idFormation);
        if (optionalFormation.isPresent()) {
            Formations formation = optionalFormation.get();

            // Remove association between Joueurs and Formation
            for (Joueurs joueur : formation.getJoueurs()) {
                joueur.getFormations().remove(formation); // Remove formation from joueur
                joueursRepo.save(joueur);
            }
            // Clear joueur references in formation
            formation.getJoueurs().clear();
            formationsRepo.save(formation);
            // Delete the formation
            formationsRepo.deleteById(idFormation);
        } else {
            throw new EntityNotFoundException("Formation with ID " + idFormation + " not found");
        }
    }





    public Formations modifyFormations(int idFormation, Formations formation)
    {
        Optional<Formations> existingFormation = formationsRepo.findById(idFormation);

        if (!existingFormation.isPresent()) {
            throw new RuntimeException("Formation non trouvée !");
        }

        Formations formationToUpdate = existingFormation.get();
        formationToUpdate.setNameFormation(formation.getNameFormation());
        formationToUpdate.setDescriptionFormation(formation.getDescriptionFormation());
        // Ajoutez d'autres champs si nécessaire

        return formationsRepo.save(formationToUpdate);    }

    public List<Formations> getAllFormations() {
        return formationsRepo.findAll();
    }

    public Formations getFormationsById(int idFormation) {
        return formationsRepo.findById(idFormation).get();
    }

//    public void affecterJoueurAFormation(int numjoueur, int idFormation) {
//        Joueurs joueur = joueursRepo.findByNumeroJoueur(numjoueur);
//        Formations formation = formationsRepo.getById(idFormation);
//        joueur.setFormation(formation);
//        if (formation.getJoueurs().size() < 11){
//            joueursRepo.save(joueur);
//        }else
//            throw new IllegalStateException("Le sous-groupe est déjà complet.");
//    }


    public void affecterJoueurAFormation(int numjoueur, int idFormation) {
        Joueurs joueur = joueursRepo.findByNumeroJoueur(numjoueur);
        Formations formation = formationsRepo.findById(idFormation)
                .orElseThrow(() -> new EntityNotFoundException("Formation not found"));

        if (formation.getJoueurs().size() < 11) {
            // Add formation to joueur's formations
            joueur.getFormations().add(formation);

            // Add joueur to formation's joueurs (maintain bidirectional consistency)
            formation.getJoueurs().add(joueur);

            // Save both entities
            joueursRepo.save(joueur);
            formationsRepo.save(formation);
        } else {
            throw new IllegalStateException("Le sous-groupe est déjà complet.");
        }
    }



    public void affecterTacticAFormation(int idTactic, int idFormation) {
        Tactics tactic = tacticsRepo.getById(idTactic);
        Formations formation = formationsRepo.getById(idFormation);
        tactic.setFormation(formation);
        tacticsRepo.save(tactic);
    }

    public Set<Joueurs> getJoueursInFormation(int idFormation) {
        Formations formation = formationsRepo.findById(idFormation).get();

        return formation.getJoueurs();
    }
}

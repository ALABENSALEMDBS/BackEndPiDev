package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Formations;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.FormationsRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormationsImpService implements IFormationsService {
    //******************
    FormationsRepo formationsRepo;
    JoueursRepo joueursRepo;
    public Formations addFormations(Formations formation) {
        return formationsRepo.save(formation);
    }

    public void deleteFormations(int idFormation)
    {
        Formations formation = formationsRepo.findById(idFormation).get();
        for(Joueurs joueur : formation.getJoueurs()) {
            joueur.setFormation(null);
            joueursRepo.save(joueur);
        }
     formationsRepo.deleteById(idFormation);
    }

    public Formations modifyFormations(Formations formation) {
        return formationsRepo.save(formation);
    }

    public List<Formations> getAllFormations() {
        return formationsRepo.findAll();
    }

    public Formations getFormationsById(int idFormation) {
        return formationsRepo.findById(idFormation).get();
    }

    public void affecterJoueurAFormation(int numjoueur, int idFormation) {
        Joueurs joueur = joueursRepo.findByNumeroJoueur(numjoueur);
        Formations formation = formationsRepo.getById(idFormation);
        joueur.setFormation(formation);
        if (formation.getJoueurs().size() < 11){
            joueursRepo.save(joueur);
        }else
            throw new IllegalStateException("Le sous-groupe est déjà complet.");
    }
}

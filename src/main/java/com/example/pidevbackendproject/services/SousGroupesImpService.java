package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.SousGroupesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SousGroupesImpService implements ISousGroupesService {
    //********
    SousGroupesRepo sousGroupesRepo;
    JoueursRepo joueursRepo;

    public SousGroupes addSousGroupes(SousGroupes sousGroupe) {
        return sousGroupesRepo.save(sousGroupe);
    }

    public void deleteSousGroupes(int idSousGroup) {
        SousGroupes sousGroup = sousGroupesRepo.findById(idSousGroup)
                .orElseThrow(() -> new RuntimeException("Sous-groupe non trouvé"));

        // Dissocier les joueurs du sous-groupe
        for (Joueurs joueur : sousGroup.getJoueurs()) {
            joueur.setSousGroupe(null); // Enlever l'affectation du sous-groupe
            joueursRepo.save(joueur); // Sauvegarder la mise à jour de chaque joueur
        }
     sousGroupesRepo.deleteById(idSousGroup);
    }

    public SousGroupes modifySousGroupes(SousGroupes sousGroupe) {
        return sousGroupesRepo.save(sousGroupe);
    }

    public List<SousGroupes> getAllSousGroupes() {
        return sousGroupesRepo.findAll();
    }

    public SousGroupes getSousGroupesById(int idSousGroup) {
        return sousGroupesRepo.findById(idSousGroup).get();
    }

    public void affecterJoueurASousGroup(int numjoueur, int idSousGroupe) {
        Joueurs joueur = joueursRepo.findByNumeroJoueur(numjoueur);
        SousGroupes sousGroupe = sousGroupesRepo.getById(idSousGroupe);
        joueur.setSousGroupe(sousGroupe);
        if (sousGroupe.getJoueurs().size() < sousGroupe.getNbrJoueurSousGroup()){
            joueursRepo.save(joueur);
        }else
            throw new IllegalStateException("Le sous-groupe est déjà complet.");
    }
}

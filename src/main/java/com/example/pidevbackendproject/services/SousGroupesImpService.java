package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.SousGroupesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public SousGroupes modifySousGroupes(int idSousGroup, SousGroupes sousGroupe)
    {
        Optional<SousGroupes> optionalSousGroupe = sousGroupesRepo.findById(idSousGroup);

        if (!optionalSousGroupe.isPresent()) {
            throw new RuntimeException("Sous-groupe non trouvé");
        }

        SousGroupes existingSousGroupe = optionalSousGroupe.get();
        existingSousGroupe.setNameSousGroup(sousGroupe.getNameSousGroup());
        existingSousGroupe.setNbrJoueurSousGroup(sousGroupe.getNbrJoueurSousGroup());

        return sousGroupesRepo.save(existingSousGroupe);

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

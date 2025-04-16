package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JoueursImpService implements IJoueursService {
    JoueursRepo joueursRepo;
    public Joueurs addJoueurs(Joueurs joueur) {
        return joueursRepo.save(joueur);
    }

    public void deleteJoueurs(int numeroJoueur) {
     joueursRepo.deleteById(numeroJoueur);
    }

    public Joueurs modifyJoueurs(Joueurs joueur) {
        return joueursRepo.save(joueur);
    }

    public List<Joueurs> getAllJoueurs() {
        return joueursRepo.findAll();
    }

    public Joueurs getJoueursById(int numeroJoueur) {
        return joueursRepo.findById(numeroJoueur).get();
    }

    public Joueurs getbyrapports(int idRapport) {
        return joueursRepo.findByRapportIdRapport(idRapport);
    }

    public List<Joueurs> getJoueurWithoutsousgroups() {

        List<Joueurs> allJoueurs = joueursRepo.findAll();
        return allJoueurs.stream().filter(x -> x.getSousGroupe()==null).collect(Collectors.toList());
    }

    public List<Joueurs> findJoueursWithoutFicheMedicale() {
        return joueursRepo.findJoueursWithoutFicheMedicale();
    }

    public void maktitulaire(int joueurid) {
        Joueurs joueur = joueursRepo.findById(joueurid)
                .orElseThrow(() -> new RuntimeException("Joueur not found"));
        if (joueur.isTituliare()){
            joueur.setTituliare(false);
        }
        else joueur.setTituliare(true);
        joueursRepo.save(joueur);
    }
}

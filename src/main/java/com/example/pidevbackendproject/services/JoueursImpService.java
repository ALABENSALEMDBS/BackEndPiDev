package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
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

    public List<Joueurs> findJoueursBynameSousGroup(String nameSousGroup) {
        return joueursRepo.findJoueursBynameSousGroup(nameSousGroup);
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
    public Joueurs findJoueursByNumeroJoueur(int numeroJoueur) {
        return joueursRepo.findJoueursByNumeroJoueur(numeroJoueur);
    }

    public Rapports findJoueursRapports(int idRapport, int numeroJoueur ) {
        return joueursRepo.findJoueursRapports(idRapport,numeroJoueur);
    }

    public List<Rapports> findJoueursRapportsnumeroJoueurposteJoueur( int numeroJoueur, String posteJoueur) {
        return joueursRepo.findJoueursRapportsnumeroJoueurposteJoueur(numeroJoueur,posteJoueur);
    }

    public List<Joueurs> findSousGroupesJoueurs(int idExercice) {
        return joueursRepo.findSousGroupesJoueurs(idExercice);
    }
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.FicheMedicales;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.FicheMedicalesRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FicheMedicalesImpService implements IFicheMedicalesService {
    FicheMedicalesRepo ficheMedicalesRepo;
    JoueursRepo joueursRepo;



    public FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale, int joueurId) {

        FicheMedicales AddedFicheMedicales = ficheMedicalesRepo.save(ficheMedicale);

        Joueurs j = joueursRepo.findById(joueurId).get();

        j.setFicheMedicale(AddedFicheMedicales);

        joueursRepo.save(j);

        return AddedFicheMedicales;
    }

   /* public FicheMedicales addFicheMedicalByIdJoueurs( int idPlayer,FicheMedicales ficheMedicales) {

            //if we dont have id player return joueur introvable
        Joueurs joueur = joueursRepo.findById(idPlayer)
                .orElseThrow(() -> new RuntimeException("Joueur introuvable  " ));

        // Associer la fiche médicale au joueur
        ficheMedicales.setJoueurficheMedicale(joueur);

        // Sauvegarder et retourner la fiche médicale
        return ficheMedicalesRepo.save(ficheMedicales);

    }*/


    public void deleteFicheMedicales(int idFicheMedicale) {
       ficheMedicalesRepo.deleteById(idFicheMedicale);
    }

    public FicheMedicales modifyFicheMedicales(FicheMedicales ficheMedicale) {
        return ficheMedicalesRepo.save(ficheMedicale);
    }

    public List<FicheMedicales> getAllFicheMedicales() {

        return ficheMedicalesRepo.findAll();
    }

    public FicheMedicales getFicheMedicalesById(int idFicheMedicale) {
        return ficheMedicalesRepo.findById(idFicheMedicale).get();
    }
}

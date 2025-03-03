package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
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


    //ubdate test create fiche



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
        FicheMedicales FicheMedicales = ficheMedicalesRepo.findById(idFicheMedicale).get();

        var joueur = FicheMedicales.getJoueurficheMedicale();

        if(joueur != null) {
            joueur.setFicheMedicale(null);

            joueursRepo.save(joueur);
        }

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

    //new methode
    public FicheMedicales createFicheMedicale(String name, String prenom, FicheMedicales ficheMedicales) {
        // Récupérer le joueur
        Joueurs joueur = joueursRepo.findByNameUsersAndPrenomUser(name, prenom)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé !"));

        // Vérifier si le joueur a déjà une fiche médicale
        if (joueur.getFicheMedicale() != null) {
            throw new RuntimeException("Ce joueur a déjà une fiche médicale !");
        }

        // Associer la fiche médicale au joueur
        ficheMedicales.setJoueurficheMedicale(joueur);

        return ficheMedicalesRepo.save(ficheMedicales);
    }

    public List<ficheMedicaleDto> findAllWithJoueurFullName() {
        return ficheMedicalesRepo.findAllWithJoueurFullName();
    }
}

package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import com.example.pidevbackendproject.entities.FicheMedicales;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.ExerciceRetablissementsRepo;
import com.example.pidevbackendproject.repositories.FicheMedicalesRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FicheMedicalesImpService implements IFicheMedicalesService {
   // @Autowired
    FicheMedicalesRepo ficheMedicalesRepo;
    JoueursRepo joueursRepo;
    ExerciceRetablissementsRepo exerciceRetablissementsRepo;
    EmailService emailService;

    //ubdate test create fiche



    public FicheMedicales addFicheMedicales(FicheMedicales ficheMedicale, int joueurId, int idexrcice) {
        ExerciceRetablissements exerciceRetablissements = exerciceRetablissementsRepo.findById(idexrcice)
                .orElseThrow(() -> new RuntimeException("Exercice non trouvé !"));

        FicheMedicales AddedFicheMedicales = ficheMedicalesRepo.save(ficheMedicale);
        AddedFicheMedicales.setExerciceRetablissements(exerciceRetablissements);

        Joueurs j = joueursRepo.findById(joueurId).get();

        j.setFicheMedicale(AddedFicheMedicales);

        joueursRepo.save(j);
        sendRecoveryExercisesEmail(j, exerciceRetablissements);
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

    public FicheMedicales createFicheMedicale(int idexrcice,String name, String prenom, FicheMedicales ficheMedicales) {
        ExerciceRetablissements exerciceRetablissements = exerciceRetablissementsRepo.findById(idexrcice)
                .orElseThrow(() -> new RuntimeException("Exercice non trouvé !"));
        // Récupérer le joueur
        Joueurs joueur = joueursRepo.findByNameUsersAndPrenomUser(name, prenom)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé !"));

        // Vérifier si le joueur a déjà une fiche médicale
        if (joueur.getFicheMedicale() != null) {
            throw new RuntimeException("Ce joueur a déjà une fiche médicale !");
        }
        ficheMedicales.setExerciceRetablissements(exerciceRetablissements);
        // Associer la fiche médicale au joueur
        ficheMedicales.setJoueurficheMedicale(joueur);

        return ficheMedicalesRepo.save(ficheMedicales);
    }

    public List<ficheMedicaleDto> findAllWithJoueurFullName() {
        return ficheMedicalesRepo.findAllWithJoueurFullName();
    }

    public List<Object[]> countInjuredPlayersByGravite() {
        return ficheMedicalesRepo.countInjuredPlayersByGravite();
    }
//envoier mail
private void sendRecoveryExercisesEmail(Joueurs joueur, ExerciceRetablissements exercice) {
    StringBuilder body = new StringBuilder();
    body.append("Bonjour ").append(joueur.getNameUsers()).append(",\n\n");
    body.append("Votre fiche médicale a été créée avec succès.\n");
    body.append("Vous avez été assigné à l'exercice de rétablissement suivant :\n");
    body.append("Nom : ").append(exercice.getNomExerciceRetablissement()).append("\n");
    body.append("Description : ").append(exercice.getDescriptionExerciceRetablissement()).append("\n\n");
    body.append("Prenez soin de vous et suivez les instructions !");

   emailService.sendMail(joueur.getEmailUser(), body.toString());
}


}

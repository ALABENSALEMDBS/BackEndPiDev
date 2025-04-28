package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.SousGroupesRepo;
import jakarta.mail.MessagingException;
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
    EmailService emailService;

    public SousGroupes addSousGroupes(SousGroupes sousGroupe) {
        sousGroupesRepo.save(sousGroupe);
        sousGroupe.getJoueurs().forEach(j -> {
            affecterJoueurASousGroup(j.getIdUser(), sousGroupe);
            sendEmail(j, sousGroupe);
        });
        return sousGroupe;
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

    private void affecterJoueurASousGroup(int idJoueur, SousGroupes sousGroupe) {
        Joueurs joueur = joueursRepo.findById(idJoueur).get();
        joueur.setSousGroupe(sousGroupe);
//        if (sousGroupe.getJoueurs().size() < sousGroupe.getNbrJoueurSousGroup()){
        joueursRepo.save(joueur);
//        }else
//            throw new IllegalStateException("Le sous-groupe est déjà complet.");
    }

//    private void sendEmail(Joueurs j, SousGroupes s){
//        StringBuilder emailBody = new StringBuilder();
//        emailBody.append("Hello ")
//                .append(j.getNameUsers())
//                .append("\nYou have been added to a new subgroup (")
//                .append(s.getNameSousGroup())
//                .append(")");
//        emailService.sendMail(j.getEmailUser(), emailBody.toString());
//    }

    private void sendEmail(Joueurs j, SousGroupes s){
        try {
            emailService.sendMail(j.getEmailUser(), j.getNameUsers(), s.getNameSousGroup());
        } catch (MessagingException e) {
            e.printStackTrace(); // ou logger proprement
        }
    }
    public List<SousGroupes> findSousGroupestitleSeance(String titleSeance) {
        return sousGroupesRepo.findSousGroupestitleSeance(titleSeance);
    }





    public List<SousGroupes> findSousGroupesidExercice(int idExercice) {
        return sousGroupesRepo.findSousGroupesidExercice(idExercice);
    }
}

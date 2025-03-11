package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.RapportsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RapportsImpService implements IRapportsService {

    RapportsRepo rapportsRepo;
    JoueursRepo joueursRepo;

    public void deleteRapports(int idRapport) {
        Optional<Rapports> optionalRapport = rapportsRepo.findById(idRapport);

        if (optionalRapport.isPresent()) {
            Rapports rapports = optionalRapport.get();

            // If there is a linked joueur, set the rapport to null
            Joueurs joueur = rapports.getJoueurrapport();
            if (joueur != null) {
                joueur.setRapport(null);  // Remove the link to the rapport
                joueursRepo.save(joueur); // Save the joueur without the rapport
            }

            // Delete the rapport
            rapportsRepo.deleteById(idRapport);
        }
    }


    public Rapports modifyRapports(int idRapport, Rapports rapport) {
            Optional<Rapports> optionalRapports = rapportsRepo.findById(idRapport);
            if (!optionalRapports.isPresent()) {
                throw new RuntimeException("Rapport non trouvé");
            }
            Rapports existingRapports = optionalRapports.get();
            existingRapports.setSpeedRapport(rapport.getSpeedRapport());
            existingRapports.setAccelerationRapport(rapport.getAccelerationRapport());
            existingRapports.setBlessureRapport(rapport.getBlessureRapport());
            existingRapports.setEtatRapport(rapport.getEtatRapport());
            existingRapports.setAgility(rapport.getAgility());
            existingRapports.setBalance(rapport.getBalance());
            existingRapports.setEndurance(rapport.getEndurance());
            existingRapports.setAerobicCapacity(rapport.getAerobicCapacity());
            existingRapports.setVerticalJump(rapport.getVerticalJump());
            existingRapports.setReactivity(rapport.getReactivity());
            existingRapports.setReactionTime(rapport.getReactionTime());
            existingRapports.setPower(rapport.getPower());
            existingRapports.setAnaerobicCapacity(rapport.getAnaerobicCapacity());
            existingRapports.setCoordination(rapport.getCoordination());
            existingRapports.setHorizontalJump(rapport.getStrength());
            existingRapports.setMuscularEndurance(rapport.getMuscularEndurance());
            existingRapports.setExplosiveness(rapport.getExplosiveness());
            existingRapports.setStrength(rapport.getStrength());

            return rapportsRepo.save(existingRapports);
        }



    public List<Rapports> getAllRapports() {
        return rapportsRepo.findAll();
    }

    public Rapports getRapportsById(int idRapport) {
        return rapportsRepo.findById(idRapport).get();
    }

    public List<Rapports> getRapportsByJoueur(int numeroJoueur) {
        return rapportsRepo.findByJoueurrapport_NumeroJoueur(numeroJoueur);
    }

    public void addRapports(Rapports rapport, int numeroJoueur) {
        Joueurs joueurs = joueursRepo.findByNumeroJoueur(numeroJoueur);
        Rapports rapports = rapportsRepo.save(rapport);
        rapports.setJoueurrapport(joueurs);
        rapportsRepo.save(rapports);
    }
}

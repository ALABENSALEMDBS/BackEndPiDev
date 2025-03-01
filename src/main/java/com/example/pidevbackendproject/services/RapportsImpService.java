package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.repositories.RapportsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RapportsImpService implements IRapportsService {

    RapportsRepo rapportsRepo;
    public Rapports addRapports(Rapports rapport) {
        return rapportsRepo.save(rapport);
    }

    public void deleteRapports(int idRapport) {
    rapportsRepo.deleteById(idRapport);
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
}

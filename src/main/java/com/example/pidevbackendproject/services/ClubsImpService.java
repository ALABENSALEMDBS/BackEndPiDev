package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubsImpService implements IClubsServise {
    ClubsRepo clubsRepo;

    public Clubs addClubs(Clubs club) {
        return clubsRepo.save(club);
    }

    public void deleteClubs(int idClub) {
        clubsRepo.deleteById(idClub);
    }



    public Clubs modifyClubs(int id, Clubs updatedClub) {
        return clubsRepo.findById(id)
                .map(existingClub -> {
                    existingClub.setNameClub(updatedClub.getNameClub());
                    existingClub.setEmailClub(updatedClub.getEmailClub());
                    existingClub.setAdressClub(updatedClub.getAdressClub());
                    existingClub.setDateClub(updatedClub.getDateClub());
                    existingClub.setLicenceClub(updatedClub.getLicenceClub());
                    existingClub.setLogo(updatedClub.getLogo());
                    return clubsRepo.save(existingClub);
                })
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id: " + id));
    }





    public List<Clubs> getAllClubs() {
        List<Clubs> clubs = clubsRepo.findAll();
        return clubs;
    }

    public Clubs getClubsById(int idClub) {
        return clubsRepo.findById(idClub).get();
    }
}

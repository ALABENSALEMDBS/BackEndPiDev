package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Config.FileStorageService;
import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubsImpService implements IClubsServise {
    ClubsRepo clubsRepo;
    FileStorageService fileStorageService;

    public Clubs addClubs(Clubs club) {
        return clubsRepo.save(club);
    }

    public void deleteClubs(int idClub) {


        //standingsRepository.deleteById(standingsRepository.findByClub(clubsRepo.findById(idClub).get()).getId());

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

    public void uploadPostPicture(MultipartFile file, Integer postId){
        Clubs post=clubsRepo.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("No post found with ID : "+postId));
        var profilePicture=fileStorageService.saveFile(file);
        post.setMediaUrl(profilePicture);
        clubsRepo.save(post);
    }
}

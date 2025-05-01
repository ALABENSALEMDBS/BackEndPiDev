package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.StandingsRepo;
import com.example.pidevbackendproject.services.ClubsImpService;
import com.example.pidevbackendproject.services.IClubsServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Tag(name = "Gestion Clubs")
@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubRestController {
    private final MatchsRepo matchsRepo;
    IClubsServise clubsServise;
    ClubsImpService clubsImpService;
    StandingsRepo standingsRepository;

    ClubsRepo clubsRepo;




    @PostMapping(value = "saveClub", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Clubs> createNewClub(
            @RequestPart("club") String clubJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Clubs c = objectMapper.readValue(clubJson, Clubs.class);


        Clubs clubs = Clubs.builder()
                .nameClub(c.getNameClub())
                .emailClub(c.getEmailClub())
                .adressClub(c.getAdressClub())
                .dateClub(c.getDateClub())
                .licenceClub(c.getLicenceClub())
                .logo(file.getBytes())
                .build();
        clubsRepo.save(clubs);

        return new ResponseEntity<>(clubs, HttpStatus.CREATED);
    }



    /*
    @PostMapping(value = "saveClub", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Clubs> createNewClub(
            @RequestPart("club") String clubJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Clubs c = objectMapper.readValue(clubJson, Clubs.class);


        Clubs clubs = Clubs.builder()
                .nameClub(c.getNameClub())
                .emailClub(c.getEmailClub())
                .adressClub(c.getAdressClub())
                .dateClub(c.getDateClub())
                .licenceClub(c.getLicenceClub())
                .logo(file.getBytes())
                .build();
        clubsRepo.save(clubs);

        return new ResponseEntity<>(clubs, HttpStatus.CREATED);
    }
     */

    @GetMapping("allClubs")
    public ResponseEntity<List<Clubs>> getClubs() {
        List<Clubs> clubList = clubsRepo.findAll();
        return ResponseEntity.ok(clubList);
    }

    /*@GetMapping("allClubs")
    public ResponseEntity<List<Clubs> getClubByID() {
        clubList = clubsRepo.findById(id);
        return ResponseEntity.ok(clubList);
    }*/



    @Operation(description = "Ajouter un Club")
    @PostMapping("/add-club")
    public Clubs addClub(@RequestBody Clubs c) {
        //Clubs club= clubsServise.addClubs(c);
        return clubsServise.addClubs(c);
    }

    @Operation(description = "récupérer toutes les club de la base de données")
    @GetMapping(value = "/retrieve-all-club")
    public List<Clubs> getAllClubs() {
        List<Clubs> listClub= clubsServise.getAllClubs();
        return listClub;
    }

    @Operation(description = "récupérer les club de la base de données by ID")
    @GetMapping("/retrieve-club/{club-id}")
    public Clubs retrieveClub(@PathVariable("club-id") int idClub) {
        Clubs club = clubsServise.getClubsById(idClub);
        return club;
    }


    @Operation(description = "Supprimer club by ID")
    @DeleteMapping("/remove-club/{club-id}")
    public void deleteClubs(@PathVariable("club-id") int idClub) {
        //standingsRepository.deleteByClubId(idClub);
        clubsServise.deleteClubs(idClub);
    }


    @PutMapping("/modify-club/{id}")
    public ResponseEntity<?> updateClub(
            @PathVariable Long id,
            @RequestParam String nameClub,
            @RequestParam String emailClub,
            @RequestParam String adressClub,
            @RequestParam String dateClub,
            @RequestParam String licenceClub,
            @RequestParam(required = false) MultipartFile logo
    ) {
        // Handle the update logic here
        return ResponseEntity.ok().build();
    }





    /*@PutMapping(value = "updateClub/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Clubs> updateClub(
            @PathVariable("id") Integer clubId,
            @RequestPart("club") String clubJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        Clubs existingClub = clubsRepo.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + clubId));

        ObjectMapper objectMapper = new ObjectMapper();
        Clubs updatedData = objectMapper.readValue(clubJson, Clubs.class);

        existingClub.setNameClub(updatedData.getNameClub());
        existingClub.setEmailClub(updatedData.getEmailClub());
        existingClub.setAdressClub(updatedData.getAdressClub());
        existingClub.setDateClub(updatedData.getDateClub());
        existingClub.setLicenceClub(updatedData.getLicenceClub());

        if (file != null && !file.isEmpty()) {
            existingClub.setLogo(file.getBytes());
        }

        Clubs updatedClub = clubsRepo.save(existingClub);

        return ResponseEntity.ok(updatedClub);
    }*/




    @PutMapping("/updateClub/{id}")
    public ResponseEntity<Clubs> updateClub(
            @PathVariable("id") Integer id,
            @RequestBody Clubs updatedClub) {

        Clubs existingClub = clubsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        existingClub.setNameClub(updatedClub.getNameClub());
        existingClub.setEmailClub(updatedClub.getEmailClub());
        existingClub.setAdressClub(updatedClub.getAdressClub());
        existingClub.setDateClub(updatedClub.getDateClub());
        existingClub.setLicenceClub(updatedClub.getLicenceClub());

        Clubs savedClub = clubsRepo.save(existingClub);

        return ResponseEntity.ok(savedClub);
    }











    @PostMapping(value = "/picture/{post-id}",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPostCoverPicture(
            @PathVariable("post-id") Integer postId,
            @Parameter()
            @RequestPart("file") MultipartFile file

    ){
        clubsImpService.uploadPostPicture(file,postId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        try {
            Path imagePath = Paths.get("uploadss", filename);
            byte[] imageBytes = Files.readAllBytes(imagePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


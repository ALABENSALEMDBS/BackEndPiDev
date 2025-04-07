package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.services.IClubsServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Gestion Clubs")
@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubRestController {
    IClubsServise clubsServise;

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
        clubsServise.deleteClubs(idClub);
    }

    @Operation(description = "Modifer club")
    @PutMapping("/modify-club")
    public Clubs modifyClub(@RequestBody Clubs c) {
        Clubs club= clubsServise.modifyClubs(c);
        return club;
    }
}


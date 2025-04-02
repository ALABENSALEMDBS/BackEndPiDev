package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.UsersRepo;
import com.example.pidevbackendproject.services.IMatchsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Gestion des Matchs")
@RestController
@AllArgsConstructor
@RequestMapping("/matchs")
public class MatchsRestController {

    private final UsersRepo usersRepo;
    private final MatchsRepo matchsRepo;
    private final ClubsRepo clubsRepo;
    IMatchsService matchsService;






    @Operation(description = "Ajouter un Match")
    @PostMapping("/add-matchs")
    public Matchs addMatchs(@RequestBody Matchs m) {
        return matchsService.addMatchs(m);
    }

    /// //
    /*@PostMapping(value = "saveMatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Matchs> createNewMatch(
            @RequestPart("match") String matchJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Matchs m = objectMapper.readValue(matchJson, Matchs.class);

        Matchs match = Matchs.builder()
                .resultatMatch(m.getResultatMatch())
                .dateMatch(m.getDateMatch())
                .lieuMatch(m.getLieuMatch())
                .statusMatch(m.getStatusMatch())
                .typeMatch(m.getTypeMatch())
                .arbitre(m.getArbitre())
                //.club1(m.getClub1())
                //.club2(m.getClub2())
                .displayPicture(file.getBytes())
                .build();

        matchsRepo.save(match);

        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }*/



    @GetMapping("allMatchs")
    public ResponseEntity<List<Matchs>> getMatchs() {
        List<Matchs> matchsList = matchsRepo.findAll();
        return ResponseEntity.ok(matchsList);
    }



    /*
     private String resultatMatch;
    private String dateMatch;  // Nouveau attribut pour la date du match
    private String lieuMatch;  // Nouveau attribut pour le lieu du match
    private String statusMatch; // Nouveau attribut pour l'état du match (par exemple "en cours", "terminé")
    private String typeMatch;   // Nouveau attribut pour le type du match (par exemple "amical", "championnat")
    private String arbitre;
    private String equipe1;
    private String equipe2;
    *
    */




    @Operation(description = "récupérer toutes les Matchs de la base de données")
    @GetMapping(value = "/retrieve-all-Matchs")
    public List<Matchs> getAllMatchs() {
        List<Matchs> matchs= matchsService.getAllMatchs();
        return matchs;
    }

    @Operation(description = "récupérer les Matchs de la base de données by ID")
    @GetMapping("/retrieve-matchs/{matchs-id}")
    public Matchs retrieveMatchs(@PathVariable("matchs-id") int idMatchs) {
        Matchs matchs = matchsService.getMatchsById(idMatchs);
        return matchs;
    }

    @Operation(description = "Supprimer matchs by ID")
    @DeleteMapping("/remove-matchs/{matchs-id}")
    public void deleteMatchs(@PathVariable("matchs-id") int idMatchs) {
        matchsService.deleteMatchs(idMatchs);
    }

    @Operation(description = "Modifer Matchs")
    @PutMapping("/modify-matchs/{matchs-id}")
    public Matchs modifyMatchs(@RequestBody Matchs mat,@PathVariable("matchs-id") int idMatchs) {
        Matchs matchs= matchsService.modifyMatchs(idMatchs,mat);
        return matchs;
    }


    //AffecterClubs
    @PostMapping("/affect-clubs/{idClub1}/{idClub2}")
    public ResponseEntity<String> assignClubsToMatch(
            @RequestBody Matchs m,
            @PathVariable int idClub1,
            @PathVariable int idClub2) {
        matchsService.affectTwoClubs(m, idClub1, idClub2);
        return ResponseEntity.ok("Clubs assigned to match successfully");
    }


    //normal post
    @PostMapping("/saveMatch")
    public Matchs saveMatchh(
            @RequestBody Matchs m) {
        return matchsService.addMatchs(m);
        //return ResponseEntity.ok("Matches saved successfully");
    }

/*
    @PostMapping(value = "saveMatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewMatch(
            @RequestPart("match") String matchJson,
            @RequestPart("file") MultipartFile file
            ) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Matchs m = objectMapper.readValue(matchJson, Matchs.class);

        Optional<Clubs> club1 = clubsRepo.findByNameClub(m.getClub1().getNameClub());
        Optional<Clubs> club2 = clubsRepo.findByNameClub(m.getClub2().getNameClub());

        if(club1.isEmpty() || club2.isEmpty()){
            return ResponseEntity.badRequest().body("Clubs not found");
        }

        Matchs match = Matchs.builder()
                .resultatMatch(m.getResultatMatch())
                .dateMatch(m.getDateMatch())
                .lieuMatch(m.getLieuMatch())
                .statusMatch(m.getStatusMatch())
                .typeMatch(m.getTypeMatch())
                .arbitre(m.getArbitre())
                .club1(club1.get())
                .club2(club2.get())
                .displayPicture(file.getBytes())
                .build();

        matchsRepo.save(match);

        //return new ResponseEntity<>(match, HttpStatus.CREATED);
        return new ResponseEntity<>(match, HttpStatus.CREATED);

        //return new ResponseEntity<>(match, HttpStatus.CREATED);
    }*/

    /*@PostMapping(value = "saveMatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewMatch(
            @RequestPart("match") String matchJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Matchs m = objectMapper.readValue(matchJson, Matchs.class);

        // Fetch clubs by name
        Clubs clubb1 = clubsRepo.findByNameClub(m.getClub1().getNameClub())
                .orElseThrow(() -> new RuntimeException("Club1 not found"));
        Clubs clubb2 = clubsRepo.findByNameClub(m.getClub2().getNameClub())
                .orElseThrow(() -> new RuntimeException("Club2 not found"));


        Matchs match = Matchs.builder()
                .resultatMatch(m.getResultatMatch())
                .dateMatch(m.getDateMatch())
                .lieuMatch(m.getLieuMatch())
                .statusMatch(m.getStatusMatch())
                .typeMatch(m.getTypeMatch())
                .arbitre(m.getArbitre())
                .club1(clubb1)
                .club2(clubb2)
                .displayPicture(file.getBytes())
                .build();

        //System.out.println(m.getClub1().getNameClub());
        matchsRepo.save(match);
        //System.out.println(m.getClub1().getNameClub());

        //return ResponseEntity.status(HttpStatus.CREATED).body("Match mrigl");
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }*/



    /*@PostMapping(value = "saveMatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewMatch(
            @RequestPart("match") String matchJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Matchs m = objectMapper.readValue(matchJson, Matchs.class);

        // Create and save match
        Matchs match = Matchs.builder()
                .resultatMatch(m.getResultatMatch())
                .dateMatch(m.getDateMatch())
                .lieuMatch(m.getLieuMatch())
                .statusMatch(m.getStatusMatch())
                .typeMatch(m.getTypeMatch())
                .arbitre(m.getArbitre())
                .club1(m.getClub1())
                .club2(m.getClub2())
                .displayPicture(file.getBytes())
                .build();
        matchsRepo.save(match);


        return ResponseEntity.status(HttpStatus.CREATED).body("Match created successfully");
    }*/



    @GetMapping("/notYedPlayedMatchs")
    public List<Matchs> notYetPlayedMatchs() {
        return matchsRepo.notYetPlayedMatchs();
    }

    @GetMapping("/playedMatchs")
    public List<Matchs> playedMatchs() {
        return matchsRepo.playedMatchs();
    }


    @PostMapping(value = "saveMatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewMatch(
            @RequestPart("match") String matchJson,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Convert JSON string to Matchs object
        ObjectMapper objectMapper = new ObjectMapper();
        Matchs m = objectMapper.readValue(matchJson, Matchs.class);

        // Fetch clubs by name
        Clubs clubb1 = clubsRepo.findByNameClub(m.getClub1().getNameClub())
                .orElseThrow(() -> new RuntimeException("Club1 not found"));
        Clubs clubb2 = clubsRepo.findByNameClub(m.getClub2().getNameClub())
                .orElseThrow(() -> new RuntimeException("Club2 not found"));


        Matchs match = Matchs.builder()
                .resultatMatch(null)
                .dateMatch(m.getDateMatch())
                .lieuMatch(m.getLieuMatch())
                .statusMatch(m.getStatusMatch())
                .typeMatch(m.getTypeMatch())
                .arbitre(m.getArbitre())
                .goals1(null)
                .goals2(null)
                .club1(clubb1)
                .club2(clubb2)
                .displayPicture(file.getBytes())
                .build();

        //System.out.println(m.getClub1().getNameClub());
        matchsRepo.save(match);
        //System.out.println(m.getClub1().getNameClub());

        //return ResponseEntity.status(HttpStatus.CREATED).body("Match mrigl");
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }



    @PatchMapping("/goals/{idMatch}")
    public Matchs updateGoals(@PathVariable int idMatch , @RequestBody Matchs goalsBody){
        Optional<Matchs> updatedMatch = matchsService.updateGoals(idMatch , goalsBody.getGoals1(), goalsBody.getGoals2());
                return updatedMatch.orElse(null);
    }


    @GetMapping("/ImgToString")
    public ResponseEntity<String> getImgToString(@RequestParam MultipartFile file) throws TesseractException, IOException {
        return new ResponseEntity<>(matchsService.getImageString(file), HttpStatus.OK);

    }







}

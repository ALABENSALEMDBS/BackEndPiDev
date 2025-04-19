package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
import com.example.pidevbackendproject.repositories.StandingsRepo;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchsImpService implements IMatchsService {

    private final StandingsRepo standingsRepo;
    MatchsRepo matchsRepo;
    ClubsRepo clubsRepo;
    Tesseract tesseract;
    StandingImplService standingImplService;

    public Matchs addMatchs(Matchs match) {
        return matchsRepo.save(match);
    }

    public void deleteMatchs(int idMatch) {
     matchsRepo.deleteById(idMatch);
    }



    /*public Matchs modifyMatchs(int idMatch, Matchs match)
    {
        Optional<Matchs> optionalMatchs = matchsRepo.findById(idMatch);

        if (!optionalMatchs.isPresent()) {
            throw new RuntimeException("match non trouvé");
        }

        Matchs existingMatchs = optionalMatchs.get();
        existingMatchs.setResultatMatch(match.getResultatMatch());

        return matchsRepo.save(existingMatchs);    }*/


    public Matchs modifyMatchs(int idMatch, Matchs match) {
        Matchs existingMatchs = matchsRepo.findById(idMatch)
                .orElseThrow(() -> new RuntimeException("Match non trouvé"));

        existingMatchs.setResultatMatch(match.getResultatMatch());
        existingMatchs.setDateMatch(match.getDateMatch());
        existingMatchs.setLieuMatch(match.getLieuMatch());
        existingMatchs.setStatusMatch(match.getStatusMatch());
        existingMatchs.setTypeMatch(match.getTypeMatch());
        existingMatchs.setArbitre(match.getArbitre());
        existingMatchs.setGoals1(match.getGoals1());
        existingMatchs.setGoals2(match.getGoals2());
        existingMatchs.setClub1(match.getClub1());
        existingMatchs.setClub2(match.getClub2());
        existingMatchs.updateResultat(); // auto-update resultatMatch
        existingMatchs.theWinner();      // auto-set winner

        return matchsRepo.save(existingMatchs);
    }





    public List<Matchs> getAllMatchs() {
        return matchsRepo.findAll();
    }


    public Matchs getMatchsById(int idMatch) {
        return matchsRepo.findById(idMatch).get();
    }

    //affect two clubs to thee latch
    public void affectTwoClubs(Matchs match,int idClub1, int idClub2) {
        match.setClub1(clubsRepo.findById(idClub1).get());
        match.setClub2(clubsRepo.findById(idClub2).get());
        matchsRepo.save(match);
    }

    @Override
    @Transactional
    public Optional<Matchs> updateGoals(int idMatch, Integer goal1, Integer goal2) {
        return matchsRepo.findById(idMatch).map(matchs ->{//map etsta3mlha m3a optional behc tbuildi if exists
            matchs.setGoals1(goal1);
            matchs.setGoals2(goal2);
            matchs.updateResultat();
            matchs.theWinner();
            //lmehtode mte3 lwinner
            return matchsRepo.save(matchs);
                });
    }






    public static final String BASEURL = "C:\\Program Files\\Tesseract-OCR";


    /*@Override
    @Transactional
    public Optional<Matchs> updateGoals(int idMatch, Integer goal1, Integer goal2) {
        Optional<Matchs> updatedMatch = matchsRepo.findById(idMatch).map(matchs -> {
            matchs.setGoals1(goal1);
            matchs.setGoals2(goal2);
            matchs.updateResultat();
            matchs.theWinner();
            return matchsRepo.save(matchs);
        });

        updatedMatch.ifPresent(match -> {
            if (match.getCompetition() != null) {
                int idCompetition = match.getCompetition().getIdCompetition();
                standingImplService.saveStandingsData(idCompetition);
            }
        });

        return updatedMatch;
    }*/



    public String getTeams(int idMatch){
        Matchs matchh = matchsRepo.findById(idMatch).get();
        return matchh.getClub1()+" - "+matchh.getClub2();
    }


    public String getImageString(MultipartFile multipartFile) throws TesseractException, IOException, IOException {
        String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Paths.get(BASEURL, originalFileName);

        // Ensure the directory exists
        Files.createDirectories(filePath.getParent());

        // Save the file
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        // Perform OCR
        String res = tesseract.doOCR(filePath.toFile());
        return res;
    }






/*
    @Override
    @Transactional
    public Optional<Matchs> updateGoalsFromSheet(int idMatch,MultipartFile multipartFile ) throws TesseractException, IOException {

        //String teamsvs = getTeams(idMatch);

        String resultat=getImageString(multipartFile);
        //String[] parts=resultat.split(":")[1].split("-");

        //todo : check if they are really the clubs (getClubs1() wtchouf if contains)

        String[] teams=resultat.split(":");
        String[] parts=teams[1].split("-");
        // trim tna7i l'esapcet bech t7assel score menna wmenna
        String score1=parts[0].trim();
        String score2=parts[1].trim();

        Integer numGoal1=Integer.valueOf(score1);
        Integer numGoal2=Integer.valueOf(score2);


        return matchsRepo.findById(idMatch).map(matchs ->{//map etsta3mlha m3a optional bech tbuildi if exists
            matchs.setGoals1(numGoal1);
            matchs.setGoals2(numGoal2);
            matchs.updateResultat();
            matchs.theWinner();
            //lmehtode mte3 lwinner
            return matchsRepo.save(matchs);
        });
    }*/






    /*@Override
    @Transactional
    public Optional<Matchs> updateGoalsFromSheet(int idMatch,MultipartFile multipartFile ) throws TesseractException, IOException {



        String resultat = getImageString(multipartFile);

        String[] parts=resultat.split(":")[1].split("-");

        // trim tna7i l'esapcet bech t7assel score menna wmenna
        String score1=parts[0].trim();
        String score2=parts[1].trim();

        Integer numGoal1 = Integer.valueOf(score1);
        Integer numGoal2 = Integer.valueOf(score2);


        return matchsRepo.findById(idMatch).map(matchs ->{//map etsta3mlha m3a optional bech tbuildi if exists
            matchs.setGoals1(numGoal1);
            matchs.setGoals2(numGoal2);
            matchs.updateResultat();
            matchs.theWinner();
            //lmehtode mte3 lwinner
            return matchsRepo.save(matchs);
        });
    }*/



    @Override
    @Transactional
    public Optional<Matchs> updateGoalsFromSheet(int idMatch, MultipartFile multipartFile) throws TesseractException, IOException {
        String resultat = getImageString(multipartFile);

        // Check if the result contains the expected format
        if (resultat.contains(":")) {
            String[] parts = resultat.split(":");

            // Check if the split string has enough parts
            if (parts.length > 1) {
                String[] scores = parts[1].split("-");

                // Ensure that we have two parts for the scores
                if (scores.length == 2) {
                    String score1 = scores[0].trim();
                    String score2 = scores[1].trim();

                    Integer numGoal1 = Integer.valueOf(score1);
                    Integer numGoal2 = Integer.valueOf(score2);

                    return matchsRepo.findById(idMatch).map(matchs -> {
                        matchs.setGoals1(numGoal1);
                        matchs.setGoals2(numGoal2);
                        matchs.updateResultat();
                        matchs.theWinner(); // Assuming this method handles the logic for the winner
                        return matchsRepo.save(matchs);
                    });
                } else {
                    throw new IllegalArgumentException("Invalid score format. Expected format is 'score1 - score2'.");
                }
            } else {
                throw new IllegalArgumentException("Invalid OCR result. Expected format is 'something: score1 - score2'.");
            }
        } else {
            throw new IllegalArgumentException("Invalid OCR result. ':' separator not found.");
        }





    }









}

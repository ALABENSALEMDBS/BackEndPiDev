package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
import com.example.pidevbackendproject.entities.SousGroupes;
import com.example.pidevbackendproject.repositories.ClubsRepo;
import com.example.pidevbackendproject.repositories.MatchsRepo;
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

    MatchsRepo matchsRepo;
    ClubsRepo clubsRepo;
    Tesseract tesseract;

    public Matchs addMatchs(Matchs match) {
        return matchsRepo.save(match);
    }

    public void deleteMatchs(int idMatch) {
     matchsRepo.deleteById(idMatch);
    }



    public Matchs modifyMatchs(int idMatch, Matchs match) 
    {
        Optional<Matchs> optionalMatchs = matchsRepo.findById(idMatch);

        if (!optionalMatchs.isPresent()) {
            throw new RuntimeException("match non trouvé");
        }

        Matchs existingMatchs = optionalMatchs.get();
        existingMatchs.setResultatMatch(match.getResultatMatch());

        return matchsRepo.save(existingMatchs);    }

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

    /*public static final String BASEURL="C:\\Program Files\\Tesseract-OCR";


    public String getImageString(MultipartFile multipartFile) throws TesseractException {
        final String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Paths.get(BASEURL +"\\"+originalFileName);
        final String orcToString = tesseract.doOCR(new File(String.valueOf(filePath)));
        return orcToString;
    }*/



    public static final String BASEURL = "C:\\Program Files\\Tesseract-OCR";

    /*public String getImageString(MultipartFile multipartFile) throws TesseractException, IOException, IOException {
        String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Paths.get(BASEURL, originalFileName);

        // Ensure the directory exists
        Files.createDirectories(filePath.getParent());

        // Save the file
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Perform OCR
        return tesseract.doOCR(filePath.toFile());
    }*/


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

    /*public void extractResultsFromSheet(String na){
        //9asemthom 7asb :
        String[] parts=na.split(":")[1].split("-");

        // trim tna7i l'esapcet bech t7assel score menna wmenna
        String score1=parts[0].trim();
        String score2=parts[1].trim();

        Integer numGoal1 = Integer.valueOf(score1);
        Integer numGoal2 = Integer.valueOf(score2);
    }*/



    public String getTeams(int idMatch){
        Matchs matchh = matchsRepo.findById(idMatch).get();
        return matchh.getClub1()+" - "+matchh.getClub2();
    }


    @Override
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
    }





}

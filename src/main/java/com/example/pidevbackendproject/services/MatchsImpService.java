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
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static final String BASEURL="C:\\Program Files";


    public String getImageString(MultipartFile multipartFile) throws TesseractException {
        final String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Paths.get(BASEURL +"\\"+originalFileName);
        final String orcToString = tesseract.doOCR(new File(String.valueOf(filePath)));
        return orcToString;
    }



}

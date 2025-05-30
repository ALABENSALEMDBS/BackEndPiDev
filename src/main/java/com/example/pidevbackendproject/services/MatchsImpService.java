package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Matchs;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    //test methode of one match each three days
    /*public RuntimeException addValidMatchs(Matchs match) {
        Clubs club1 = clubsRepo.findById(match.getClub1().getIdClub())
                .orElseThrow(()-> new RuntimeException("there is no club 1"));
        Clubs club2 = clubsRepo.findById(match.getClub2().getIdClub())
                .orElseThrow(()-> new RuntimeException("there is no club 2"));

        //Date dateMatch = match.getDateMatch();

        String dateStr = match.getDateMatch();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        LocalDateTime startDate = dateTime.plusDays(3);
        LocalDateTime endDate = dateTime.minusDays(3);


        //System.out.println(dateTime); // Output: 2025-04-08T08:03

        List<Matchs> playedMatchsIn3DaysClub1 = matchsRepo.validMatchsForAclub(club1.getIdClub(),startDate,endDate);
        List<Matchs> playedMatchsIn3DaysClub2 = matchsRepo.validMatchsForAclub(club2.getIdClub(),startDate,endDate);


        if(playedMatchsIn3DaysClub1.isEmpty() && playedMatchsIn3DaysClub2.isEmpty()){
            matchsRepo.save(match);

        }
        else if(!playedMatchsIn3DaysClub1.isEmpty()){
            playedMatchsIn3DaysClub1.stream().map(Matchs::getIdMatch).toList();
            return new RuntimeException("there are match for club 1 in 3 days" , (Throwable) playedMatchsIn3DaysClub1);
        }
        else {
            playedMatchsIn3DaysClub2.stream().map(Matchs::getIdMatch).toList();
            return new RuntimeException("there are match for club 1 in 3 days" , (Throwable) playedMatchsIn3DaysClub2);
        }

        return null;
    }*/







    /*public Matchs modifyMatchs(int idMatch, Matchs match)
    {
        Optional<Matchs> optionalMatchs = matchsRepo.findById(idMatch);

        if (!optionalMatchs.isPresent()) {
            throw new RuntimeException("match non trouvé");
        }

        Matchs existingMatchs = optionalMatchs.get();
        existingMatchs.setResultatMatch(match.getResultatMatch());

        return matchsRepo.save(existingMatchs);    }*/



    /*public void addValidMatchs(Matchs match) {
        Clubs club1 = clubsRepo.findById(match.getClub1().getIdClub())
                .orElseThrow(() -> new RuntimeException("There is no club 1"));
        Clubs club2 = clubsRepo.findById(match.getClub2().getIdClub())
                .orElseThrow(() -> new RuntimeException("There is no club 2"));

        String dateStr = match.getDateMatch(); // must be like "2025-04-08T08:03"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        LocalDateTime startDate = dateTime.minusDays(3);
        LocalDateTime endDate = dateTime.plusDays(3);

        List<Matchs> playedMatchsIn3DaysClub1 = matchsRepo.validMatchsForAclub(club1.getIdClub(), startDate, endDate);
        List<Matchs> playedMatchsIn3DaysClub2 = matchsRepo.validMatchsForAclub(club2.getIdClub(), startDate, endDate);

        if (playedMatchsIn3DaysClub1.isEmpty() && playedMatchsIn3DaysClub2.isEmpty()) {
            matchsRepo.save(match);
        } else if (!playedMatchsIn3DaysClub1.isEmpty()) {
            throw new RuntimeException("Club 1 has a match within 3 days" + playedMatchsIn3DaysClub1.stream().map(Matchs::getIdMatch));
        } else {
            throw new RuntimeException("Club 2 has a match within 3 days" + playedMatchsIn3DaysClub2.stream().map(Matchs::getIdMatch)  );
        }
    }*/


    public boolean isValidMatch(Matchs match){
        Clubs club1 = clubsRepo.findById(match.getClub1().getIdClub())
                .orElseThrow(() -> new RuntimeException("There is no club 1"));
        Clubs club2 = clubsRepo.findById(match.getClub2().getIdClub())
                .orElseThrow(() -> new RuntimeException("There is no club 2"));

        String dateStr = match.getDateMatch(); // must be like "2025-04-08T08:03"
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        //LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime matchDate = LocalDateTime.parse(match.getDateMatch(), formatter);*/



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime matchDate = LocalDateTime.parse(match.getDateMatch(), formatter);

        String startStr = matchDate.minusDays(3).format(formatter);
        String endStr = matchDate.plusDays(3).format(formatter);

        List<Matchs> playedMatchsIn3DaysClub1 = matchsRepo.validMatchsForAclub(club1.getIdClub(), startStr, endStr);
        List<Matchs> playedMatchsIn3DaysClub2 = matchsRepo.validMatchsForAclub(club2.getIdClub(), startStr, endStr);

        if (playedMatchsIn3DaysClub1.isEmpty() && playedMatchsIn3DaysClub2.isEmpty()) {
            return true;
        }
        else
            return false;
    }




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




    public String getTeams(int idMatch){
        Matchs matchh = matchsRepo.findById(idMatch).get();
        return matchh.getClub1()+" - "+matchh.getClub2();
    }

/*
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


*/

    public String getImageString(MultipartFile multipartFile) throws TesseractException, IOException {
        String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Paths.get(BASEURL, originalFileName);

        // Ensure the directory exists
        Files.createDirectories(filePath.getParent());

        // Save the file
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Perform OCR and return the result
        return tesseract.doOCR(filePath.toFile());
    }




    @Override
    @Transactional
    public Optional<Matchs> updateGoalsFromSheet(int idMatch, MultipartFile multipartFile) throws TesseractException, IOException {
        String resultat = getImageString(multipartFile);

        // Sanitize and log the OCR result
        if (resultat == null || resultat.trim().isEmpty()) {
            throw new IllegalArgumentException("OCR result is empty or unreadable.");
        }

        resultat = resultat.replaceAll("\\s+", ""); // Remove all whitespace

        // Example OCR result: "FinalScore:2-1"
        if (!resultat.contains(":") || !resultat.contains("-")) {
            throw new IllegalArgumentException("Invalid OCR result format. Expected format like 'Label:2-1'.");
        }

        String[] parts = resultat.split(":");
        if (parts.length < 2) {
            throw new IllegalArgumentException("OCR result is missing score section after ':'.");
        }

        String[] scores = parts[1].split("-");
        if (scores.length != 2) {
            throw new IllegalArgumentException("Score section should contain two values separated by '-'.");
        }

        try {
            Integer numGoal1 = Integer.parseInt(scores[0].trim());
            Integer numGoal2 = Integer.parseInt(scores[1].trim());

            return matchsRepo.findById(idMatch).map(matchs -> {
                matchs.setGoals1(numGoal1);
                matchs.setGoals2(numGoal2);
                matchs.updateResultat();
                matchs.theWinner();
                return matchsRepo.save(matchs);
            });
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("OCR extracted scores are not valid integers.", e);
        }
    }







}

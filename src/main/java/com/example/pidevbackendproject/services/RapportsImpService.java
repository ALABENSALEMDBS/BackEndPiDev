package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.*;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.RapportsRepo;
import com.example.pidevbackendproject.repositories.SeancesRepo;
import com.example.pidevbackendproject.repositories.SousGroupesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RapportsImpService implements IRapportsService {

    SeancesRepo seancesRepo;
    SousGroupesRepo sousGroupesRepo;
    RapportsRepo rapportsRepo;
    JoueursRepo joueursRepo;

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
        existingRapports.setDateRapport(rapport.getDateRapport());
        existingRapports.setEtatRapport(rapport.getEtatRapport());

        return rapportsRepo.save(existingRapports);
    }




    public List<Rapports> findRapportByNumeroJoueur(int numeroJoueur) {
        return rapportsRepo.findRapportByNumeroJoueur(numeroJoueur);
    }

    public List<Rapports> findRapportBySousGroupe(String nameSousGroup) {
        return rapportsRepo.findRapportBySousGroupe(nameSousGroup);
    }

    public List<Rapports> findRapportBytitleSeance(String titleSeance) {
        return rapportsRepo.findRapportBytitleSeance(titleSeance);
    }

    public List<Rapports> findByDateRapport(LocalDate date) {
        return rapportsRepo.findByDateRapport(date);
    }



    public List<Rapports> getAllRapports() {
        return rapportsRepo.findAll();
    }

    public Rapports getRapportsById(int idRapport) {
        return rapportsRepo.findById(idRapport).get();
    }



    public void addRapports(Rapports rapport, int numeroJoueur, String nameSousGroup,String titleSeance) {
        Joueurs joueurs = joueursRepo.findJoueursByNumeroJoueur(numeroJoueur);
        Seances seances = seancesRepo.findByTitleSeance(titleSeance);
        SousGroupes sousGroupes = sousGroupesRepo.findByNameSousGroup(nameSousGroup);
        rapport.setJoueursrapport(joueurs);
        rapport.setSeancesrapport(seances);
        rapport.setSousGroupesrapport(sousGroupes);
        rapportsRepo.save(rapport);

    }
    public Rapports getBestPlayerByPosteAndSeance(String poste, String titleSeance) {
        return rapportsRepo.findBestGoodEtatPlayerByPosteAndSeance(poste, titleSeance);
    }

    public  List<Rapports> getBestPlayerBySeance(String titleSeance) {
        return rapportsRepo.findBestGoodEtatPlayerBySeance(titleSeance);
    }

    public Rapports getBestPlayerByPosteSeanceAndSousGroupe(String poste, String titleSeance, String nomSousGroupe) {
        List<Rapports> rapports = rapportsRepo.findBestPlayersByPosteSeanceAndSousGroupe(poste, titleSeance, nomSousGroupe);
        return rapports.isEmpty() ? null : rapports.get(0); // ou throw exception si vide
    }
    public List<Rapports> getBadReportsByPoste(String poste) {
        return rapportsRepo.findBadReportsByPoste(poste);

    }

    public List<Rapports> getSimilarRapportsByPosteAndPerformance(String poste, int numeroJoueur) {
        return rapportsRepo.findSimilarOrBetterRapportsGoodEtatByPoste(poste, numeroJoueur);
    }

    public List<Rapports> detecterRapportsSuspectMaisEtatGood(String poste) {
        // Étape 1 : récupérer tous les rapports par poste avec état GOOD
        List<Rapports> rapports = rapportsRepo.findByPosteEtEtat(poste, etatplayer.GOOD);

        // Étape 2 : grouper par joueur
        Map<Joueurs, List<Rapports>> rapportsParJoueur = rapports.stream()
                .collect(Collectors.groupingBy(Rapports::getJoueursrapport));

        List<Rapports> rapportsSuspects = new ArrayList<>();

        for (Map.Entry<Joueurs, List<Rapports>> entry : rapportsParJoueur.entrySet()) {
            List<Rapports> derniersRapports = entry.getValue().stream()
                    .filter(r -> {
                        if (r.getDateRapport() == null) {
                            System.out.println("⚠ Rapport sans date détecté pour joueur: " + r.getJoueursrapport().getNameUsers());
                            return false;
                        }
                        return true;
                    })
                    .sorted(Comparator.comparing(Rapports::getDateRapport).reversed())
                    .limit(3)
                    .collect(Collectors.toList());

            if (derniersRapports.size() >= 2) {
                Rapports r1 = derniersRapports.get(0);
                Rapports r2 = derniersRapports.get(1);

                boolean baisse =
                        isDegradationValeur(r1.getSpeedRapport(), r2.getSpeedRapport(), 5) ||
                                isDegradationValeur(r1.getAccelerationRapport(), r2.getAccelerationRapport(), 6) ||
                                isDegradationValeur(r1.getEndurance(), r2.getEndurance(), 25) ||
                                isDegradationValeur(r1.getMuscularEndurance(), r2.getMuscularEndurance(), 20) ||
                                isDegradationValeur(r1.getAerobicCapacity(), r2.getAerobicCapacity(), 30) ||
                                isDegradationValeur(r1.getAnaerobicCapacity(), r2.getAnaerobicCapacity(), 28) ||
                                isDegradationValeur(r1.getStrength(), r2.getStrength(), 15) ||
                                isDegradationValeur(r1.getPower(), r2.getPower(), 18) ||
                                isDegradationValeur(r1.getExplosiveness(), r2.getExplosiveness(), 16) ||
                                isDegradationValeur(r1.getVerticalJump(), r2.getVerticalJump(), 10) ||
                                isDegradationValeur(r1.getHorizontalJump(), r2.getHorizontalJump(), 12) ||
                                isDegradationValeur(r1.getAgility(), r2.getAgility(), 15) ||
                                isDegradationValeur(r1.getBalance(), r2.getBalance(), 20) ||
                                isDegradationValeur(r1.getCoordination(), r2.getCoordination(), 20) ||
                                isDegradationValeur(r1.getReactionTime(), r2.getReactionTime(), 14) ||
                                isDegradationValeur(r1.getReactivity(), r2.getReactivity(), 15);

                if (baisse && r1.getEtatRapport() == etatplayer.GOOD) {
                    rapportsSuspects.add(r1); // On retourne le plus récent
                }
            }
        }

        return rapportsSuspects;
    }

    private boolean isDegradationValeur(Long actuel, Long precedent, int seuilPourcent) {
        if (actuel == null || precedent == null || precedent == 0) return false;

        // Si la valeur actuelle est supérieure, ce n’est pas une baisse
        if (actuel > precedent) return false;

        double variation = ((double)(precedent - actuel) / precedent) * 100;
        return variation >= seuilPourcent || actuel < precedent;
    }


}

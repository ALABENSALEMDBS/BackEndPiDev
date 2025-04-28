package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRapportsService {
    void addRapports(Rapports rapport,int numeroJoueur , String nameSousGroup,String titleSeance);
    void deleteRapports(int idRapport);
    Rapports modifyRapports(int idRapport, Rapports rapport);
    List<Rapports> getAllRapports();
    Rapports getRapportsById(int idRapport);
    public List<Rapports> findRapportByNumeroJoueur(int numeroJoueur);
    public List<Rapports> findRapportBySousGroupe(String nameSousGroup);
    public List<Rapports> findRapportBytitleSeance(String titleSeance);
    List <Rapports> findByDateRapport(LocalDate date);
    public Rapports getBestPlayerByPosteAndSeance(String poste, String titleSeance);
    public  List<Rapports> getBestPlayerBySeance(String titleSeance);
    public Rapports getBestPlayerByPosteSeanceAndSousGroupe(String poste, String titleSeance, String nomSousGroupe);
    public List<Rapports> getBadReportsByPoste(String poste);
    public List<Rapports> getSimilarRapportsByPosteAndPerformance(String poste, int numeroJoueur);
    public List<Rapports> detecterRapportsSuspectMaisEtatGood(String poste);
}

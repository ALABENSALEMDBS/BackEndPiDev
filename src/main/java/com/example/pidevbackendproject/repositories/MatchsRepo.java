package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Matchs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchsRepo extends JpaRepository<Matchs, Integer> {

    //@Query("select p from Participant p join p.evenements ev join ev.logistiques log where p.tache='ORGANISATEUR' and log.reserve=false")


    @Query("select m from Matchs m where m.resultatMatch IS null ")
    List<Matchs> notYetPlayedMatchs();

    @Query("select m from Matchs m where m.resultatMatch IS NOT NULL ")
    List<Matchs> playedMatchs();

    @Query("select m from Matchs m where m.competition= :idCompetition ")
    List<Matchs> MatchsOfCompetition(@Param("idCompetition") int idCompetition);

    /*@Query("select m.club1 from Matchs m where m.competition= :idCompetition ")
    List<Matchs> ClubsFromMatchs(@Param("idCompetition") int idCompetition);*/




}

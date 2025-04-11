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

    @Query("select m from Matchs m where m.competition.idCompetition = :idCompetition ")
    List<Matchs> MatchsOfCompetition(@Param("idCompetition") int idCompetition);



    @Query("select m from Matchs m where m.competition.idCompetition= :idCompetition")
    List<Matchs> matchsOfCompetition(@Param("idCompetition") int idCompetition);

    /*@Query("select m.club1 from Matchs m where m.competition= :idCompetition ")
    List<Matchs> ClubsFromMatchs(@Param("idCompetition") int idCompetition);*/






    @Query("SELECT COALESCE(SUM(m.goals1), 0) FROM Matchs m WHERE m.club1.idClub = :idClub AND m.competition.idCompetition = :idCompetition")
    Integer goalsHome(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);

    @Query("SELECT COALESCE(SUM(m.goals1), 0) FROM Matchs m WHERE m.club2.idClub = :idClub AND m.competition.idCompetition = :idCompetition")
    Integer goalsAway(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);


    @Query("""
    SELECT COALESCE(SUM(
        CASE 
            WHEN m.club1.idClub = :idClub THEN m.goals1
            WHEN m.club2.idClub = :idClub THEN m.goals2
            ELSE 0
        END
    ), 0)
    FROM Matchs m
    WHERE m.competition.idCompetition = :idCompetition
""")
    Integer totalGoals(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);

    @Query("""
    SELECT COALESCE(SUM(
        CASE 
            WHEN m.club1.idClub = :idClub THEN m.goals2
            WHEN m.club2.idClub = :idClub THEN m.goals1
            ELSE 0
        END
    ), 0)
    FROM Matchs m
    WHERE m.competition.idCompetition = :idCompetition
""")
    Integer againstGoals(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);



    @Query("""
    SELECT COALESCE(COUNT(m), 0) 
    FROM Matchs m
    Where m.winner.idClub = :idClub
    and m.competition.idCompetition = :idCompetition
""")
    Integer totalWins(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);


    @Query("""
    SELECT COALESCE(COUNT(m), 0)  
    FROM Matchs m
    WHERE (m.club1.idClub = :idClub OR m.club2.idClub = :idClub)
    AND m.competition.idCompetition = :idCompetition
""")
    Integer playedMatchs(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);



    @Query("""
    SELECT COALESCE(COUNT(m), 0) 
    FROM Matchs m
    WHERE (m.club1.idClub = :idClub OR m.club2.idClub = :idClub)
      AND m.winner IS NULL
      AND m.competition.idCompetition = :idCompetition
""")
    Integer totalDraws(@Param("idCompetition") int idCompetition, @Param("idClub") int idClub);






}

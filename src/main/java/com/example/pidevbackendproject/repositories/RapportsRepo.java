package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RapportsRepo extends JpaRepository<Rapports, Integer> {

    @Query(" SELECT r FROM Rapports r JOIN r.joueursrapport j where j.numeroJoueur= :numeroJoueur")
    List<Rapports> findRapportByNumeroJoueur(@Param("numeroJoueur") int numeroJoueur);

    @Query(" SELECT r FROM Rapports r JOIN r.sousGroupesrapport j WHERE j.nameSousGroup = :nameSousGroup")
    List<Rapports> findRapportBySousGroupe(@Param("nameSousGroup") String nameSousGroup);

    @Query(" SELECT r FROM Rapports r JOIN r.seancesrapport rs WHERE rs.titleSeance = :titleSeance")
    List<Rapports> findRapportBytitleSeance(@Param("titleSeance") String titleSeance);

    @Query("SELECT r FROM Rapports r WHERE r.joueursrapport.numeroJoueur = :numero AND r.sousGroupesrapport.nameSousGroup = :nomGroupe AND r.seancesrapport.titleSeance = :titreSeance")
    List<Rapports> findByJoueurAndGroupeAndSeance(@Param("numero") int numero, @Param("nomGroupe") String nomGroupe, @Param("titreSeance") String titreSeance);


    @Query("SELECT r FROM Rapports r WHERE r.seancesrapport.titleSeance = :titleSeance " +
            "AND r.sousGroupesrapport.idSousGroup = :sousGroupId " +
            "AND r.joueursrapport.numeroJoueur = :numeroJoueur")
    List<Rapports> findBySeanceTitleAndSousGroupAndJoueurNumero(
            @Param("titleSeance") String titleSeance,
            @Param("sousGroupId") int sousGroupId,
            @Param("numeroJoueur") int numeroJoueur);

    @Query("SELECT r FROM Rapports r WHERE r.joueursrapport.numeroJoueur = :numeroJoueur")
    List<Rapports> findByJoueurNumero(@Param("numeroJoueur") int numeroJoueur);

    List<Rapports> findByDateRapport(LocalDate date);

    List<Rapports> findByJoueursrapport(Joueurs joueur);

    List<Rapports> findAllByJoueursrapport_PosteJoueur(String poste);

    @Query("SELECT r FROM Rapports r WHERE r.joueursrapport = :joueur AND LOWER(r.seancesrapport.titleSeance) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Rapports> findByJoueurAndSessionTitleContainsIgnoreCase(@Param("joueur") Joueurs joueur, @Param("title") String title);

    @Query(" SELECT r FROM Rapports r JOIN r.seancesrapport j join r.joueursrapport jj WHERE j.titleSeance = :titleSeance and jj.posteJoueur=:poste")
    List<Rapports> findByRapport(String poste, String titleSeance);

    List<Rapports> findByJoueursrapport_PosteJoueurAndJoueursrapport_NumeroJoueur(String poste, int numero);

    @Query("SELECT r FROM Rapports r WHERE r.joueursrapport.numeroJoueur = :numero AND r.seancesrapport.titleSeance = :title AND r.joueursrapport.posteJoueur = :poste")
    List<Rapports> findByNumeroAndSessionAndPoste(int numero, String title, String poste);

    List<Rapports> findAllBySeancesrapport_TitleSeance(String titleSeance);

    List<Rapports> findAllBySeancesrapport_TitleSeanceAndJoueursrapport_PosteJoueur(String titleSeance, String poste);

    List<Rapports> findAllByJoueursrapport(Joueurs joueur);

    @Query("""
    SELECT r FROM Rapports r
    WHERE r.joueursrapport.posteJoueur = :poste
      AND r.seancesrapport.titleSeance = :titleSeance
      AND r.etatRapport = com.example.pidevbackendproject.entities.etatplayer.GOOD
      AND r.joueursrapport.numeroJoueur = (
          SELECT r2.joueursrapport.numeroJoueur FROM Rapports r2
          WHERE r2.joueursrapport.posteJoueur = :poste
            AND r2.seancesrapport.titleSeance = :titleSeance
            AND r2.etatRapport = com.example.pidevbackendproject.entities.etatplayer.GOOD
          GROUP BY r2.joueursrapport.numeroJoueur
          ORDER BY AVG(
              COALESCE(r2.speedRapport, 0) + COALESCE(r2.accelerationRapport, 0) +
              COALESCE(r2.endurance, 0) + COALESCE(r2.muscularEndurance, 0) +
              COALESCE(r2.aerobicCapacity, 0) + COALESCE(r2.anaerobicCapacity, 0) +
              COALESCE(r2.strength, 0) + COALESCE(r2.power, 0) + COALESCE(r2.explosiveness, 0) +
              COALESCE(r2.verticalJump, 0) + COALESCE(r2.horizontalJump, 0) +
              COALESCE(r2.agility, 0) + COALESCE(r2.balance, 0) + COALESCE(r2.coordination, 0) +
              COALESCE(r2.reactionTime, 0) + COALESCE(r2.reactivity, 0)
          ) DESC
          LIMIT 1
      )
    ORDER BY (
        COALESCE(r.speedRapport, 0) + COALESCE(r.accelerationRapport, 0) +
        COALESCE(r.endurance, 0) + COALESCE(r.muscularEndurance, 0) +
        COALESCE(r.aerobicCapacity, 0) + COALESCE(r.anaerobicCapacity, 0) +
        COALESCE(r.strength, 0) + COALESCE(r.power, 0) + COALESCE(r.explosiveness, 0) +
        COALESCE(r.verticalJump, 0) + COALESCE(r.horizontalJump, 0) +
        COALESCE(r.agility, 0) + COALESCE(r.balance, 0) + COALESCE(r.coordination, 0) +
        COALESCE(r.reactionTime, 0) + COALESCE(r.reactivity, 0)
    ) DESC
    LIMIT 1
""")
    Rapports findBestGoodEtatPlayerByPosteAndSeance(@Param("poste") String poste, @Param("titleSeance") String titleSeance);

    @Query("""
    SELECT r FROM Rapports r
    WHERE r.seancesrapport.titleSeance = :titleSeance
      AND r.etatRapport = com.example.pidevbackendproject.entities.etatplayer.GOOD
      AND r.joueursrapport.numeroJoueur = (
          SELECT r2.joueursrapport.numeroJoueur FROM Rapports r2
          WHERE r2.seancesrapport.titleSeance = :titleSeance
            AND r2.etatRapport = com.example.pidevbackendproject.entities.etatplayer.GOOD
          GROUP BY r2.joueursrapport.numeroJoueur
          ORDER BY AVG(
              COALESCE(r2.speedRapport, 0) + COALESCE(r2.accelerationRapport, 0) +
              COALESCE(r2.endurance, 0) + COALESCE(r2.muscularEndurance, 0) +
              COALESCE(r2.aerobicCapacity, 0) + COALESCE(r2.anaerobicCapacity, 0) +
              COALESCE(r2.strength, 0) + COALESCE(r2.power, 0) + COALESCE(r2.explosiveness, 0) +
              COALESCE(r2.verticalJump, 0) + COALESCE(r2.horizontalJump, 0) +
              COALESCE(r2.agility, 0) + COALESCE(r2.balance, 0) + COALESCE(r2.coordination, 0) +
              COALESCE(r2.reactionTime, 0) + COALESCE(r2.reactivity, 0)
          ) DESC
          LIMIT 1
      )
""")
    List<Rapports> findBestGoodEtatPlayerBySeance(@Param("titleSeance") String titleSeance);



    @Query("""

            SELECT r FROM Rapports r
WHERE r.joueursrapport.posteJoueur = :poste
  AND r.seancesrapport.titleSeance = :titleSeance
  AND r.joueursrapport.sousGroupe.nameSousGroup = :nomSousGroupe
  AND r.joueursrapport.numeroJoueur = (
      SELECT r2.joueursrapport.numeroJoueur FROM Rapports r2
      WHERE r2.joueursrapport.posteJoueur = :poste
        AND r2.seancesrapport.titleSeance = :titleSeance
        AND r2.joueursrapport.sousGroupe.nameSousGroup = :nomSousGroupe
      GROUP BY r2.joueursrapport.numeroJoueur
      ORDER BY AVG(
          COALESCE(r2.speedRapport, 0) + COALESCE(r2.accelerationRapport, 0) +
          COALESCE(r2.endurance, 0) + COALESCE(r2.muscularEndurance, 0) +
          COALESCE(r2.aerobicCapacity, 0) + COALESCE(r2.anaerobicCapacity, 0) +
          COALESCE(r2.strength, 0) + COALESCE(r2.power, 0) + COALESCE(r2.explosiveness, 0) +
          COALESCE(r2.verticalJump, 0) + COALESCE(r2.horizontalJump, 0) +
          COALESCE(r2.agility, 0) + COALESCE(r2.balance, 0) + COALESCE(r2.coordination, 0) +
          COALESCE(r2.reactionTime, 0) + COALESCE(r2.reactivity, 0)
      ) DESC
      LIMIT 1
)
""")
    List<Rapports> findBestPlayersByPosteSeanceAndSousGroupe(
            @Param("poste") String poste,
            @Param("titleSeance") String titleSeance,
            @Param("nomSousGroupe") String nomSousGroupe
    );


    @Query("""
    SELECT r FROM Rapports r 
    JOIN FETCH r.joueursrapport j 
    WHERE r.etatRapport = com.example.pidevbackendproject.entities.etatplayer.BAD 
      AND j.posteJoueur = :poste
""")
    List<Rapports> findBadReportsByPoste(@Param("poste") String poste);

    @Query("""
    SELECT r2 FROM Rapports r1
    JOIN r1.joueursrapport j1,
         Rapports r2
    JOIN r2.joueursrapport j2
    WHERE j1.posteJoueur = :poste
    AND j1.numeroJoueur = :numero
    AND r1.etatRapport = 'GOOD'
    AND r2.etatRapport = 'GOOD'
    AND j2.posteJoueur = :poste
    AND j2.numeroJoueur <> :numero
    AND (
        (
            (r2.speedRapport + r2.accelerationRapport + r2.endurance +
             r2.muscularEndurance + r2.aerobicCapacity + r2.anaerobicCapacity +
             r2.strength + r2.power + r2.explosiveness +
             r2.verticalJump + r2.horizontalJump + r2.agility +
             r2.balance + r2.coordination + r2.reactionTime + r2.reactivity) / 16.0
        )
        >=
        (
            (r1.speedRapport + r1.accelerationRapport + r1.endurance +
             r1.muscularEndurance + r1.aerobicCapacity + r1.anaerobicCapacity +
             r1.strength + r1.power + r1.explosiveness +
             r1.verticalJump + r1.horizontalJump + r1.agility +
             r1.balance + r1.coordination + r1.reactionTime + r1.reactivity) / 16.0
        ) * 0.9
    )
""")
    List<Rapports> findSimilarOrBetterRapportsGoodEtatByPoste(@Param("poste") String poste, @Param("numero") int numero);
    Rapports findTopByJoueursrapportOrderByDateRapportDesc(Joueurs joueur);
    @Query("SELECT r FROM Rapports r WHERE r.joueursrapport.posteJoueur = :poste AND r.etatRapport = :etat")
    List<Rapports> findByPosteEtEtat(@Param("poste") String poste, @Param("etat") etatplayer etat);


    List<Rapports> findBySeancesrapport(Seances seance);

}
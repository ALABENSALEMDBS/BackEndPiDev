package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Formations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationsRepo extends JpaRepository<Formations, Integer> {


    @Query("SELECT f FROM Formations f " +
            "JOIN f.joueurs j " +
            "WHERE SIZE(f.joueurs) BETWEEN 11 AND 16 " +
            "GROUP BY f.idFormation " +
//            "HAVING COUNT(CASE WHEN j.posteJoueur = 'Gardien' THEN 1 END) = 1 " +
            "HAVING COUNT(CASE WHEN j.posteJoueur = 'Gardien' THEN 1 END) >= 0 " +
            "AND COUNT(CASE WHEN j.posteJoueur = 'Défenseur' THEN 1 END) >= 0 " +
            "AND COUNT(CASE WHEN j.posteJoueur = 'Milieu' THEN 1 END) >= 0 " +
            "AND COUNT(CASE WHEN j.posteJoueur= 'Attaquant' THEN 1 END) >= 0 "
    )
    List<Formations> findFormationsWith11PlayersAndCompatiblePositions();

}

package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Rapports;
import com.example.pidevbackendproject.entities.SousGroupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SousGroupesRepo extends JpaRepository<SousGroupes, Integer> {
    SousGroupes findByNameSousGroup(String nameSousGroup);
    @Query("SELECT sg FROM SousGroupes sg JOIN sg.exercices e WHERE e.seanceExercice.titleSeance = :titleSeance")
    List<SousGroupes> findSousGroupestitleSeance(@Param("titleSeance") String titleSeance);

    @Query("SELECT sg FROM SousGroupes sg JOIN sg.exercices e WHERE e.idExercice = :idExercice")
    List<SousGroupes> findSousGroupesidExercice(@Param("idExercice") int idExercice);

}

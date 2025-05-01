package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Seances;
import com.example.pidevbackendproject.entities.SousGroupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeancesRepo extends JpaRepository<Seances, Integer> {
    Seances findByTitleSeance(String titleSeance);
    @Query("SELECT sg FROM Seances sg  JOIN sg.exercises ee WHERE ee.idExercice = :idExercice")
    List<Seances> findSousSeances(@Param("idExercice") int idExercice);


}

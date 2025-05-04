package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Exercices;
import com.example.pidevbackendproject.entities.ExerciseType;
import com.example.pidevbackendproject.entities.Seances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicesRepo extends JpaRepository<Exercices, Integer> {
    int countBySeanceExercice(Seances seance);
    List<Exercices> findBySeanceExerciceIdSeance(int seanceId);
    // Find exercises associated with a specific session
    List<Exercices> findBySeanceExercice_IdSeance(int idSeance);

    // Find exercises not associated with any session
    @Query("SELECT e FROM Exercices e WHERE e.seanceExercice IS NULL")
    List<Exercices> findExercicesWithNoSeance();

    // Find exercises associated with sessions other than the specified one
    @Query("SELECT e FROM Exercices e WHERE e.seanceExercice IS NOT NULL AND e.seanceExercice.idSeance != :idSeance")
    List<Exercices> findExercicesAssignedToOtherSeances(@Param("idSeance") int idSeance);

    // Find all exercises except those assigned to other sessions
    @Query("SELECT e FROM Exercices e WHERE e.seanceExercice IS NULL OR e.seanceExercice.idSeance = :idSeance")
    List<Exercices> findAvailableExercices(@Param("idSeance") int idSeance);

    List<Exercices> findByTypeExercice(ExerciseType typeExercice);

}

package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
import com.example.pidevbackendproject.entities.Consultation;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {
    @Query("SELECT new com.example.pidevbackendproject.Dto.consultationDto( " +
            "f.id, f.dateConsultation,f.description, CONCAT(COALESCE(j.prenomUser, ''), ' ', COALESCE(j.nameUsers, '')), j.idUser) " +
            "FROM Consultation f " +
            "LEFT JOIN f.joueur j")
    List<consultationDto> findAllWithJoueurFullNameConsultation();

    boolean existsByDateConsultationAndIdNot(LocalDateTime dateConsultation, Long joueurId);

}

package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.Dto.ficheMedicaleDto;
import com.example.pidevbackendproject.entities.FicheMedicales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FicheMedicalesRepo extends JpaRepository<FicheMedicales, Integer> {
    @Query("SELECT new com.example.pidevbackendproject.Dto.ficheMedicaleDto( " +
            "f.idFicheMedicale, f.poidsFicheMedicale, f.tailleFicheMedicale, f.dateBlessure, " +
            "f.gravite, f.type, j.idUser, CONCAT(COALESCE(j.prenomUser, ''), ' ', COALESCE(j.nameUsers, ''))) " +
            "FROM FicheMedicales f " +
            "LEFT JOIN f.joueurficheMedicale j") // Ensure this matches the entity field
    List<ficheMedicaleDto> findAllWithJoueurFullName();
}

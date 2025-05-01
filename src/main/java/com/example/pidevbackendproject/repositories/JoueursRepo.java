package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Joueurs;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoueursRepo extends JpaRepository<Joueurs, Integer>{
    Joueurs findByNumeroJoueur (int numeroJoueur);

    //Joueurs findByNameUsersAndPrenomUser(String NameUser,String PrenomUser);

    Optional<Joueurs> findByNameUsersAndPrenomUser(String name, String prenom);

    @Query("SELECT j FROM Joueurs j WHERE j.ficheMedicale IS NULL")
    List<Joueurs> findJoueursWithoutFicheMedicale();
    Joueurs findByRapportIdRapport(int rapportId);

    Optional<Joueurs> getByIdUser(int idUser);


    // @Query("SELECT u FROM Users u JOIN u.nameUsers p  WHERE u.club.nameClub = :clubName")
   // List<User> findUsersWithPlayerByClubName(@Param("clubName") String clubName);
}

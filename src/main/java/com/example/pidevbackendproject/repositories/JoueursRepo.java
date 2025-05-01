package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Joueurs;
import org.apache.catalina.User;
import com.example.pidevbackendproject.entities.Rapports;
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
    @Query(" SELECT j FROM SousGroupes r JOIN r.joueurs j  where j.numeroJoueur= :numeroJoueur")
    Joueurs findJoueursByNumeroJoueur(@Param("numeroJoueur") int numeroJoueur);

    @Query(" SELECT r FROM Joueurs r JOIN r.sousGroupe j  where j.nameSousGroup= :nameSousGroup")
    List<Joueurs> findJoueursBynameSousGroup(@Param("nameSousGroup") String nameSousGroup);
    @Query("SELECT r FROM Rapports r JOIN r.joueursrapport j WHERE r.idRapport= :idRapport AND r.joueursrapport.numeroJoueur = :numeroJoueur")
    Rapports findJoueursRapports(@Param("idRapport") int idRapport, @Param("numeroJoueur") int numeroJoueur);
    List<Joueurs> findByPosteJoueur(String posteJoueur);

    @Query("SELECT r FROM Rapports r JOIN r.joueursrapport j JOIN r.sousGroupesrapport jg JOIN r.seancesrapport js WHERE  j.numeroJoueur = :numeroJoueur AND j.posteJoueur = :posteJoueur ")
    List<Rapports> findJoueursRapportsnumeroJoueurposteJoueur(
            @Param("numeroJoueur") int numeroJoueur,
            @Param("posteJoueur") String posteJoueur);

    @Query("SELECT sg FROM Joueurs sg JOIN sg.sousGroupe e JOIN e.exercices ee WHERE ee.idExercice = :idExercice")
    List<Joueurs> findSousGroupesJoueurs(@Param("idExercice") int idExercice);

    Optional<Joueurs> findByNameUsersAndPrenomUser(String name, String prenom);

    @Query("SELECT j FROM Joueurs j WHERE j.ficheMedicale IS NULL")
    List<Joueurs> findJoueursWithoutFicheMedicale();
    // Joueurs findByRapportIdRapport(int rapportId);

    Optional<Joueurs> getByIdUser(int idUser);


    // @Query("SELECT u FROM Users u JOIN u.nameUsers p  WHERE u.club.nameClub = :clubName")
   // List<User> findUsersWithPlayerByClubName(@Param("clubName") String clubName);

    Joueurs findByPosteJoueurAndNumeroJoueur(String poste, int numero);

}

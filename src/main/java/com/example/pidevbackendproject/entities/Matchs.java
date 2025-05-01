package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Matchs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatch;
    private String resultatMatch;
    private String dateMatch;  // Nouveau attribut pour la date du match
    private String lieuMatch;  // Nouveau attribut pour le lieu du match
    private String statusMatch; // Nouveau attribut pour l'état du match (par exemple "en cours", "terminé")
    private String typeMatch;   // Nouveau attribut pour le type du match (par exemple "amical", "championnat")
    private String arbitre;


    private Integer goals1;
    private Integer goals2;
    /*@Transient*/


    public void updateResultat(){
        if(goals1!=null && goals2!=null){
            resultatMatch = goals1+" - "+goals2;
        }
    }




    @ManyToOne
    @JoinColumn(name = "winner_id")  // Foreign key in the Matchs table referencing Clubs
    private Clubs winner;

    public Clubs theWinner(){
        winner=null;
        if ((goals1==null) || (goals2 == null)){
            return null;
        }

        if(resultatMatch!=null && resultatMatch.length()>=3){
            if(  goals1>goals2 || (resultatMatch.charAt(0)>resultatMatch.charAt(4)) ){
                winner = getClub1();
            }
            else if(  goals1<goals2 || (resultatMatch.charAt(0)<resultatMatch.charAt(4)) ){
                winner = getClub2();
            }
            return null;
        }
        return null;
    }




    //private String equipe1;
    //private String equipe2;

    /*@ManyToOne
    @JoinColumn(name = "equipe_1_id_club")
    private Clubs equipe1;*/

    /*@ManyToOne
    @JoinColumn(name = "equipe_2_id_club")
    private Clubs equipe2;*/

    @Lob
    @Column(length = 100000)
    private byte[] displayPicture;

    @JsonIgnore
    @ManyToOne
    Tournois tournoi;




    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "competition_id")
    Competition competition;




    @JsonIgnore
    @OneToOne
    StatistiqueMatchs statistiqueMatches;

    @JsonIgnore
    @OneToOne
    EvenementsGlobales evenementsGlobale;

    @JsonIgnore
    @OneToOne
    Formations formation;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "club1_id")
    private Clubs club1;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "club2_id")
    private Clubs club2;


    @ManyToOne
    @JoinColumn(name = "id_cup")
    private Cup cup;


    private String roundName;


}

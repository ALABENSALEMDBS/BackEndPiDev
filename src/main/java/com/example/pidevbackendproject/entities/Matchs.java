package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

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


    /*private int goals1;
    private int goals2;
    @Transient
    public String resultatMatch(){
        String goalsStr1 = String.valueOf(goals1);
        String goalsStr2 = String.valueOf(goals2);
        return goalsStr1 + " - " + goalsStr2;
    }*/




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
}

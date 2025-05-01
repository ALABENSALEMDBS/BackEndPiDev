package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idClub;
    String nameClub;
    //String logoClub;
    String emailClub;
    String adressClub;
    Date dateClub;
    int licenceClub;
    String mediaUrl;

    @Lob
    @Column(length = 100000)
    private byte[] logo;

    //relation of two clubs
    /*@OneToMany(mappedBy = "equipe1")
    private List<Matchs> matchesAsEquipe1;

    @OneToMany(mappedBy = "equipe2")
    private List<Matchs> matchesAsEquipe2;*/



/*
    @OneToOne(cascade = CascadeType.ALL)
    private Matchs matches;
    //private Clubs clubb;
*/


    /*@JsonIgnore
    @ManyToMany(mappedBy = "matchClub")
    Set<Clubs> matchs;*/



    @JsonIgnore
    @OneToMany (mappedBy = "club")
    Set<Users> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "club1")
    private List<Matchs> matchesOfClub1 = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "club2")
    private List<Matchs> matchesOfClub2 = new ArrayList<>();










}

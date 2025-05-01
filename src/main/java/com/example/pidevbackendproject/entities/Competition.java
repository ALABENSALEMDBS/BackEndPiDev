package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCompetition;

    @Column(name = "name_competition")
    String nameCompetition;

    @Enumerated(EnumType.STRING)
    private TypeCompetition TypeC;


    /*@OneToMany(mappedBy = "competition")
    //@Column(name="liste_Des_Matchs")
    @JsonIgnore
    Set<Matchs> matchesTournoi =  new HashSet<>();*/


    @OneToMany(mappedBy = "competition" ,cascade = CascadeType.ALL)
    //@JsonIgnore // Retire cette ligne si tu veux que les matchs soient renvoyés dans la réponse JSON
    Set<Matchs> matchesTournoi = new HashSet<>();

    /*@OneToOne
    Standing standing;*/




    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Standings> standings;







}






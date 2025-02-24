package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournois {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTournoi;
    String nameTournoi;
    Date debutTournoi;
    Date finTournoi;
@JsonIgnore
    @OneToMany(mappedBy = "tournoi")
    Set<Matchs> matchesTournoi =  new HashSet<>();
}

package com.example.pidevbackendproject.entities;

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
public class Tournois {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTournoi;
    String nameTournoi;

    @OneToMany(mappedBy = "tournoi")
    Set<Matchs> matchesTournoi;
}

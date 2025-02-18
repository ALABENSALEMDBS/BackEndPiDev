package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatistiqueMatchs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idStatistiqueMatch;
    String resultatStatistiqueMatch;
    int possessionStatistiqueMatch;
    int tirStatistiqueMatch;

    @OneToOne(mappedBy = "statistiqueMatches")
    Matchs matchStatistiqueMatch;
}

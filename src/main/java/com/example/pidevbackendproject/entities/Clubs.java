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
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idClub;
    String nameClub;
    String logoClub;
    String emailClub;
    String addressClub;
    int licenceClub;


    @OneToMany (mappedBy = "club")
    Set<Users> users;

    @ManyToOne
    Matchs matchClub;

}

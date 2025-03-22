package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Lob
    @Column(length = 100000)
    private byte[] logo;


    @JsonIgnore
    @OneToMany (mappedBy = "club")
    Set<Users> users = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    Matchs matchClub;


}

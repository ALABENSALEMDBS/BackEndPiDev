package com.example.pidevbackendproject.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Cup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCup;


    private String name;


    @OneToMany(mappedBy = "cup")
    private List<Matchs> matchs;



}

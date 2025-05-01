package com.example.pidevbackendproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonIgnore
    @OneToMany(mappedBy = "cup" , cascade = CascadeType.ALL)
    private List<Matchs> matchs;



}

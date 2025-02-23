package com.example.pidevbackendproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Tactics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTactic;
    String nameTactic;
    String descriptionTactic;
    String photoTactic;
    String videoTactic;
@JsonIgnore
    @ManyToOne
    Formations formation;
}

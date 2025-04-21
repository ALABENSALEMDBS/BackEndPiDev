package com.example.pidevbackendproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String title;
    private String description;
//
//    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
//    private List<Comment> comments;
}

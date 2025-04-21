package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}

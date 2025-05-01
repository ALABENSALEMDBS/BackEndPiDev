package com.example.pidevbackendproject.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoService implements IVideoService{
    @Override
    public String uploadVideo(Path root, MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), root.resolve(fileName));
        return "Fichier téléchargé avec succès : " + fileName;
    }

    @Override
    public List<String> getAllVideos(Path root) throws IOException {
        List<String> videos = Files.list(root)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        return videos;
    }

    @Override
    public Resource streamVideo(Path root, String filename) throws MalformedURLException {
        Path file = root.resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }
}

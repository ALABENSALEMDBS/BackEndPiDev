package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.services.IVideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final Path root = Paths.get("uploads");

    @Autowired
    private IVideoService videoService;

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(root);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(videoService.uploadVideo(root, file));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllVideos() throws IOException {
        return ResponseEntity.ok(videoService.getAllVideos(root));
    }

    @GetMapping("/stream/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(videoService.streamVideo(root, filename));
    }
}


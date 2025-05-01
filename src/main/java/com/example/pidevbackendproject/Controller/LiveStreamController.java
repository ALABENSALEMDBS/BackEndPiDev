package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.LiveStream;
import com.example.pidevbackendproject.services.LiveStreamService;
import com.example.pidevbackendproject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livestreams")
public class LiveStreamController {

    private final LiveStreamService liveStreamService;
    private final RoomService roomService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public LiveStreamController(LiveStreamService liveStreamService, RoomService roomService, SimpMessagingTemplate messagingTemplate) {
        this.liveStreamService = liveStreamService;
        this.roomService = roomService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/start/{roomId}")
    public ResponseEntity<LiveStream> startLiveStream(@PathVariable String roomId) {
        try {
            LiveStream liveStream = liveStreamService.startLiveStream(roomId);
            return ResponseEntity.ok(liveStream);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/end/{roomId}")
    public ResponseEntity<LiveStream> endLiveStream(@PathVariable String roomId) {
        try {
            LiveStream liveStream = liveStreamService.endLiveStream(roomId);
            return ResponseEntity.ok(liveStream);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LiveStream>> getAllLiveStreams() {
        return ResponseEntity.ok(liveStreamService.getAllLiveStreams());
    }

    @GetMapping("/active")
    public ResponseEntity<List<LiveStream>> getActiveLiveStreams() {
        return ResponseEntity.ok(liveStreamService.getActiveLiveStreams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiveStream> getLiveStream(@PathVariable String id) {
        Optional<LiveStream> liveStream = liveStreamService.findLiveStream(id);
        return liveStream.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<LiveStream> getLiveStreamByRoomId(@PathVariable String roomId) {
        Optional<LiveStream> liveStream = liveStreamService.findLiveStreamByRoomId(roomId);
        return liveStream.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Nouveau endpoint pour uploader l'enregistrement complet
    @PostMapping("/upload/{roomId}")
    public ResponseEntity<String> uploadRecording(@PathVariable String roomId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier vide");
        }

        try {
            // Créer le répertoire des enregistrements s'il n'existe pas
            Path dirPath = Paths.get("uploads");
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Générer un nom de fichier unique
            String filename = roomId + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".webm";
            Path filePath = dirPath.resolve(filename);

            // Enregistrer le fichier
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Mettre à jour le chemin d'enregistrement dans la room
            liveStreamService.setRecordingPath(roomId, filePath.toString());

            return ResponseEntity.ok("Enregistrement téléchargé avec succès: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement du fichier: " + e.getMessage());
        }
    }
}
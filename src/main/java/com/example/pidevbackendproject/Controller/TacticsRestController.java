//**
package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Tactics;
import com.example.pidevbackendproject.services.ITacticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
@Tag(name = "Gestion Tactics")
@RestController
@AllArgsConstructor
@RequestMapping("/tactics")
public class TacticsRestController {
    ITacticsService tacticsService;

    @Operation(description = "Ajouter un Tactic")
    @PostMapping("/add-tactics")
    public Tactics addTactics(@RequestBody Tactics t) {
        return tacticsService.addTactics(t);
    }

    @Operation(description = "Récupérer toutes les tactics de la base de données")
    @GetMapping(value = "/retrieve-all-tactics")
    public List<Tactics> getAllTactics() {
        List<Tactics> tactics= tacticsService.getAllTactics();
        return tactics;
    }

    @Operation(description = "Récupérer les tactics de la base de données by ID")
    @GetMapping("/retrieve-tactics/{tactics-id}")
    public Tactics retrieveTactics(@PathVariable("tactics-id") int idTactics) {
        Tactics tactics = tacticsService.getTacticsById(idTactics);
        return tactics;
    }

    @Operation(description = "Supprimer Tactics by ID")
    @DeleteMapping("/remove-tactics/{tactics-id}")
    public void deleteTactics(@PathVariable("tactics-id") int idTactics) {
        tacticsService.deleteTactics(idTactics);
    }

    @Operation(description = "Modifer Tactics")
    @PutMapping("/modify-tactics")
    public Tactics modifyTactics(@RequestBody Tactics t) {
        Tactics tactics= tacticsService.modifyTactics(t);
        return tactics;
    }






    private final String uploadDir = "C:/xampp/htdocs/tactics/";

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        // Check if the file is null or empty
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "No file was uploaded"), HttpStatus.BAD_REQUEST);
        }

        try {
            // Generate a unique file name
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String fileName = file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir, fileName); // Corrected path

            // Create the upload directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory including any necessary parent directories.
            }

            // Save the file
            Files.write(filePath, file.getBytes());

            // Create the file URL (IMPORTANT: Adjust this based on your webserver setup)
            String fileUrl = "http://localhost/tactics/" + fileName;  // IMPORTANT

            // Create a success response
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IOException e) {
            // Handle the exception (log it, etc.)
            System.err.println("Error uploading file: " + e.getMessage());  // Log the full error message
            e.printStackTrace();  // VERY IMPORTANT:  Log the stack trace for debugging
            return new ResponseEntity<>(Map.of("error", "Failed to upload file: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

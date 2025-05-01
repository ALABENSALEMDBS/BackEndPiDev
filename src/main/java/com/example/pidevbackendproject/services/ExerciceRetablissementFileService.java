package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import com.example.pidevbackendproject.repositories.ExerciceRetablissementsRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciceRetablissementFileService implements IExerciceRetablissementFile {
    @Autowired
    ExerciceRetablissementsRepo exerciceRetablissementsRepo;

    @Value("${file.path}")
    private String filePath;

    public String save(MultipartFile file) {
        // Define the directory where files will be stored
        String dir = System.getProperty("user.dir") + "/" + filePath;
        File directory = new File(dir);

        // Create the directory if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Define the file path including file name
        File targetFile = new File(dir + "/" + file.getOriginalFilename());
        try {
            // Save the file to the target location
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }

        /*// read file into list of ...
        List<ExerciceRetablissements> exercices = readExercicesFromCSV(targetFile.getAbsolutePath());
        // foreach and save
        exerciceRetablissementsRepo.saveAll(exercices);*/

        List<ExerciceRetablissements> importedExercices = readExercicesFromCSV(targetFile.getAbsolutePath());
        List<ExerciceRetablissements> newExercices = new ArrayList<>();

        for (ExerciceRetablissements exercice : importedExercices) {
            boolean exists = exerciceRetablissementsRepo
                    .existsByNomExerciceRetablissement(exercice.getNomExerciceRetablissement());

            if (!exists) {
                newExercices.add(exercice);
            }
        }

        exerciceRetablissementsRepo.saveAll(newExercices);

        // Return the name of the saved file
        return file.getOriginalFilename();
    }

    public List<ExerciceRetablissements> readExercicesFromCSV(String filePath) {
        List<ExerciceRetablissements> exercices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] fields = line.split(";");
                if (fields.length >= 4) {
                    ExerciceRetablissements exercice = new ExerciceRetablissements();
                    exercice.setNomExerciceRetablissement(fields[0]);
                    exercice.setDescriptionExerciceRetablissement(fields[1]);
                    exercice.setDureeExercice(Integer.parseInt(fields[2]));
                    exercice.setNiveauDifficulte(fields[3]);

                    exercices.add(exercice);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // or log it
        }

        return exercices;
    }

    @Override
    public Resource getFile(String fileName) {
        // Define the path to the file
        String dir = System.getProperty("user.dir") + "/" + filePath + "/" + fileName;
        Path path = Paths.get(dir);

        try {
            // Create a UrlResource for the file
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

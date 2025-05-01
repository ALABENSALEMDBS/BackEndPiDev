package com.example.pidevbackendproject.services;

import ai.onnxruntime.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class MealSuggestionService {

    private final OrtEnvironment env;
    private final OrtSession session;

    public MealSuggestionService() {
        try {
            env = OrtEnvironment.getEnvironment();
            OrtSession.SessionOptions opts = new OrtSession.SessionOptions();

            // Charger le modèle ONNX IA-Nourriture_model.onnx depuis les ressources
            InputStream modelStream = getClass().getClassLoader().getResourceAsStream("model/IA-Nourriture_model.onnx");

            if (modelStream == null) {
                throw new RuntimeException("Fichier IA-Nourriture_model.onnx introuvable dans resources");
            }

            Path tempFile = Files.createTempFile("model", ".onnx");
            Files.copy(modelStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            session = env.createSession(tempFile.toString(), opts);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'initialisation du modèle", e);
        }
    }

    public String predict(float weight, float height, String severity) {
        // Convertir la sévérité en valeur numérique
        float severityValue = switch (severity.toLowerCase()) {
            case "low" -> 0f;
            case "medium" -> 1f;
            case "high" -> 2f;
            default -> 0f;
        };

        // Données d'entrée pour la prédiction du modèle
        float[][] inputData = new float[][]{{weight, height, severityValue}};

        try (OnnxTensor inputTensor = OnnxTensor.createTensor(env, inputData)) {
            Map<String, OnnxTensor> inputMap = Map.of("input", inputTensor);

            // Exécution du modèle ONNX pour obtenir la prédiction
            OrtSession.Result result = session.run(inputMap);

            // Récupérer la sortie du modèle (index prédit)
            Object outputValue = result.get(0).getValue();

            // Vérification si la sortie est un tableau de long[]
            if (outputValue instanceof long[]) {
                long[] outputArray = (long[]) outputValue;

                // Accéder à l'index prédit
                int predictedIndex = (int) outputArray[0];

                // Retourner directement l'index sans mappage
                return String.valueOf(predictedIndex); // Retourner l'index sans utiliser un mappage interne

            } else {
                return "Erreur : la sortie n'est pas un tableau de long[]. Type: " + outputValue.getClass().getName();
            }

        } catch (Exception e) {
            return "Erreur lors de la prédiction : " + e.getMessage();
        }
    }
}

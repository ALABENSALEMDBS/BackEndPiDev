package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.entities.CommentType;
import com.example.pidevbackendproject.repositories.CommentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CommentAnalysisService {

    @Autowired
    private CommentRepository commentRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://api-inference.huggingface.co/models/distilbert-base-uncased-finetuned-sst-2-english";
    private final String hfToken = "Bearer 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";

    @Value("${huggingface.api.token}")
    private String apiToken;

    private final String HF_API_URL = "https://api-inference.huggingface.co/models/distilbert-base-uncased-finetuned-sst-2-english";

    public String analyzeCommentContent(String content) {
        // Vérification que le contenu n'est pas null ou vide
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Le contenu du commentaire ne peut pas être null ou vide.");
        }

        // Implement a simple local sentiment analysis as fallback
        String simpleSentiment = analyzeContentLocally(content);

        // Try the API with retries
        return callHuggingFaceWithRetry(content, simpleSentiment);
    }

    private String callHuggingFaceWithRetry(String content, String fallbackSentiment) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", hfToken);

        Map<String, String> body = Map.of("inputs", content);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        // Retry parameters
        int maxRetries = 3;
        int retryDelayMs = 1000; // 1 second

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Calling HuggingFace API, attempt " + attempt + " for content: " +
                    (content.length() > 30 ? content.substring(0, 30) + "..." : content));

                ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

                // Traitement de la réponse de HuggingFace
                String responseBody = response.getBody();
                if (responseBody == null || responseBody.isEmpty()) {
                    System.out.println("Empty response from HuggingFace API");
                    if (attempt < maxRetries) {
                        Thread.sleep(retryDelayMs);
                        continue;
                    }
                    return fallbackSentiment;
                }

                // La réponse peut être dans différents formats JSON
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // Afficher la réponse brute pour le débogage
                    System.out.println("Raw response from HuggingFace: " + responseBody);

                    // Format 1: Tableau imbriqué [[{...},...]]
                    try {
                        List<List<Map<String, Object>>> nestedList = objectMapper.readValue(responseBody,
                            new TypeReference<List<List<Map<String, Object>>>>() {});

                        if (!nestedList.isEmpty() && !nestedList.get(0).isEmpty()) {
                            String label = (String) nestedList.get(0).get(0).get("label");
                            if (label != null) {
                                System.out.println("HuggingFace API returned (nested format): " + label + " for content: " +
                                    (content.length() > 30 ? content.substring(0, 30) + "..." : content));
                                return label.toUpperCase(); // Return POSITIVE or NEGATIVE
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Not a nested array format: " + e.getMessage());
                    }

                    // Format 2: Tableau simple [{...},...]
                    try {
                        List<Map<String, Object>> responseList = objectMapper.readValue(responseBody,
                            new TypeReference<List<Map<String, Object>>>() {});

                        if (!responseList.isEmpty()) {
                            String label = (String) responseList.get(0).get("label");
                            if (label != null) {
                                System.out.println("HuggingFace API returned (array format): " + label + " for content: " +
                                    (content.length() > 30 ? content.substring(0, 30) + "..." : content));
                                return label.toUpperCase(); // Return POSITIVE or NEGATIVE
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Not a simple array format: " + e.getMessage());
                    }

                    // Format 3: Objet simple {...}
                    try {
                        Map<String, Object> responseMap = objectMapper.readValue(responseBody,
                            new TypeReference<Map<String, Object>>() {});

                        String label = (String) responseMap.get("label");
                        if (label != null) {
                            System.out.println("HuggingFace API returned (object format): " + label + " for content: " +
                                (content.length() > 30 ? content.substring(0, 30) + "..." : content));
                            return label.toUpperCase(); // Return POSITIVE or NEGATIVE
                        }
                    } catch (Exception e) {
                        System.out.println("Not an object format: " + e.getMessage());
                    }

                } catch (Exception ex) {
                    // Log the error
                    System.err.println("Error parsing HuggingFace response: " + ex.getMessage());
                    System.err.println("Response body: " + responseBody);
                }

                // If we got a response but couldn't parse it, try again
                if (attempt < maxRetries) {
                    Thread.sleep(retryDelayMs);
                    continue;
                }

                return fallbackSentiment;
            } catch (HttpClientErrorException e) {
                // Log the error from HuggingFace
                System.err.println("HuggingFace API error: " + e.getResponseBodyAsString());
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }
                return fallbackSentiment;
            } catch (Exception e) {
                // Log the general error
                System.err.println("Error on attempt " + attempt + ": " + e.getMessage());
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }
                return fallbackSentiment;
            }
        }

        // If all retries failed, use the fallback
        return fallbackSentiment;
    }

    private String analyzeContentLocally(String content) {
        // Simple keyword-based sentiment analysis as fallback
        content = content.toLowerCase();

        // Lists of positive and negative words
        String[] positiveWords = {"bon", "bien", "super", "excellent", "génial", "merci", "bravo", "j'aime", "aime",
                                 "parfait", "heureux", "content", "satisfait", "formidable", "fantastique", "agréable",
                                 "good", "great", "excellent", "awesome", "amazing", "thank", "thanks", "love", "perfect",
                                 "happy", "glad", "satisfied", "wonderful", "fantastic", "nice", "best", "better"};

        String[] negativeWords = {"mauvais", "mal", "horrible", "terrible", "nul", "pas bon", "déteste", "déçu",
                                 "décevant", "problème", "pire", "bad", "worst", "terrible", "horrible", "awful",
                                 "hate", "disappointed", "disappointing", "problem", "worse", "not good", "poor"};

        int positiveScore = 0;
        int negativeScore = 0;

        // Count positive and negative words
        for (String word : positiveWords) {
            if (content.contains(word)) {
                positiveScore++;
            }
        }

        for (String word : negativeWords) {
            if (content.contains(word)) {
                negativeScore++;
            }
        }

        // Determine sentiment based on scores
        if (positiveScore > negativeScore) {
            return "POSITIVE";
        } else if (negativeScore > positiveScore) {
            return "NEGATIVE";
        } else {
            return "NEUTRAL";
        }
    }

    public void classifyComment(Comment comment) {
        // Utilisation de la méthode d'analyse du commentaire
        String typeStr = analyzeCommentContent(comment.getContent());

        try {
            // Mapper la réponse de HuggingFace en CommentType
            // Convertir en majuscules pour assurer la compatibilité avec l'enum
            CommentType commentType = CommentType.valueOf(typeStr);
            comment.setType(commentType);
            System.out.println("Comment classified as: " + commentType + " - Content: " + comment.getContent());
        } catch (IllegalArgumentException e) {
            // Gestion d'une valeur non valide de type de commentaire
            comment.setType(CommentType.NEUTRAL);
            System.out.println("Invalid comment type: " + typeStr + " - Setting to NEUTRAL - Content: " + comment.getContent());
        }
    }

    public Comment classifyAndSaveComment(Comment comment) {
        // Classifier et sauvegarder le commentaire dans la base de données
        String typeStr = analyzeCommentContent(comment.getContent());

        try {
            CommentType commentType = CommentType.valueOf(typeStr);
            comment.setType(commentType);
            System.out.println("Comment classified and saved as: " + commentType + " - Content: " + comment.getContent());
        } catch (IllegalArgumentException e) {
            comment.setType(CommentType.NEUTRAL);
            System.out.println("Invalid comment type for save: " + typeStr + " - Setting to NEUTRAL - Content: " + comment.getContent());
        }

        return commentRepository.save(comment);
    }
}

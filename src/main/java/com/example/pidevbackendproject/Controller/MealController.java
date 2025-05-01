package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.services.MealSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealSuggestionService mealSuggestionService;

    @GetMapping("/suggest")
    public ResponseEntity<String> suggestMeal(@RequestParam float weight,
                                              @RequestParam float height,
                                              @RequestParam String severity) {
        try {
            String result = mealSuggestionService.predict(weight, height, severity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la prédiction : " + e.getMessage());
        }
    }
}

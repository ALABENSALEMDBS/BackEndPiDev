package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentAnalysisService {

    private final List<String> positiveWords = Arrays.asList("bravo", "super", "excellent", "génial", "bien joué");
    private final List<String> negativeWords = Arrays.asList("nul", "mauvais", "décevant", "horrible", "pourri");

    public String analyzeCommentContent(String content) {
        String lowerContent = content.toLowerCase();

        long positiveCount = positiveWords.stream().filter(lowerContent::contains).count();
        long negativeCount = negativeWords.stream().filter(lowerContent::contains).count();

        if (positiveCount > negativeCount) {
            return "POSITIVE";
        } else if (negativeCount > positiveCount) {
            return "NEGATIVE";
        } else {
            return "NEUTRAL";
        }
    }

    public void classifyComment(Comment comment) {
        String typeStr = analyzeCommentContent(comment.getContent());
        comment.setType(Enum.valueOf(com.example.pidevbackendproject.entities.CommentType.class, typeStr));
    }
}

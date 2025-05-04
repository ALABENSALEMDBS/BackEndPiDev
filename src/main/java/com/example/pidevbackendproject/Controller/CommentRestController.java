package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.entities.CommentType;
import com.example.pidevbackendproject.services.CommentAnalysisService;
import com.example.pidevbackendproject.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private CommentAnalysisService commentAnalysisService;

    @GetMapping("/{videoId}")
    public List<Comment> getComments(@PathVariable String videoId) {
        return commentService.getCommentsByVideoId(videoId);
    }

    @GetMapping("/stats/global")
    public Map<CommentType, Long> getGlobalStats() {
        return commentService.getGlobalStats();
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(@RequestBody Map<String, String> payload) {
        String content = payload.get("content");
        String result = commentAnalysisService.analyzeCommentContent(content);
        return ResponseEntity.ok(result);
    }

}

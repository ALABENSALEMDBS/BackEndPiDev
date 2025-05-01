package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.entities.CommentType;
import com.example.pidevbackendproject.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/{videoId}")
    public List<Comment> getComments(@PathVariable String videoId) {
        return commentService.getCommentsByVideoId(videoId);
    }

    @GetMapping("/stats/global")
    public Map<CommentType, Long> getGlobalStats() {
        return commentService.getGlobalStats();
    }
}

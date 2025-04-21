package com.example.pidevbackendproject.Controller;


import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/{videoId}")
    public List<Comment> getComments(@PathVariable String videoId) {
        return commentService.getCommentsByVideoId(videoId);
    }
}

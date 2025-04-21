package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByVideoId(String videoId) {
        return commentRepository.findByVideoIdOrderByTimestampAsc(videoId);
    }
}

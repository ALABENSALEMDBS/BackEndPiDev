package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.entities.CommentType;
import com.example.pidevbackendproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentAnalysisService analysisService;

    @Override
    public Comment saveComment(Comment comment) {
        analysisService.classifyComment(comment);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByVideoId(String videoId) {
        return commentRepository.findByVideoIdOrderByTimestampAsc(videoId);
    }

    public Map<CommentType, Long> getGlobalStats() {
        List<Comment> allComments = commentRepository.findAll();
        return allComments.stream()
                .filter(comment -> comment.getType() != null)
                .collect(Collectors.groupingBy(Comment::getType, Collectors.counting()));

    }
}

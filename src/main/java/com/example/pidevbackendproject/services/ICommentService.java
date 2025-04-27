package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.entities.CommentType;

import java.util.List;
import java.util.Map;

public interface ICommentService {

    Comment saveComment(Comment comment);
    List<Comment> getCommentsByVideoId(String videoId);
    Map<CommentType, Long> getGlobalStats() ;
}

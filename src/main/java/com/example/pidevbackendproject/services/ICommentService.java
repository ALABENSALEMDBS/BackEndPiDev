package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Comment;

import java.util.List;

public interface ICommentService {

    Comment saveComment(Comment comment);
    List<Comment> getCommentsByVideoId(String videoId);
}

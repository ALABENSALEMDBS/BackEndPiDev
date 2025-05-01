package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Comment;
import com.example.pidevbackendproject.services.ICommentService;
import com.example.pidevbackendproject.services.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class CommentWebSocketController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IVideoService videoService;

    @MessageMapping("/comment/{videoId}")
    @SendTo("/topic/comments/{videoId}")
    public Comment sendComment(@DestinationVariable String videoId, Comment comment) {
        comment.setVideoId(videoId);
        commentService.saveComment(comment); // Sauvegarde en base
        return comment;
    }
}



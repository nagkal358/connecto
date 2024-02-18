package com.trinet.connecto.service;

import com.trinet.connecto.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsForThread(Integer threadId);

    Comment getCommentById(Integer commentId);

    Comment addNewComment(Comment comment);
}

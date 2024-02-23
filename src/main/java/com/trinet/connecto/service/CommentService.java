package com.trinet.connecto.service;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.model.Like;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsForThread(Long threadId);

    Comment getCommentById(Long commentId);

    Comment addNewComment(Comment comment);

    Like likeOrDisLikeComment(Like like);
}

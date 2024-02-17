package com.trinet.connecto.repository;

import com.trinet.connecto.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAllCommentsForThread(Integer threadId);

    Comment getCommentById(Integer commentId);

    Comment addNewComment(Comment comment);
}

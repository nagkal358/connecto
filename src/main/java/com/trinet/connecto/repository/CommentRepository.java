package com.trinet.connecto.repository;

import com.trinet.connecto.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAllCommentsForThread(Long threadId);

    Comment getCommentById(Long commentId);

    Comment addNewComment(Comment comment);
}

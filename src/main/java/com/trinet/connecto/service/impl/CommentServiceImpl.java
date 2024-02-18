package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.repository.CommentRepository;
import com.trinet.connecto.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    public List<Comment> getAllCommentsForThread(Integer threadId){
        return commentRepository.getAllCommentsForThread(threadId);
    }

    public Comment getCommentById(Integer commentId){
        return commentRepository.getCommentById(commentId);
    }

    public Comment addNewComment(Comment comment){
        return commentRepository.addNewComment(comment);
    }
}

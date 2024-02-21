package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.repository.CommentRepository;
import com.trinet.connecto.repository.SequenceRepository;
import com.trinet.connecto.service.CommentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    SequenceRepository sequenceRepository;
    public List<Comment> getAllCommentsForThread(Long threadId){
        return commentRepository.getAllCommentsForThread(threadId);
    }

    public Comment getCommentById(Long commentId){
        return commentRepository.getCommentById(commentId);
    }

    @SneakyThrows
    public Comment addNewComment(Comment comment){
        comment.setId(sequenceRepository.getNextSequenceId("comment"));
        return commentRepository.addNewComment(comment);
    }
}

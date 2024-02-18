package com.trinet.connecto.controller;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "${application-version}"+"${comment-api.path}")
public class CommentsController {
    @Autowired
    CommentService commentService;
    @GetMapping(value = "/get-comments/{threadId}")
    public ResponseEntity<List<Comment>> getAllCommentsForThread(@PathVariable(required = true) Integer threadId){
        return new ResponseEntity<List<Comment>>(commentService.getAllCommentsForThread(threadId), HttpStatus.OK);
    }

    @GetMapping(value = "/get-comment/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(required = true) Integer commentId){
        return new ResponseEntity<Comment>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PostMapping(value = "/add-comment")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment){
        return new ResponseEntity<Comment>(commentService.addNewComment(comment), HttpStatus.OK);
    }
}

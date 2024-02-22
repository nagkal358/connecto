package com.trinet.connecto.controller;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "/comment")
public class CommentsController {
    @Autowired
    CommentService commentService;
    @GetMapping(value = "/get-comments/{threadId}")
    public ResponseEntity<List<Comment>> getAllCommentsForThread(@PathVariable Long threadId){
        return new ResponseEntity<>(commentService.getAllCommentsForThread(threadId), HttpStatus.OK);
    }

    @GetMapping(value = "/get-comment/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PostMapping(value = "/add-comment")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.addNewComment(comment), HttpStatus.OK);
    }
}

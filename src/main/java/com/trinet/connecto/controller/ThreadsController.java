package com.trinet.connecto.controller;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.ThreadData;
import com.trinet.connecto.service.ThreadService;
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
@RequestMapping(path = "${application.version}"+"${thread-api.path}")
public class ThreadsController {
    @Autowired
    ThreadService threadService;
    @GetMapping(value = "/get-threads/{pageNo}/{pageLimit}")
    public ResponseEntity<List<ThreadData>> getAllThreads(@PathVariable(required = false) Integer pageNo, @PathVariable(required = false) Integer pageLimit){
        return new ResponseEntity<>(threadService.getAllThreads(pageNo, pageLimit), HttpStatus.OK);
    }
    @GetMapping(value = "/get-categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(threadService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-thread/{threadId}")
    public ResponseEntity<ThreadData> getThread(@PathVariable(required = false) Long threadId){
        return new ResponseEntity<>(threadService.getThreadById(threadId), HttpStatus.OK);
    }
    @PostMapping(value = "/add-thread")
    public ResponseEntity<Thread> addNewThread(@RequestBody Thread thread){
        return new ResponseEntity<>(threadService.addNewThread(thread), HttpStatus.OK);
    }
}

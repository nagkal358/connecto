package com.trinet.connecto.controller;

import com.trinet.connecto.model.*;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.service.ThreadService;
import com.trinet.connecto.utils.RecordExists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "thread")
public class ThreadsController {

    @Autowired
    RecordExists recordExists;
    @Autowired
    ThreadService threadService;
    @GetMapping(value = "/get-open-thread-count")
    public ResponseEntity<Long> getOpenThreadsCount(){
        return new ResponseEntity<>(threadService.getOpenThreadCount(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-thread-counts-by-status")
    public ResponseEntity<List<StatusCounts>> getThreadsCountByStatus(){
        return new ResponseEntity<>(threadService.getThreadCountsByStatus(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-threads/{status}/{pageNo}/{pageLimit}")
    public ResponseEntity<List<ThreadData>> getAllThreads(@PathVariable(required = false) Integer status, @PathVariable(required = false) Integer pageNo, @PathVariable(required = false) Integer pageLimit){
        return new ResponseEntity<>(threadService.getAllThreads(status, pageNo, pageLimit), HttpStatus.OK);
    }
    @GetMapping(value = "/get-expired-threads")
    public ResponseEntity<List<ThreadData>> getExpiredThreads(){
        return new ResponseEntity<>(threadService.getExpiredThreads(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-thread-count-by-category")
    public ResponseEntity<List<CategoryCounts>> getCategorywiseCounts(){
        return new ResponseEntity<>(threadService.getCategoryWiseCounts(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-threads-for-user/{employeeId}/{status}/{pageNo}/{pageLimit}")
    public ResponseEntity<List<ThreadData>> getAllThreadsForEmployee(@PathVariable Integer employeeId,@PathVariable(required = false) Integer status, @PathVariable(required = false) Integer pageNo, @PathVariable(required = false) Integer pageLimit){
        return new ResponseEntity<>(threadService.getAllThreadsForEmployee(employeeId, status, pageNo, pageLimit), HttpStatus.OK);
    }
    @GetMapping(value = "/get-thread/{threadId}")
    public ResponseEntity<ThreadData> getThread(@PathVariable(required = false) Long threadId){
        return new ResponseEntity<>(threadService.getThreadById(threadId), HttpStatus.OK);
    }
    @PostMapping(value = "/add-thread")
    public ResponseEntity<Thread> addNewThread(@RequestBody Thread thread){
        return new ResponseEntity<>(threadService.addNewThread(thread), HttpStatus.OK);
    }
    @PostMapping(value = "/edit-thread/{threadId}")
    public ResponseEntity<Thread> editThread(@PathVariable Long threadId, @RequestBody Thread thread){
        thread.setId(threadId);
        return new ResponseEntity<>(threadService.editThread(thread), HttpStatus.OK);
    }
    @PostMapping(value = "/reject-thread/{threadId}")
    public ResponseEntity<Thread> editThread(@PathVariable Long threadId){
        return new ResponseEntity<>(threadService.editThreadStatus(threadId, 4), HttpStatus.OK);
    }
    @PostMapping(value = "/approve-thread/{threadId}")
    public ResponseEntity<Thread> approveThread(@PathVariable Long threadId){
        return new ResponseEntity<>(threadService.editThreadStatus(threadId, 2), HttpStatus.OK);
    }
    @GetMapping(value = "/get-categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(threadService.getAllCategories(), HttpStatus.OK);
    }
    @PostMapping(value = "/add-category")
    public ResponseEntity<Object> addNewCategory(@RequestBody Category category){
        Optional<Category> category1 = Optional.ofNullable(threadService.addNewCategory(category));
        return category1.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(recordExists.recordExists("Category"), HttpStatus.OK));
    }
    @PostMapping(value = "/vote-for-thread")
    public ResponseEntity<Vote> voteForThread(@RequestBody Vote vote){
        return new ResponseEntity<>(threadService.voteForThread(vote), HttpStatus.OK);
    }

    @GetMapping(value = "/get-votes-for-threads")
    public ResponseEntity<List<ThreadVotes>> getVotesForThreads(){
        return new ResponseEntity<>(threadService.getVotesForThreads(), HttpStatus.OK);
    }
}

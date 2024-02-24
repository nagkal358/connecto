package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.*;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.repository.CommentRepository;
import com.trinet.connecto.repository.SequenceRepository;
import com.trinet.connecto.repository.ThreadRepository;
import com.trinet.connecto.service.ThreadService;
import com.trinet.connecto.utils.RecordExists;
import com.trinet.connecto.utils.ThreadStatusMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
@Slf4j
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    SequenceRepository sequenceRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ThreadStatusMapper threadStatusMapper;
    public Long getOpenThreadCount(){
        return threadRepository.getOpenThreadsCount();
    }

    public List<StatusCounts> getThreadCountsByStatus(){
        List<StatusCounts> countsByStatus = threadRepository.getThreadsCountsByStatus();
        Map<Integer, String> threadStatus = threadStatusMapper.getMapping();
        countsByStatus.forEach(cs -> cs.setStatusText(threadStatus.get(cs.getStatus())));
        return countsByStatus;
    }

    @Override
    public List<ThreadData> getAllThreadsForEmployee(Integer employeeId, Integer status, Integer pageNo, Integer pageLimit) {
        List<ThreadData> threadsData = new ArrayList<>();
        List<Thread> threads = threadRepository.getAllThreadsForEmployee(employeeId, status, pageNo, pageLimit);
        threads.forEach((thread) -> {
            ThreadData threadData = modelMapper.map(thread, ThreadData.class);
            threadData.setComments(commentRepository.getAllCommentsForThread(thread.id));
            threadsData.add(threadData);
        });
        return threadsData;
    }

    public List<ThreadData> getAllThreads(Integer status, Integer pageNo, Integer pageLimit){
        List<ThreadData> threadsData = new ArrayList<>();
        List<Thread> threads = threadRepository.getAllThreads(status, pageNo, pageLimit);
        threads.forEach((thread) -> {
            ThreadData threadData = modelMapper.map(thread, ThreadData.class);
            threadData.setComments(commentRepository.getAllCommentsForThread(thread.id));
            threadsData.add(threadData);
        });
        return threadsData;
    }
    public List<Category> getAllCategories(){
        return threadRepository.getAllCategories();
    }

    @Override
    public List<CategoryCounts> getCategorywiseCounts() {
        //        Map<Integer, String> threadStatus = threadStatusMapper.getMapping();
//        countsByStatus.forEach(cs -> cs.setStatusText(threadStatus.get(cs.getStatus())));
        return threadRepository.getCategorywiseCounts();
    }

    public ThreadData getThreadById(Long threadId){
        Thread thread = threadRepository.getThreadById(threadId);
        ThreadData threadData = modelMapper.map(thread, ThreadData.class);
        threadData.setComments(commentRepository.getAllCommentsForThread(threadId));
        return threadData;
    }

    @Override
    public List<ThreadData> getExpiredThreads() {
        List<ThreadData> threadsData = new ArrayList<>();
        List<Thread> threads = threadRepository.getAllExpiredThreads();
        threads.forEach((thread) -> {
            ThreadData threadData = modelMapper.map(thread, ThreadData.class);
            threadData.setComments(commentRepository.getAllCommentsForThread(thread.id));
            threadsData.add(threadData);
        });
        return threadsData;
    }

    @SneakyThrows
    public Thread addNewThread(Thread thread){
        thread.setId(sequenceRepository.getNextSequenceId("thread"));
        return threadRepository.addNewThread(thread);
    }
    @SneakyThrows
    public Thread editThread(Thread thread){
        return threadRepository.editThread(thread);
    }

    @Override
    public Thread editThreadStatus(Long threadId, Integer status) {
        Optional<Thread> thread = Optional.ofNullable(threadRepository.getThreadById(threadId));
        Thread thread1 = null;
        if(thread.isPresent()){
            thread1 = thread.get();
            thread1.setStatus(status);
            thread1.setStatusChangedOn(Date.from(Instant.now()));
            if(status == 2){
                LocalDateTime next7thDay = LocalDateTime.now().plusDays(7);
                thread1.setExpiryOn(Date.from(next7thDay.toInstant(ZoneOffset.UTC)));
            }
            threadRepository.editThread(thread1);
        }
        return thread1;
    }

    @SneakyThrows
    @Override
    public Vote voteForThread(Vote vote) {
        vote.setId(sequenceRepository.getNextSequenceId("vote"));
        threadRepository.addVoteForThread(vote);
        threadRepository.increaseVoteCountsForThread(vote.getThreadId());
        return vote;
    }

    @SneakyThrows
    public Category addNewCategory(Category category){
        Optional<Category> category1 = Optional.ofNullable(threadRepository.getCategory(category.getCategory()));
        if(category1.isEmpty()){
            category.setId(sequenceRepository.getNextSequenceId("category"));
            return threadRepository.addNewCategory(category);
        } else{
            return null;
        }
    }
}

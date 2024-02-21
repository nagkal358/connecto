package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.ThreadData;
import com.trinet.connecto.repository.CommentRepository;
import com.trinet.connecto.repository.SequenceRepository;
import com.trinet.connecto.repository.ThreadRepository;
import com.trinet.connecto.service.ThreadService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    SequenceRepository sequenceRepository;
    @Autowired
    CommentRepository commentRepository;
    ModelMapper modelMapper;
    public List<ThreadData> getAllThreads(Integer pageNo, Integer pageLimit){
        List<ThreadData> threadsData = new ArrayList<ThreadData>();
        List<Thread> threads = threadRepository.getAllThreads(pageNo, pageLimit);
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

    public ThreadData getThreadById(Long threadId){
        Thread thread = threadRepository.getThreadById(threadId);
        ThreadData threadData = modelMapper.map(thread, ThreadData.class);
        threadData.setComments(commentRepository.getAllCommentsForThread(threadId));
        return threadData;
    }

    @SneakyThrows
    public Thread addNewThread(Thread thread){
        thread.setId(sequenceRepository.getNextSequenceId("thread"));
        return threadRepository.addNewThread(thread);
    }
}

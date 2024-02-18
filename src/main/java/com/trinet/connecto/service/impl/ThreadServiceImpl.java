package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.repository.ThreadRepository;
import com.trinet.connecto.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    ThreadRepository threadRepository;
    public List<Thread> getAllThreads(Integer pageNo, Integer pageLimit){
        return threadRepository.getAllThreads(pageNo, pageLimit);
    }
    public List<Category> getAllCategories(){
        return threadRepository.getAllCategories();
    }

    public Thread getThreadById(Integer threadId){
        return threadRepository.getThreadById(threadId);
    }

    public Thread addNewThread(Thread thread){
        return threadRepository.addNewThread(thread);
    }
}

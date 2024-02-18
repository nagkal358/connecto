package com.trinet.connecto.service;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;

import java.util.List;

public interface ThreadService {

    List<Thread> getAllThreads(Integer pageNo, Integer pageLimit);
    List<Category> getAllCategories();
    Thread getThreadById(Integer threadId);
    Thread addNewThread(Thread thread);
}

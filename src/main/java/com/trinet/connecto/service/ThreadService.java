package com.trinet.connecto.service;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.ThreadData;

import java.util.List;

public interface ThreadService {

    List<ThreadData> getAllThreads(Integer pageNo, Integer pageLimit);
    List<Category> getAllCategories();
    ThreadData getThreadById(Long threadId);
    Thread addNewThread(Thread thread);
}

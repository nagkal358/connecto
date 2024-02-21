package com.trinet.connecto.service;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.ThreadData;

import java.util.List;

public interface ThreadService {

    Long getOpenThreadCount();
    List<ThreadData> getAllThreads(Integer pageNo, Integer pageLimit);
    ThreadData getThreadById(Long threadId);
    Thread addNewThread(Thread thread);
    Thread editThread(Thread thread);
    Category addNewCategory(Category category);
    List<Category> getAllCategories();
}

package com.trinet.connecto.repository;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;

import java.util.List;

public interface ThreadRepository {

    List<Thread> getAllThreads(Integer pageNo, Integer pageLimit);
    List<Category> getAllCategories();

    Thread getThreadById(Long threadId);
    Long getOpenThreadsCount();
    Thread addNewThread(Thread thread);
    Thread editThread(Thread thread);
    Category addNewCategory(Category category);
}

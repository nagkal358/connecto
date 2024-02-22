package com.trinet.connecto.repository;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.StatusCounts;
import com.trinet.connecto.model.Thread;

import java.util.List;

public interface ThreadRepository {

    List<Thread> getAllThreads(Integer status, Integer pageNo, Integer pageLimit);
    List<Category> getAllCategories();

    Thread getThreadById(Long threadId);
    Long getOpenThreadsCount();
    List<StatusCounts> getThreadsCountsByStatus();
    Thread addNewThread(Thread thread);
    Thread editThread(Thread thread);
    Category addNewCategory(Category category);
}

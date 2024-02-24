package com.trinet.connecto.service;

import com.trinet.connecto.model.*;
import com.trinet.connecto.model.Thread;

import java.util.List;

public interface ThreadService {

    Long getOpenThreadCount();
    List<StatusCounts> getThreadCountsByStatus();
    List<ThreadData> getAllThreadsForEmployee(Integer employeeId,Integer status, Integer pageNo, Integer pageLimit);
    List<ThreadData> getAllThreads(Integer status, Integer pageNo, Integer pageLimit);
    ThreadData getThreadById(Long threadId);

    List<ThreadData> getExpiredThreads();

    Thread addNewThread(Thread thread);
    Thread editThread(Thread thread);
    Thread editThreadStatus(Long threadId, Integer status);
    Vote voteForThread(Vote vote);
    Category addNewCategory(Category category);
    List<Category> getAllCategories();

    List<CategoryCounts> getCategoryWiseCounts();

    List<ThreadVotes> getVotesForThreads();
}

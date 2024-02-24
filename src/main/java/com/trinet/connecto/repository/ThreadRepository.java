package com.trinet.connecto.repository;

import com.trinet.connecto.model.*;
import com.trinet.connecto.model.Thread;

import java.util.List;

public interface ThreadRepository {

    List<Thread> getAllThreadsForEmployee(Integer employeeId, Integer status, Integer pageNo, Integer pageLimit);
    List<Thread> getAllThreads(Integer status, Integer pageNo, Integer pageLimit);
    List<Thread> getAllExpiredThreads();
    List<Category> getAllCategories();
    List<Thread> getOpenThreads();

    Thread getThreadById(Long threadId);
    Long getOpenThreadsCount();
    List<StatusCounts> getThreadsCountsByStatus();
    Thread addNewThread(Thread thread);
    Thread editThread(Thread thread);
    Category addNewCategory(Category category);
    Category getCategory(String categoryName);

    void addVoteForThread(Vote vote);

    void increaseLikeCountsForThread(Integer threadId);

    void increaseVoteCountsForThread(Integer threadId);
    void increaseCommentCountsForThread(Integer threadId);

    List<CategoryCounts> getCategorywiseCounts();
}

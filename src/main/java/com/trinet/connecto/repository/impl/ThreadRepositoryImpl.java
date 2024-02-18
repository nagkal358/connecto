package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ThreadRepositoryImpl implements ThreadRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Thread> getAllThreads(Integer pageNo, Integer pageLimit) {
        return mongoTemplate.findAll(Thread.class);
    }
    @Override
    public List<Category> getAllCategories() {
        return mongoTemplate.findAll(Category.class);
    }

    @Override
    public Thread getThreadById(Integer threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(threadId));
        return mongoTemplate.findOne(query, Thread.class);
    }

    @Override
    public Thread addNewThread(Thread thread) {
        mongoTemplate.save(thread);
        return thread;
    }
}

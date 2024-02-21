package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.repository.ThreadRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
    public Thread getThreadById(Long threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(threadId));
        return mongoTemplate.findOne(query, Thread.class);
    }
    @Override
    public Long getOpenThreadsCount() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(1));
        return mongoTemplate.count(query, Thread.class);
    }

    @Override
    public Thread addNewThread(Thread thread) {
        return mongoTemplate.save(thread);
    }

    @Override
    public Thread editThread(Thread thread) {
        Query query = new Query(Criteria.where("id").is(thread.getId()));
        Document dbDoc = new Document();
        mongoTemplate.getConverter().write(thread, dbDoc);
        Update update = Update.fromDocument(dbDoc);
        mongoTemplate.updateFirst(query, update, Thread.class);
        return thread;
    }

    @Override
    public Category addNewCategory(Category category) {
        return mongoTemplate.save(category);
    }
}

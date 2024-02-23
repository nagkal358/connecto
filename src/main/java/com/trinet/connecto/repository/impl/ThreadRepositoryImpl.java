package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Category;
import com.trinet.connecto.model.StatusCounts;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.Vote;
import com.trinet.connecto.repository.ThreadRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
@Repository
public class ThreadRepositoryImpl implements ThreadRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Thread> getAllThreadsForEmployee(Integer employeeId, Integer status, Integer pageNo, Integer pageLimit) {
        Query query = new Query();
        Criteria criteria = Criteria.where("employeeId").is(employeeId);
        query.skip((long) pageNo * pageLimit);
        query.limit(pageLimit);
        if(status > 0)
            criteria.and("status").is(status);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Thread.class);
    }

    @Override
    public List<Thread> getAllThreads(Integer status, Integer pageNo, Integer pageLimit) {
        Query query = new Query();
        query.skip((long) pageNo * pageLimit);
        query.limit(pageLimit);
        if(status > 0)
            query.addCriteria(Criteria.where("status").is(status));
        return mongoTemplate.find(query, Thread.class);
    }

    @Override
    public List<Thread> getAllExpiredThreads() {
        Query query = new Query();
        query.addCriteria(Criteria.where("expiryOn").lt(Date.from(Instant.now())));
        return mongoTemplate.find(query, Thread.class);
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
    public List<StatusCounts> getThreadsCountsByStatus() {
        GroupOperation groupBy = group("status").count().as("count");
        ProjectionOperation project = project("count").and("status").previousOperation();
        Aggregation aggregation = newAggregation(groupBy, project);
        AggregationResults<StatusCounts> result = mongoTemplate.aggregate(aggregation, Thread.class, StatusCounts.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(1));
        return result.getMappedResults();
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

    @Override
    public void addVoteForThread(Vote vote) {
        mongoTemplate.save(vote);
    }

    @Override
    public void increaseLikeCountsForThread(Integer threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(threadId));
        Update update = new Update();
        update.inc("noOfLikes", 1);
        mongoTemplate.findAndModify(query, update, Thread.class);
    }
    @Override
    public void increaseVoteCountsForThread(Integer threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(threadId));
        Update update = new Update();
        update.inc("noOfVotes", 1);
        mongoTemplate.findAndModify(query, update, Thread.class);
    }
    @Override
    public void increaseCommentCountsForThread(Integer threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(threadId));
        Update update = new Update();
        update.inc("noOfComments", 1);
        mongoTemplate.findAndModify(query, update, Thread.class);
    }
}

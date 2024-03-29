package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.model.Like;
import com.trinet.connecto.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Comment> getAllCommentsForThread(Long threadId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("threadId").is(threadId));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(commentId));
        return mongoTemplate.findOne(query, Comment.class);
    }

    @Override
    public Comment addNewComment(Comment comment) {
        mongoTemplate.save(comment);
        return comment;
    }

    @Override
    public void likeOrDisLikeComment(Like like) {
        mongoTemplate.save(like);
    }

    @Override
    public void increaseLikeCountsForComment(Integer commentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(commentId));
        Update update = new Update();
        update.inc("noOfLikes", 1);
        mongoTemplate.findAndModify(query, update, Comment.class);
    }


}

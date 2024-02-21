package com.trinet.connecto.repository.impl;

import com.trinet.connecto.exception.SequenceException;
import com.trinet.connecto.model.SequenceId;
import com.trinet.connecto.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceRepositoryImpl implements SequenceRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public long getNextSequenceId(String collectionName) throws SequenceException {
        Query query = new Query(Criteria.where("_id").is(collectionName));
        Update update =new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        if(seqId == null){
            throw new SequenceException("MONGO_SEQUENCE_ERROR", "Unable to generate sequence for the collection::"+collectionName);
        }
        return seqId.getSeq();
    }

}

package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Thread {
    @Id
    public Long id;
    public String title;
    public String description;
    public Category category;
    public Date createdOn = Date.from(Instant.now());
    public Date statusChangedOn;
    public Date expiryOn;
    public Integer status = 1;
    public Integer employeeId;
    public Integer noOfComments = 0;
    public Integer noOfLikes = 0;
    public Integer noOfVotes = 0;
    public VoteCounts votes = new VoteCounts(0, 0);
}

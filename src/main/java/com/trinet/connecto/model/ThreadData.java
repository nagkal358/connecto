package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ThreadData {

    public Long id;
    public String title;
    public String description;
    public Category category;
    public Date createdOn;
    public Integer status;
    public Integer employeeId;
    public List<Comment> comments;
    public Integer noOfComments = 0;
    public Integer noOfLikes = 0;
    public Integer noOfVotes = 0;
}

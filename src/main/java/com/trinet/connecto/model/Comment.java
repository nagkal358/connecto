package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Comment {
    @Id
    public Long id;
    public String comment;
    public Date commentedDate = Date.from(Instant.now());
    public Integer threadId;
    public Integer employeeId;
    public Integer status = 1;
    public Integer noOfLikes = 0;
}

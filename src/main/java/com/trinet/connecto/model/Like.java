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
public class Like {
    @Id
    public Long id;
    public Integer commentId;
    public Integer threadId;
    public Integer employeeId;
    public Integer like;
    public Date date = Date.from(Instant.now());
}

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
public class Vote {
    @Id
    public Long id;
    public Integer threadId;
    public Integer employeeId;
    public Integer agree;
    public Date date = Date.from(Instant.now());
}

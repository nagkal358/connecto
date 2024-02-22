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
    public Integer status = 1;
    public Integer employeeId;
}

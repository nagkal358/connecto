package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Comment {
    @Id
    @Field("id")
    public Long id;
    public String comment;
    public Date commentedDate;
    public Integer threadId;
    public Integer employeeId;
    public Integer status;
}

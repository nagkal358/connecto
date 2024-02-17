package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment {
    public Integer id;
    public String comment;
    public Date commentedDate;
    public Integer threadId;
    public Integer employeeId;
    public Integer status;
}

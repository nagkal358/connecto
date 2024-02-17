package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Thread {
    @Id
    public String id;
    public String title;
    public String description;
    public Category category;
    public Date createdOn;
    public Integer status;
    public Integer employeeId;

}

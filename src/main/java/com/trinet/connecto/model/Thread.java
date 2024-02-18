package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Thread {
    @Id
    @Field("id")
    public Integer id;
    public String title;
    public String description;
    public Category category;
    public Date createdOn;
    public Integer status;
    public Integer employeeId;

}

package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Employee {
    @Id
    @Field("id")
    public Long id;
    public String name;
    public String email;
    public String password;
    public Integer role;
    public Date signupOn;
    public Date lastLogin;
}

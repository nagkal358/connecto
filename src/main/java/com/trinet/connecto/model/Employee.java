package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Employee {
    @Id
    public String id;
    public String name;
    public String email;
    public String password;
    public Integer role;
    public Date singupOn;
    public Date lastLogin;
}

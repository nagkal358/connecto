package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Employee {
    @Id
    public Long id;
    public String name;
    public String email;
    public String password;
    public Integer role = 1;
    public Date signupOn = Date.from(Instant.now());
    public Date lastLogin;
}


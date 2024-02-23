package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));
        return mongoTemplate.findOne(query, Employee.class);
    }
    @Override
    public Employee checkEmployee(Employee employee) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(employee.email));
        query.addCriteria(Criteria.where("password").is(employee.password));
        Optional<Employee> emp = Optional.ofNullable(mongoTemplate.findOne(query, Employee.class));
        return emp.map(this::updateEmployeeLastLogin).orElse(null);
    }

    private Employee updateEmployeeLastLogin(Employee employee){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employee.getId()));
        Update update = new Update();
        update.addToSet("lastLogin", Date.from(Instant.now()));
        return mongoTemplate.findAndModify(query, update, Employee.class);
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        mongoTemplate.save(employee);
        return employee;
    }


}

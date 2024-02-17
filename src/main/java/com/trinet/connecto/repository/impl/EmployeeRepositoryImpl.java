package com.trinet.connecto.repository.impl;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));
        return mongoTemplate.findOne(query, Employee.class);
    }
    @Override
    public Employee getEmployeeByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        mongoTemplate.save(employee);
        return employee;
    }

}

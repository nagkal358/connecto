package com.trinet.connecto.repository;

import com.trinet.connecto.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer employeeId);
    Employee getEmployeeByEmail(String email);

    Employee addNewEmployee(Employee employee);
}

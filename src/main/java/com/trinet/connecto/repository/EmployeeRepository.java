package com.trinet.connecto.repository;

import com.trinet.connecto.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee getEmployeeByEmail(String email);
    Employee getEmployeeById(Integer employeeId);
    Employee checkEmployee(Employee employee);

    Employee addNewEmployee(Employee employee);
}

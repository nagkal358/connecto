package com.trinet.connecto.service;

import com.trinet.connecto.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer employeeId);
    Employee checkEmployee(Employee employee);
    Employee addNewEmployee(Employee employee);
}

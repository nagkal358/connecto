package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.repository.EmployeeRepository;
import com.trinet.connecto.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Integer employeeId){
        return employeeRepository.getEmployeeById(employeeId);
    }
    public Employee checkEmployee(Employee employee){
        return employeeRepository.checkEmployee(employee);
    }

    public Employee addNewEmployee(Employee employee){
        return employeeRepository.addNewEmployee(employee);
    }
}

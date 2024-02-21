package com.trinet.connecto.service.impl;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.repository.EmployeeRepository;
import com.trinet.connecto.repository.SequenceRepository;
import com.trinet.connecto.service.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    SequenceRepository sequenceRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Integer employeeId){
        return employeeRepository.getEmployeeById(employeeId);
    }
    public Employee checkEmployee(Employee employee){
        return employeeRepository.checkEmployee(employee);
    }
    @SneakyThrows
    public Employee addNewEmployee(Employee employee){
        employee.setId(sequenceRepository.getNextSequenceId("employee"));
        return employeeRepository.addNewEmployee(employee);
    }
}

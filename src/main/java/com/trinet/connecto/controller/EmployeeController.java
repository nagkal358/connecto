package com.trinet.connecto.controller;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "${application-version}"+"${employee-api.path}")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/get-employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }
    @GetMapping(value = "/login")
    public ResponseEntity<Employee> checkEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.checkEmployee(employee), HttpStatus.OK);
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<Employee> addNewEmployee(Employee employee){
        return new ResponseEntity<Employee>(employeeService.addNewEmployee(employee), HttpStatus.OK);
    }
}

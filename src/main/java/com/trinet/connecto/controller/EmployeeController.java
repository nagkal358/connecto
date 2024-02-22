package com.trinet.connecto.controller;

import com.trinet.connecto.model.Employee;
import com.trinet.connecto.service.EmployeeService;
import com.trinet.connecto.utils.RecordExists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RecordExists recordExists;

    @GetMapping(value = "/get-employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping(value = "/get-employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId){
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<Employee> checkEmployee(@RequestBody Employee employee){
        Optional<Employee> empl = Optional.ofNullable(employeeService.checkEmployee(employee));
        return empl.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<Object> addNewEmployee(@RequestBody Employee employee){
        Optional<Employee> emp = Optional.ofNullable(employeeService.addNewEmployee(employee));
        return emp.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(recordExists.recordExists("Email"), HttpStatus.OK));
    }
}

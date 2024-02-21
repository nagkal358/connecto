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
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

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
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addNewEmployee(employee), HttpStatus.OK);
    }
}

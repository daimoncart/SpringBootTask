package test.boot.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.boot.spring.entity.Employee;
import test.boot.spring.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(path="/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }
}

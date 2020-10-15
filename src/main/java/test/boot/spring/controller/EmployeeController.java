package test.boot.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.boot.spring.entity.Employee;
import test.boot.spring.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(path="/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping(path="/employees/{id}")
    public Employee getEmployee(@PathVariable long id){
        Employee returnedEmployee = employeeService.findById(id);
        if (returnedEmployee==null) {
            throw new RuntimeException("User not found");
        }
        return returnedEmployee;
    }
}

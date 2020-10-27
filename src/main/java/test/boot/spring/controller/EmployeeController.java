package test.boot.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.boot.spring.exception.EmployeeNotFoundException;
import test.boot.spring.model.Employee;
import test.boot.spring.service.EmployeeService;

import java.util.List;

@Api(value = "EmployeeController")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "Get a list of employees in the system")
    @GetMapping(path="/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @ApiOperation(value = "Get a specific employee in the system")
    @GetMapping(path="/employees/{id}")
    public Employee getEmployee(@PathVariable long id){
        Employee returnedEmployee = employeeService.findById(id);
        if (returnedEmployee==null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return returnedEmployee;
    }

    @ApiOperation(value = "Create a new employee in the system")
    @PostMapping(path="/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        Employee returnEmployee = employeeService.save(employee);
        return employeeService.findById(returnEmployee.getId());
    }
}

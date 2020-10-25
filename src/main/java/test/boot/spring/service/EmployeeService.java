package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.boot.spring.exception.EmployeeAlreadyExistsException;
import test.boot.spring.exception.EmplyeeNotFoundException;
import test.boot.spring.model.Employee;
import test.boot.spring.repository.EmployeeRepository;
import test.boot.spring.utils.PrivateLogger;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TownService townService;

    @Autowired
    PrivateLogger privateLogger;

    public List<Employee> findAll() {
        privateLogger.log("List of all employees returned.");
        return employeeRepository.findAll();
    }

    public Employee findById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            privateLogger.log("Non-existent employee with id " + id + " requested.");
            throw new EmplyeeNotFoundException("Employee not found");
        }
        privateLogger.log("Single employee with id " + id + " returned.");
        return employeeOptional.get();
    }

    public void save(Employee employee){
        if (employeeRepository.findByEmail(employee.getEmail()) != null){
            privateLogger.log("A request to save a user with duplicate email address " +
                    employee.getEmail() + " has been made.");
            throw new EmployeeAlreadyExistsException("Employee with an email " + employee.getEmail() + " already exists.");
        }
        employeeRepository.save(employee);
        privateLogger.log("A new employee saved");
    }

}

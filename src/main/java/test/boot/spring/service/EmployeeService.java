package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.boot.spring.exception.EmployeeAlreadyExistsException;
import test.boot.spring.exception.EmployeeNotFoundException;
import test.boot.spring.exception.IncorrectEmployeeParameterException;
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
            throw new EmployeeNotFoundException("Employee not found");
        }
        privateLogger.log("Single employee with id " + id + " returned.");
        return employeeOptional.get();
    }

    public Employee save(Employee employee){
        if (employeeRepository.findByEmail(employee.getEmail()) != null){
            privateLogger.log("A request to save a user with duplicate email address " +
                    employee.getEmail() + " has been made.");
            throw new EmployeeAlreadyExistsException("Employee with an email " + employee.getEmail() + " already exists.");
        }
        if (employee.getEmail().length()<8 ||
            employee.getFirstName().length()<2 ||
            employee.getLastName().length()<2){
            throw new IncorrectEmployeeParameterException("Either name, last name or email is too short");
        }
        if (employee.getEmail().length()>100 ||
                employee.getFirstName().length()>50 ||
                employee.getLastName().length()>50){
            throw new IncorrectEmployeeParameterException("Either name, last name or email is too long");
        }
        employeeRepository.save(employee);
        privateLogger.log("A new employee saved");
        return employee;
    }

}

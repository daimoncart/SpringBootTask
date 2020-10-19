package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.boot.spring.controller.ForecastController;
import test.boot.spring.model.Employee;
import test.boot.spring.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ForecastController forecastController;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return employeeOptional.get();
    }

    public void save(Employee employee){
        if (employeeRepository.findByEmail(employee.getEmail()) != null){
            throw new RuntimeException("Employee with an email " + employee.getEmail() + " already exists.");
        }
        if (!forecastController.checkForTown(employee.getTown())){
            throw new RuntimeException("Sorry, there is no " + employee.getTown() + " town. Please check spelling.");
        }
        employeeRepository.save(employee);
    }

}

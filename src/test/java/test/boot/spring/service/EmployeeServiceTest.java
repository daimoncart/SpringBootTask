package test.boot.spring.service;



import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.boot.spring.exception.IncorrectEmployeeParameterException;
import test.boot.spring.exception.InvalidEmailException;
import test.boot.spring.model.Employee;
import test.boot.spring.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void findAll_test() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                new Employee(1, "TestName1", "TestLastName1", "test1@test.com", null),
                new Employee(2, "TestName2", "TestLastName2", "test2@test.com", null),
                new Employee(3, "TestName3", "TestLastName3", "test3@test.com", null)));
        List<Employee> list = employeeRepository.findAll();
        assertEquals(3, list.size());
    }

    @Test
    public void findById_test() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("TestName1")
                .lastName("TestLName1")
                .email("test1@test.com")
                .build();
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        Employee returnedEmployee = employeeRepository.findById(1l).get();
        assertEquals(1l, returnedEmployee.getId());
    }
    @Test
    public void findByEmail_test() {
        Employee employee = new Employee(1, "TestName5", "TestLastName5", "test5@test.com", null);
        when(employeeRepository.findByEmail("test5@test.com")).thenReturn(employee);
        Employee returnedEmployee = employeeRepository.findByEmail("test5@test.com");
        assertEquals(1, returnedEmployee.getId());
    }

    @Test
    public void save_withIncorrectEmail_test() {
        Employee employee = new Employee(6, "First6", "Second6", "email address", null);
        try {
            employeeService.save(employee);
        } catch (Exception e) {
            assertEquals(e.getClass(), InvalidEmailException.class);
        }

    }

    @Test
    public void save_withIncorrectFirstName_test() {
        Employee employee = new Employee(7, "a", "Second7", "email@email7", null);
        try {
            employeeService.save(employee);
        } catch (Exception e) {
            assertEquals(e.getClass(), IncorrectEmployeeParameterException.class);
        }
    }

}
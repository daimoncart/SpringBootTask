package test.boot.spring.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.boot.spring.model.Employee;
import test.boot.spring.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void getUserById() {
//        Employee testEmployee = new Employee();
//        testEmployee.setId(1L);
//        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(testEmployee));
//
//        Employee found = employeeRepository.getOne(1L);
//
//        assertThat(found.getId())
//                .isEqualTo(testEmployee.getId());
    }


    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }
}
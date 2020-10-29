package test.boot.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.boot.spring.exception.EmployeeAlreadyExistsException;
import test.boot.spring.exception.EmployeeNotFoundException;
import test.boot.spring.exception.IncorrectEmployeeParameterException;
import test.boot.spring.model.Employee;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createEmployeeTest() throws Exception {
        Employee testEmployee = Employee.builder()
            .email("test745@email.com")
            .firstName("JohnTest")
            .lastName("SmithTest")
            .build();
        String jsonRequest = mapper.writeValueAsString(testEmployee);
        MvcResult result = mockMvc.perform(
                post("/employees")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Employee returnedEmployee = mapper.readValue(resultContent, Employee.class);
        Assert.assertTrue(returnedEmployee.getId()>0);
        Assert.assertTrue(returnedEmployee.getEmail().equals("test745@email.com"));
    }

    @Test
    public void getListOfEmployeesTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<Employee> returnedEmployees = mapper.readValue(resultContent, List.class);
        Assert.assertTrue(returnedEmployees.size()>0);
    }

    @Test
    public void getSingleEmployeeTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Employee returnedEmployee = mapper.readValue(resultContent, Employee.class);
        Assert.assertTrue(returnedEmployee.getId() == 1);
    }


    @Test
    public void getSingleEmployee_NotFoundExceptionTest() throws Exception {

        mockMvc.perform(get("/employees/-1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmployeeNotFoundException));
    }

    @Test
    public void createEmployee_AlreadyExistsExceptionTest() throws Exception {

        Employee testEmployee = new Employee();
        testEmployee.setEmail("test12@email.com");
        testEmployee.setFirstName("JohnTest12");
        testEmployee.setLastName("SmithTest12");
        String jsonRequest = mapper.writeValueAsString(testEmployee);
        MvcResult result = mockMvc.perform(
                post("/employees")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(post("/employees/")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(testResult -> assertTrue(testResult.getResolvedException() instanceof EmployeeAlreadyExistsException));
    }

    @Test
    public void createEmployee_IncorrectParamsExceptionTest_emailTooShort() throws Exception {

        Employee testEmployee = new Employee();
        testEmployee.setEmail("t@e.cn");
        testEmployee.setFirstName("JohnTest2");
        testEmployee.setLastName("SmithTest2");
        String jsonRequest = mapper.writeValueAsString(testEmployee);
        mockMvc.perform(
                post("/employees")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IncorrectEmployeeParameterException));
    }

    @Test
    public void createEmployee_IncorrectParamsExceptionTest_nameTooLong() throws Exception {

        Employee testEmployee = new Employee();
        testEmployee.setEmail("test3@email.com");
        testEmployee.setFirstName("0123456789-123456789-123456789-123456789-123456789-too-much");
        testEmployee.setLastName("SmithTest3");
        String jsonRequest = mapper.writeValueAsString(testEmployee);
        mockMvc.perform(
                post("/employees")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IncorrectEmployeeParameterException));
    }
}
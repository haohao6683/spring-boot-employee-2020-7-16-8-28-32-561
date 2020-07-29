package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeRepository repository;
    private EmployeeServiceImpl employeeService;
    @BeforeEach
    void initData(){
        repository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(repository);
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        mockedEmployees.add(new Employee(6, 28, "male", "Draymond6", 20));
        when(repository.getAllData()).thenReturn(mockedEmployees);
    }
    @Test
    void should_return_employees_when_get(){
        //given

        //when
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(repository.getAllData().size(), employees.size());
    }

    @Test
    void should_return_employee_when_get_given_employeeId() {
        //given
        int id = 1;
        //when
        Employee employee = employeeService.getEmployeeById(id);
        //then
        Assertions.assertEquals(id,employee.getId());
    }

    @Test
    void should_return_employees_when_query_by_page_given_page_and_page_size() {
        //given
        int page = 1;
        int pageSize = 5;
        int firstEmployeeIdInPage1 = 1;
        //when
        List<Employee> employees = employeeService.getEmployeeByPage(page, pageSize);
        //then
        Assertions.assertEquals(pageSize,employees.size());
        Assertions.assertEquals(firstEmployeeIdInPage1, employees.get(0).getId());
    }

    @Test
    void should_return_employees_when_query_by_gender_given_gender() {
        //given
        String gender = "male";
        //when
        List<Employee> employees = employeeService.getEmployeeByGender(gender);
        //then
        for(Employee employee:employees){
            Assertions.assertEquals(gender,employee.getGender());
        }
    }
}

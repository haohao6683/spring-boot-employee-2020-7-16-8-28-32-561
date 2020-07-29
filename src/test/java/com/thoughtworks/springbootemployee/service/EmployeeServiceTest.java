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
}

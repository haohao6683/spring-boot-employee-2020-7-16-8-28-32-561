package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Test
    void should_return_employees_when_get(){
        //given
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(repository);
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        when(repository.getAllData()).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(repository.getAllData().size(), employees.size());
    }
}

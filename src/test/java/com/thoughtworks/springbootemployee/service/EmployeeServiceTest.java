package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Test
    void should_return_employees_when_get(){
        //given
        EmployeeRepository repository = mock(EmployeeRepository.class);
        when(repository.getAllData()).thenReturn(new Employee(1, 28, "male", "Draymond1", 1000),
                                                 new Employee(2, 28, "male", "Draymond2", 100),
                                                 new Employee(3, 28, "male", "Draymond3", 10));
        //when
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(repository.getAllData().size(), employees.size());
    }
}

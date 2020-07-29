package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeRepository repository = mock(EmployeeRepository.class);
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl(repository);

    @Test
    void should_return_employees_when_get(){
        //given
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        mockedEmployees.add(new Employee(6, 28, "male", "Draymond6", 20));
        when(repository.getAllData()).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(6, employees.size());
    }

    @Test
    void should_return_employee_when_get_given_employeeId() {
        //given
        int id = 1;
        when(repository.getEmployeeById(id)).thenReturn(new Employee(1, 28, "male", "Draymond1", 1000));
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
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        when(repository.getEmployeeByPage(page, pageSize)).thenReturn(mockedEmployees);
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
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        when(repository.getEmployeeByGender(gender)).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeByGender(gender);
        //then
        Assertions.assertEquals(4,employees.size());
    }

    @Test
    void should_return_employee_when_add_employees_given_employees() {
        //given
        Employee employee = new Employee(6, 28, "male", "Draymond6", 20);
        when(repository.addEmployee(employee)).thenReturn(employee);
        //when
        Employee returnValue = employeeService.addEmployee(employee);
        //then
        Assertions.assertEquals(employee.getId(), returnValue.getId());
    }
}

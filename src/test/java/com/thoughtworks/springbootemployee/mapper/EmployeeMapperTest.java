package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    void should_transform_to_employee_when_given_employeeRequest() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest(1, 22, "male", "haohao", 10);

        //when
        Employee employee = employeeMapper.toEmployee(employeeRequest);

        //then
        assertEquals(employeeRequest.getId(), employee.getId());
        assertEquals(employeeRequest.getName(), employee.getName());
        assertEquals(employeeRequest.getAge(), employee.getAge());
        assertEquals(employeeRequest.getGender(), employee.getGender());
        assertEquals(employeeRequest.getSalary(), employee.getSalary());
        assertEquals(employeeRequest.getId(), employee.getId());
    }

    @Test
    void should_transform_to_employeeResponse_when_given_employee() {
        //given
        Employee employee = new Employee(1, 22, "male", "haohao", 10);

        //when
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);

        //then
        assertEquals(employee.getId(), employeeResponse.getId());
        assertEquals(employee.getName(), employeeResponse.getName());
        assertEquals(employee.getAge(), employeeResponse.getAge());
        assertEquals(employee.getGender(), employeeResponse.getGender());
        assertEquals(employee.getSalary(), employeeResponse.getSalary());
        assertEquals(employee.getId(), employeeResponse.getId());
    }
}

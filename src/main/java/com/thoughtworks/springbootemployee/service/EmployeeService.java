package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();

    Employee getEmployeeById(Integer id) throws NoSuchDataException;

    Page<Employee> getEmployeeByPage(int page, int pageSize);

    List<Employee> getEmployeeByGender(String gender);

    Employee addEmployee(Employee employee) throws IllegalOperationException;

    Employee updateEmployeeById(Integer id, Employee newEmployee) throws Exception;

    void deleteEmployeeByID(Integer id) throws Exception;
}

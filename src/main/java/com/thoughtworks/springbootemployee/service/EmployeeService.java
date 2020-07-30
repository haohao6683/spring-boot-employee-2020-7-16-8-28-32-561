package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();

    Employee getEmployeeById(Integer id);

    Page<Employee> getEmployeeByPage(int page, int pageSize);

    List<Employee> getEmployeeByGender(String gender);

    Employee addEmployee(Employee employee);

    Employee updateEmployeeByID(Integer id, Employee newEmployee);

    Boolean deleteEmployeeByID(Integer id);
}

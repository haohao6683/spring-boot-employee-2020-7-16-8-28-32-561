package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();

    Employee getEmployeeById(int id);

    List<Employee> getEmployeeByPage(int page, int pageSize);

    List<Employee> getEmployeeByGender(String gender);

    Employee addEmployee(Employee employee);

    Employee updateEmployeeByID(int id, Employee newEmployee);

    Employee deleteEmployeeByID(int id);
}

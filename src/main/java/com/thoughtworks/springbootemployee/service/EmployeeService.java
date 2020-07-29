package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();
    Employee getEmployeeById(int id);
    List<Employee> getEmployeeByPage(int page, int pageSize);
    List<Employee> getEmployeeByGender(String gender);
    Employee addEmployee(Employee employee);
}

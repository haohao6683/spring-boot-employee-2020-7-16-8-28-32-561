package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    public List<Employee> getAllData() {
        return null;
    }

    public Employee getEmployeeById(int id) {
        return null;
    }

    public List<Employee> getEmployeeByPage(int page, int pageSize){
        return null;
    }

    public List<Employee> getEmployeeByGender(String gender){
        return null;
    }

    public Employee addEmployee(Employee employee) {
        return null;
    }

    public Employee updateEmployeeByID(int id, Employee newEmployee) {
        return null;
    }
}

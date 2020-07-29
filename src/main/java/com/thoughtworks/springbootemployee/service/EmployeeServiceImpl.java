package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployeeList() {
        return repository.getAllData();
    }

    public Employee getEmployeeById(int id) {
        return getEmployeeList().stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        return getEmployeeList().stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }
}

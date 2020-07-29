package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return repository.getAllData();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return repository.getEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        return repository.getEmployeeByPage(page,pageSize);
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return repository.getEmployeeByGender(gender);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repository.addEmployee(employee);
    }
}

package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Employee> getEmployeeByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page-1, pageSize));
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return repository.findByGender(gender);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployeeByID(Integer id, Employee newEmployee) {
        Employee employee = this.getEmployeeById(id);
        employee.setAge(newEmployee.getAge());
        employee.setGender(newEmployee.getGender());
        employee.setName(newEmployee.getName());
        employee.setSalary(newEmployee.getSalary());
        return repository.save(employee);
    }

    @Override
    public void deleteEmployeeByID(Integer id) {
        repository.deleteById(id);
    }
}

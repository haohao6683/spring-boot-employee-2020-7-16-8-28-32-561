package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XIEDR2
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) throws NoSuchDataException {
        Employee employee = repository.findById(id).orElse(null);
        if (employee == null) {
            throw new NoSuchDataException();
        }
        return employee;
    }

    @Override
    public Page<Employee> getEmployeeByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return repository.findByGender(gender);
    }

    @Override
    public Employee addEmployee(Employee employee) throws IllegalOperationException {
        if (employee == null) {
            throw new IllegalOperationException();
        }
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Integer id, Employee newEmployee) throws Exception {
        if (!id.equals(newEmployee.getId())) {
            throw new IllegalOperationException();
        }
        Employee employee = this.getEmployeeById(id);
        BeanUtils.copyProperties(newEmployee, employee);
        return repository.save(employee);
    }

    @Override
    public void deleteEmployeeByID(Integer id) throws IllegalOperationException {
        try {
            this.getEmployeeById(id);
        } catch (NoSuchDataException e) {
            throw new IllegalOperationException();
        }
        repository.deleteById(id);
    }
}

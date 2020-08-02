package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable int id) throws NoSuchDataException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployeeListByPage(int page, int pageSize) {
        return employeeService.getEmployeeByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeByGender(String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee newEmployee) throws IllegalOperationException {
        return employeeService.addEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeByID(@PathVariable int id, @RequestBody Employee newEmployee) throws IllegalOperationException {
        return employeeService.updateEmployeeById(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeByID(@PathVariable int id) throws IllegalOperationException {
        employeeService.deleteEmployeeByID(id);
    }
}

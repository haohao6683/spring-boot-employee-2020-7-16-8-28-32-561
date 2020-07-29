package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;


    private List<Employee> initEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 28, "male", "Draymond", 1000));
        employees.add(new Employee(2, 38, "female", "Shao", 1000));
        employees.add(new Employee(3, 38, "female", "Shao", 1000));
        employees.add(new Employee(4, 38, "female", "Shao", 1000));
        employees.add(new Employee(5, 38, "female", "Shao", 1000));
        employees.add(new Employee(6, 38, "female", "Shao", 1000));
        return employees;
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeListByPage(int page,int pageSize) {
        return employeeService.getEmployeeByPage(page,pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeByGender(String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        getEmployeeList().add(newEmployee);
        return getEmployeeList().stream().filter(employee -> employee.getId() == newEmployee.getId()).findFirst().orElse(new Employee());
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeByID(@PathVariable int id, @RequestBody Employee newEmployee) {

        List<Employee> employeeList = initEmployeeList();
        int index = 0;
        for(Employee employee : employeeList){
            if(employee.getId() == id){
                index = employeeList.indexOf(employee);
            }
        }
        employeeList.remove(index);
        employeeList.add(newEmployee);

        return employeeList.stream().filter(employee -> employee.getId() == id).findFirst().orElse(new Employee());
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployeeByID(@PathVariable int id) {

        List<Employee> employeeList = initEmployeeList();
        int index = 0;
        for(Employee employee : employeeList){
            if(employee.getId() == id){
                index = employeeList.indexOf(employee);
            }
        }
        Employee deletedEmployee = employeeList.get(index);
        employeeList.remove(index);

        return deletedEmployee;
    }
}

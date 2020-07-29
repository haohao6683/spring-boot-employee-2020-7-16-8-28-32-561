package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
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
        return initEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        //todo orelse
        return getEmployeeList().stream().filter(employee -> employee.getId() == id).findFirst().get();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeListByPage(int page,int pageSize) {
        int startIndex = (page - 1) * pageSize;
        return getEmployeeList().subList(startIndex, startIndex + pageSize);
    }

    @GetMapping(params = {"gender"})
    public Employee getEmployeeByGender(String gender) {
        //todo "male"  orelse
        //todo screen all male employees
        return getEmployeeList().stream().filter(employee -> employee.getGender().equals("male")).findFirst().get();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        getEmployeeList().add(newEmployee);
        return getEmployeeList().stream().filter(employee -> employee.getId() == newEmployee.getId()).findFirst().get();
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeByID(@PathVariable int id, @RequestBody Employee newEmployee) {

        List<Employee> employeeList = initEmployeeList();
        int index = 0;
        //todo iterator
        for(Employee employee : employeeList){
            if(employee.getId() == id){
                index = employeeList.indexOf(employee);
            }
        }
        //todo update each pro
        employeeList.remove(index);
        employeeList.add(newEmployee);

        return employeeList.stream().filter(employee -> employee.getId() == id).findFirst().get();
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

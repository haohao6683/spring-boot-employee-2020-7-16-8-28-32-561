package com.thoughtworks.springbootemployee.dto;

public class EmployeeRequest {
    private Integer id;
    private Integer age;
    private String gender;
    private String name;
    private Integer salary;

    public EmployeeRequest(Integer id, Integer age, String gender, String name, Integer salary) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}

package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int id;
    private int age;
    private String gender;
    private String name;
    private int salary;

    public Employee() {
    }

    public Employee(int id, int age, String gender, String name, int salary) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
}

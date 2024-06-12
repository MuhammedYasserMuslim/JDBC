package io.java.model;

import java.util.Date;

public class EmployeeBuilder {

    private Integer id;
    private String name;
    private boolean gender;
    private Date birthDate;
    private double salary;

    public EmployeeBuilder id(Integer id) {
        this.id = id;
        return this;
    }
    public EmployeeBuilder name(String name) {
        this.name = name;
        return this;
    }
    public EmployeeBuilder gender(boolean gender) {
        this.gender = gender;
        return this;
    }
    public EmployeeBuilder birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    public EmployeeBuilder salary(double salary) {
        this.salary = salary;
        return this;
    }
    public Employee build() {
        return new Employee(id, name, gender, birthDate, salary);
    }
}

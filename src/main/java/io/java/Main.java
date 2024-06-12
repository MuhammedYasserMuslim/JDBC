package io.java;


import io.java.dao.EmployeeDao;
import io.java.dao.EmployeeDaoImpl;
import io.java.model.Employee;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDaoImpl();
        Employee employee = Employee.builder()
                .id(0)
                .name("Salah")
                .birthDate(new Date())
                .salary(3000d)
                .build();
        dao.save(employee);
        dao.findAll().forEach(System.out::println);

    }
}

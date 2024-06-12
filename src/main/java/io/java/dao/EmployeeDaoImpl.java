package io.java.dao;

import io.java.model.Employee;
import io.java.model.EmployeeBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.java.utils.utils.getSqlDate;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        Connection connection = DbConnection.getConnection();
        if (connection == null) {
            return new ArrayList<>();
        }
        String query = "select * from employee";
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();
                employees.add(employee);
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Connection connection = DbConnection.getConnection();
        if (connection == null) {
            return null;
        }
        String query = "select * from employee where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        Connection connection = DbConnection.getConnection();
        if (connection != null) {
            if (employee.getId() > 0) { //update
                String query = "update employee set name = ? , gender = ? , birth_date = ? , salary = ? where id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, employee.getName());
                    preparedStatement.setBoolean(2, employee.isGender());
                    preparedStatement.setDate(3, getSqlDate(employee.getBirthDate()));
                    preparedStatement.setDouble(4, employee.getSalary());
                    preparedStatement.setInt(5, employee.getId());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else { // create
                String query = "insert into employee (name , gender , birth_date ,salary) values( ? , ? , ? , ? )";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, employee.getName());
                    preparedStatement.setBoolean(2, employee.isGender());
                    preparedStatement.setDate(3, getSqlDate(employee.getBirthDate()));
                    preparedStatement.setDouble(4, employee.getSalary());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection =  DbConnection.getConnection();
        String query = "delete from employee where id = ?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

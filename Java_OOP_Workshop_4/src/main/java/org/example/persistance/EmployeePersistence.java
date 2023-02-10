package org.example.persistance;

import org.example.data.Employee;

import java.util.List;

public interface EmployeePersistence {
    void addEmployee(Employee employee);

    Employee removeEmployeeBy(String employeeName);

    Employee getEmployeeBy(String employeeName);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);
}

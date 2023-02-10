package org.example.service;

import org.example.data.Employee;

public interface EmployeeService {
    void hire(Employee employee);

    Employee fire(String employeeName);

    double getNetSalary(String employeeName);

    void displayStatistics();

    Double promote(String employeeName, Double salaryIncreasePercentage);
}

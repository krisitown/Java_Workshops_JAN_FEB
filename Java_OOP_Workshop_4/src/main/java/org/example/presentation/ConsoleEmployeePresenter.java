package org.example.presentation;

import org.example.data.Employee;
import org.example.service.accounting.factory.SalaryCalculatorFactory;
import org.example.service.accounting.salary.NetSalaryCalculator;

public class ConsoleEmployeePresenter implements EmployeePresenter {
    private final SalaryCalculatorFactory salaryCalculatorFactory;

    public ConsoleEmployeePresenter(SalaryCalculatorFactory salaryCalculatorFactory) {
        this.salaryCalculatorFactory = salaryCalculatorFactory;
    }

    @Override
    public void display(Employee employee) {
        NetSalaryCalculator netSalaryCalculator = salaryCalculatorFactory.getCalculator(employee);
        String employeeString = String.format("Name: %s, Age: %d, GrossSalary: %.2f, NetSalary: %.2f %n",
            employee.getName(), employee.getAge(), employee.getSalary(),
                netSalaryCalculator.calculateNetSalary(employee.getSalary()));

        System.out.println(employeeString);
    }
}

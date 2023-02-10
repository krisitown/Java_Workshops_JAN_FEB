package org.example.service;

import org.example.data.Employee;
import org.example.persistance.EmployeePersistence;
import org.example.presentation.EmployeePresenter;
import org.example.service.accounting.factory.SalaryCalculatorFactory;
import org.example.service.accounting.salary.NetSalaryCalculator;
import org.example.service.accounting.salary.promotion.PromotionCalculator;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeePersistence employeePersistence;
    private final EmployeePresenter employeePresenter;
    private final PromotionCalculator promotionCalculator;
    private final SalaryCalculatorFactory salaryCalculatorFactory;

    public EmployeeServiceImpl(EmployeePersistence employeePersistence, EmployeePresenter employeePresenter,
                               PromotionCalculator promotionCalculator,
                               SalaryCalculatorFactory salaryCalculatorFactory) {
        this.employeePersistence = employeePersistence;
        this.employeePresenter = employeePresenter;
        this.promotionCalculator = promotionCalculator;
        this.salaryCalculatorFactory = salaryCalculatorFactory;
    }

    @Override
    public void hire(Employee employee) {
        employeePersistence.addEmployee(employee);
    }

    @Override
    public Employee fire(String employeeName) {
        return employeePersistence.removeEmployeeBy(employeeName);
    }

    @Override
    public double getNetSalary(String employeeName) {
        Employee e = employeePersistence.getEmployeeBy(employeeName);
        NetSalaryCalculator netSalaryCalculator = salaryCalculatorFactory.getCalculator(e);
        return netSalaryCalculator.calculateNetSalary(e.getSalary());
    }

    @Override
    public void displayStatistics() {
        employeePersistence.getAllEmployees().forEach(employeePresenter::display);
    }

    @Override
    public Double promote(String employeeName, Double salaryIncreasePercentage) {
        Employee employee = employeePersistence.getEmployeeBy(employeeName);
        double newSalary = promotionCalculator.increaseSalary(employee, salaryIncreasePercentage);

        employeePersistence.updateEmployee(employee);

        return newSalary;
    }
}

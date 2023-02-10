package org.example.service.accounting.salary.promotion;

import org.example.data.Employee;

public class FlatPercentagePromotionCalculator implements PromotionCalculator {

    @Override
    public double increaseSalary(Employee employee, Double percentage) {
        double newSalary = employee.getSalary() * (1 + 2 * percentage/100.0);
        employee.setSalary(newSalary);

        return newSalary;
    }
}

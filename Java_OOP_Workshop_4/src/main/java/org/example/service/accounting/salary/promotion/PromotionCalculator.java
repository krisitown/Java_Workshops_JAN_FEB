package org.example.service.accounting.salary.promotion;

import org.example.data.Employee;

public interface PromotionCalculator {
    double increaseSalary(Employee employee, Double percentage);
}

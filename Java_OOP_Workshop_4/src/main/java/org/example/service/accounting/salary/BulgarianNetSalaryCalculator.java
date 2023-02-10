package org.example.service.accounting.salary;

import org.example.service.accounting.tax.BulgarianTaxCalculator;
import org.example.service.accounting.tax.TaxCalculator;

public class BulgarianNetSalaryCalculator implements NetSalaryCalculator {
    private final TaxCalculator taxCalculator;

    public BulgarianNetSalaryCalculator(BulgarianTaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public double calculateNetSalary(double grossSalary) {
        return grossSalary - taxCalculator.calculate(grossSalary);
    }
}

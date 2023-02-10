package org.example.service.accounting.salary;

import org.example.service.accounting.tax.TaxCalculator;

public class UkNetSalaryCalculator implements NetSalaryCalculator {
    private final TaxCalculator domesticTaxCalculator;
    private final TaxCalculator foreignTaxCalculator;

    public UkNetSalaryCalculator(TaxCalculator domesticTaxCalculator, TaxCalculator foreignTaxCalculator) {
        this.domesticTaxCalculator = domesticTaxCalculator;
        this.foreignTaxCalculator = foreignTaxCalculator;
    }

    @Override
    public double calculateNetSalary(double grossSalary) {
        double domesticTax = domesticTaxCalculator.calculate(grossSalary);
        return foreignTaxCalculator.calculate(grossSalary - domesticTax);
    }
}

package org.example.service.accounting.salary;

import org.example.service.accounting.tax.TaxCalculator;

public class GermanyNetSalaryCalculator implements NetSalaryCalculator {
    private final TaxCalculator domesticTaxCalculator;
    private final TaxCalculator foreignTaxCalculator;

    public GermanyNetSalaryCalculator(TaxCalculator domesticTaxCalculator, TaxCalculator foreignTaxCalculator) {
        this.domesticTaxCalculator = domesticTaxCalculator;
        this.foreignTaxCalculator = foreignTaxCalculator;
    }

    @Override
    public double calculateNetSalary(double grossSalary) {
        double domesticTax = domesticTaxCalculator.calculate(grossSalary);
        return foreignTaxCalculator.calculate(grossSalary - domesticTax);
    }
}

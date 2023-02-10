package org.example.service.accounting.tax;

public class GermanyTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount * 0.4;
    }
}

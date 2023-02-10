package org.example.service.accounting.tax;

public class UkTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount * 0.33;
    }
}

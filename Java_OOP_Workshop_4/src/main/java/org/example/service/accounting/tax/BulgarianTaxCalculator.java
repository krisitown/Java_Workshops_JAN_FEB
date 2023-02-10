package org.example.service.accounting.tax;

public class BulgarianTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount - ((amount * 0.9) - Math.min(amount * 0.1, 500));
    }
}

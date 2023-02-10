package org.example.service.accounting.factory;

import org.example.data.Employee;
import org.example.service.accounting.salary.BulgarianNetSalaryCalculator;
import org.example.service.accounting.salary.GermanyNetSalaryCalculator;
import org.example.service.accounting.salary.NetSalaryCalculator;
import org.example.service.accounting.salary.UkNetSalaryCalculator;
import org.example.service.accounting.tax.BulgarianTaxCalculator;
import org.example.service.accounting.tax.GermanyTaxCalculator;
import org.example.service.accounting.tax.UkTaxCalculator;

public class SalaryCalculatorFactoryImpl implements SalaryCalculatorFactory {

    @Override
    public NetSalaryCalculator getCalculator(Employee e) {
        if(e.getCountry().equals("Bulgaria")) {
            return new BulgarianNetSalaryCalculator(new BulgarianTaxCalculator());
        }

        if(e.getCountry().equals("Germany")) {
            return new GermanyNetSalaryCalculator(new GermanyTaxCalculator(), new BulgarianTaxCalculator());
        }

        if(e.getCountry().equals("UK")) {
            return new UkNetSalaryCalculator(new UkTaxCalculator(), new BulgarianTaxCalculator());
        }

        throw new IllegalArgumentException("Employee country is not supported!");
    }
}

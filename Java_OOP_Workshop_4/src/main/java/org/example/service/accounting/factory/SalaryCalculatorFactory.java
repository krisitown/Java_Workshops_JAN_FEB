package org.example.service.accounting.factory;

import org.example.data.Employee;
import org.example.service.accounting.salary.NetSalaryCalculator;

public interface SalaryCalculatorFactory {
    NetSalaryCalculator getCalculator(Employee e);
}

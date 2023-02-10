package org.example;

import org.example.data.Employee;
import org.example.persistance.InMemoryEmployeePersistence;
import org.example.presentation.ConsoleEmployeePresenter;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;
import org.example.service.accounting.factory.SalaryCalculatorFactory;
import org.example.service.accounting.factory.SalaryCalculatorFactoryImpl;
import org.example.service.accounting.salary.BulgarianNetSalaryCalculator;
import org.example.service.accounting.salary.promotion.FlatPercentagePromotionCalculator;
import org.example.service.accounting.tax.BulgarianTaxCalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SalaryCalculatorFactory factory = new SalaryCalculatorFactoryImpl();

        EmployeeService employeeService = new EmployeeServiceImpl(new InMemoryEmployeePersistence(),
                new ConsoleEmployeePresenter(factory),
                new FlatPercentagePromotionCalculator(),
                factory);
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        while(!line.equals("End")) {
            String[] tokens = line.split(";");

            if(tokens[0].equals("Hire")) {
                Employee employee = new Employee(tokens[1], Integer.parseInt(tokens[2]),
                        Double.parseDouble(tokens[3]), tokens[4], tokens[5]);
                employeeService.hire(employee);
            } else if(tokens[0].equals("Fire")) {
                Employee fired = employeeService.fire(tokens[1]);
                System.out.println("Fired employee " + fired.getName());
            } else if(tokens[0].equals("GetNetSalary")) {
                employeeService.getNetSalary(tokens[1]);
            } else if(tokens[0].equals("GetStatistics")) {
                employeeService.displayStatistics();
            } else if(tokens[0].equals("Promote")) {
                double newSalary = employeeService.promote(tokens[1], Double.parseDouble(tokens[2]));
                System.out.println("Employee " + tokens[1] + "'s new salary is: " + newSalary);
            }

            line = scanner.nextLine();
        }
    }
}
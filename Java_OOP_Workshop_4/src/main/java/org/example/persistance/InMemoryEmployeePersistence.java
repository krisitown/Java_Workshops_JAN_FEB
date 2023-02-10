package org.example.persistance;

import org.example.data.Employee;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmployeePersistence implements EmployeePersistence {
    private final List<Employee> employees;

    public InMemoryEmployeePersistence() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee removeEmployeeBy(String employeeName) {
        Employee found = getEmployeeBy(employeeName);

        employees.remove(found);

        return found;
    }

    @Override
    public Employee getEmployeeBy(String employeeName) {
        return employees.stream()
                .filter(em -> em.getName().equals(employeeName))
                .findFirst()
                .get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        removeEmployeeBy(employee.getName());
        addEmployee(employee);
    }
}

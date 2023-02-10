package org.example.data;

public class Employee {
    private String name;
    private int age;
    private double salary;
    private String position;

    private String country;

    public Employee(String name, int age, double salary, String position, String country) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

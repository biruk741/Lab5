package com.company.classes;

public class Pair {
    Company company;
    Employee employee;
    int happiness;
    int position;

    public Pair(Company company, Employee employee) {
        this.company = company;
        this.employee = employee;
    }

    public Pair(Company company, Employee employee, int position) {
        this.company = company;
        this.employee = employee;
        this.position = position;
    }

    public Pair(Company company, Employee employee, int happiness, int position) {
        this.company = company;
        this.employee = employee;
        this.happiness = happiness;
        this.position = position;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "company=" + company.getName() +
                ", employee=" + employee.getName() +
                '}';
    }
}

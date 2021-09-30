package com.company.classes;

public class Pair {
    Company company;
    Employee employee;
    int happiness;

    public Pair(Company company, Employee employee) {
        this.company = company;
        this.employee = employee;
    }

    public Pair(Company company, Employee employee, int happiness) {
        this.company = company;
        this.employee = employee;
        this.happiness = happiness;
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

    @Override
    public String toString() {
        return "Pair{" +
                "company=" + company +
                ", employee=" + employee +
                ", happiness=" + happiness +
                '}';
    }
}

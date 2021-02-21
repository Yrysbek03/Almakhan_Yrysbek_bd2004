package kz.aitu.oop.practice.assignment4.controllers;

import kz.aitu.oop.practice.assignment4.entities.Employee;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IEmployeeRepository;

import java.util.ArrayList;

public class EmployeeController {
    private final IEmployeeRepository repo;

    public EmployeeController(IEmployeeRepository repository) {
        this.repo = repository;
    }
    public String createEmployee(String name, String surname, Integer salary) {
        Employee employee = new Employee(name, surname, salary);

        boolean created = repo.createEmployee(employee);


        return (!created ? "User was created!" : "User creation was failed!");
    }

    public String getEmployee(int id) {
        Employee employee = repo.getEmployee(id);

        return (employee == null ? "User was not found!" : employee.toString());
    }

    public String getAllEmployees() {
        ArrayList<Employee> employees = repo.getAllEmployees();

        return employees.toString();
    }
}

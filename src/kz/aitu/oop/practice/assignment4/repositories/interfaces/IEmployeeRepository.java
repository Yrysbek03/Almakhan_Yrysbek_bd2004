package kz.aitu.oop.practice.assignment4.repositories.interfaces;

import kz.aitu.oop.practice.assignment4.entities.Employee;

import java.util.ArrayList;

public interface IEmployeeRepository {
    boolean createEmployee(Employee employee);
    Employee getEmployee(Integer id);
    ArrayList<Employee> getAllEmployees();
}

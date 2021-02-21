package kz.aitu.oop.practice.assignment4.repositories.interfaces;

import kz.aitu.oop.practice.assignment4.entities.Employee;
import kz.aitu.oop.practice.assignment4.entities.Project;

import java.util.ArrayList;

public interface IProjectRepository {
    boolean createProject(Project project);
    boolean addEmployees(Project project, ArrayList<Integer> employees_id);
    int defineCost(Project project);
    Project getProject(Integer id);
    ArrayList<Project> getAllProjects();
    ArrayList<Employee> getEmployeesOfProject(Integer project_id);
}

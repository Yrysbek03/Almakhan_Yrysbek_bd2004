package kz.aitu.oop.practice.assignment4.controllers;

import kz.aitu.oop.practice.assignment4.entities.Project;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IProjectRepository;

import java.util.ArrayList;

public class ProjectController {
    private final IProjectRepository repo;

    public ProjectController(IProjectRepository repository) {
        this.repo = repository;
    }

    public String createProject(String title, ArrayList<Integer> employees_id) {
        Project project = new Project(title);

        boolean created = repo.createProject(project);

        if (created) {
            return  "Project creation was failed!";
        } else {
            boolean added = repo.addEmployees(project, employees_id);
            if (added) {
                return "ERROR! Not added!";
            } else {
                int i = repo.defineCost(project);
                return "Project was created! Employees added successfully!";
            }

        }
    }

    public String getProject(Integer id) {
        Project project = repo.getProject(id);

        return (project == null ? "Project was not found!" : project.toString());
    }

    public String getAllProjects() {
        ArrayList<Project> projects = repo.getAllProjects();

        return projects.toString();
    }
}

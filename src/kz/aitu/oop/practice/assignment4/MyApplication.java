package kz.aitu.oop.practice.assignment4;


import kz.aitu.oop.practice.assignment4.controllers.EmployeeController;
import kz.aitu.oop.practice.assignment4.controllers.ProjectController;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IEmployeeRepository;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IProjectRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class MyApplication {
    private final EmployeeController employeeController;
    private final ProjectController projectController;
    private final Scanner scanner;

    public MyApplication(IEmployeeRepository employeeRepository, IProjectRepository projectRepository) {
        employeeController = new EmployeeController(employeeRepository);
        projectController = new ProjectController(projectRepository);
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("*************************");
            System.out.println("Welcome to My Application");
            System.out.println("Select option:");
            System.out.println("1. Get all employees");
            System.out.println("2. Get employee by id");
            System.out.println("3. Create employee");
            System.out.println("4. Get all projects");
            System.out.println("5. Get project by id");
            System.out.println("6. Create project");
            System.out.println("0. Exit");
            System.out.println("*************************");
            try {
                System.out.print("Enter option (1-3): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllEmployeesMenu();
                } else if (option == 2) {
                    getEmployeeByIdMenu();
                } else if (option == 3) {
                    createEmployeeMenu();
                } else if (option == 4) {
                    getAllProjectsMenu();
                } else if (option == 5) {
                    getProjectByIdMenu();
                } else if (option == 6) {
                    createProjectMenu();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next(); // to ignore incorrect input
            }

            System.out.println("*************************");

        }
    }

    public void getAllEmployeesMenu() {
        String response = employeeController.getAllEmployees();
        System.out.println(response);
    }

    public void getEmployeeByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = employeeController.getEmployee(id);
        System.out.println(response);
    }

    public void createEmployeeMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter salary of the employee");
        Integer salary = scanner.nextInt();

        String response = employeeController.createEmployee(name, surname, salary);
        System.out.println(response);
    }

    public void getAllProjectsMenu() {
        String response = projectController.getAllProjects();
        System.out.println(response);
    }

    public void getProjectByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = projectController.getProject(id);
        System.out.println(response);
    }

    public void createProjectMenu() {
        System.out.println("Please enter title");
        String title = scanner.next();
        System.out.println("How many members will participate in this project?");
        int num = scanner.nextInt();
        this.getAllEmployeesMenu();
        System.out.println("Please choose employees by id");
        ArrayList<Integer> employees_id = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer id = scanner.nextInt();
            employees_id.add(id);
        }

        String response = projectController.createProject(title, employees_id);

        System.out.println(response);
    }
}
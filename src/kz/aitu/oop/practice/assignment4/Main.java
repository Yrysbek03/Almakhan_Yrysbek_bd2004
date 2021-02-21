package kz.aitu.oop.practice.assignment4;


import kz.aitu.oop.practice.assignment4.data.PostgresDB;
import kz.aitu.oop.practice.assignment4.data.interfaces.IDB;
import kz.aitu.oop.practice.assignment4.repositories.EmployeeRepository;
import kz.aitu.oop.practice.assignment4.repositories.ProjectRepository;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IEmployeeRepository;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IProjectRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IEmployeeRepository employeeRepository = new EmployeeRepository(db);
        IProjectRepository projectRepository = new ProjectRepository(db);
        MyApplication app = new MyApplication(employeeRepository, projectRepository);
        app.start();
    }
}
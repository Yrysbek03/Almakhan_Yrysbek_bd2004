package kz.aitu.oop.practice.assignment4.repositories;

import kz.aitu.oop.practice.assignment4.data.interfaces.IDB;
import kz.aitu.oop.practice.assignment4.entities.Employee;
import kz.aitu.oop.practice.assignment4.entities.Project;
import kz.aitu.oop.practice.assignment4.repositories.interfaces.IProjectRepository;

import java.sql.*;
import java.util.ArrayList;

public class ProjectRepository implements IProjectRepository {
    private final IDB db;

    public ProjectRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createProject(Project project) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO projects(title) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, project.getTitle());

            boolean executed = st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean addEmployees(Project project, ArrayList<Integer> employees_id) {
        Connection con = null;
        try {
            Integer project_id = this.getProjectId(project);
            if (project_id == null) {
                return false;
            }
            String sql = "INSERT INTO project_employee(project_id,employee_id) VALUES (?,?)";
            ArrayList<Boolean> booleans = new ArrayList<>();
            boolean executed;

            for (Integer employee_id: employees_id) {
                con = null;
                con = db.getConnection();
                PreparedStatement st = con.prepareStatement(sql);

                st.setInt(1, project_id);
                st.setInt(2, employee_id);


                executed = st.execute();
                booleans.add(executed);
            }

            for (Boolean bool: booleans) {
                if (!bool) {
                    return false;
                }
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private Integer getProjectId(Project project) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id FROM projects WHERE title=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, project.getTitle());

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                Integer id = rs.getInt("id");
                return id;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Project getProject(Integer id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,title,cost FROM projects WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Project project = new Project(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("cost"),
                        this.getEmployeesOfProject(rs.getInt("id")));

                return project;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Project> getAllProjects() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,title,cost FROM projects";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            ArrayList<Project> projects = new ArrayList<>();
            while (rs.next()) {
                Project project = new Project(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("cost"),
                        this.getEmployeesOfProject(rs.getInt("id")));

                projects.add(project);
            }

            return projects;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getEmployeesOfProject(Integer project_id) {
        Connection con = null;
        try {
            ArrayList<Integer> employees_id = this.getEmployeesIdOfProject(project_id);
            ArrayList<Employee> employees = new ArrayList<>();

            for (Integer id: employees_id) {
                con = null;
                con = db.getConnection();
                String sql = ("SELECT * FROM employees where id = " + id);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Employee employee = new Employee(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("salary"));

                    employees.add(employee);
                }
            }

            return employees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    private ArrayList<Integer> getEmployeesIdOfProject(Integer project_id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = ("SELECT employee_id FROM project_employee WHERE project_id = " + project_id );
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            ArrayList<Integer> employees_id = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("employee_id");

                employees_id.add(id);
            }

            return employees_id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int defineCost(Project project) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE projects set cost = (?) where id= (?)";
            PreparedStatement st = con.prepareStatement(sql);

            Integer project_id = this.getProjectId(project);
            Integer totalCost = this.sumCosts(project_id);

            st.setInt(1, totalCost);
            st.setInt(2, project_id);

            int executed = st.executeUpdate();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    private Integer sumCosts(Integer project_id) {
        ArrayList<Employee> employees = this.getEmployeesOfProject(project_id);
        Integer total = 0;

        for (Employee employee: employees) {
            total += employee.getSalary();
        }

        return total;
    }
}


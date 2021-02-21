package kz.aitu.oop.practice.assignment4.entities;

import java.util.ArrayList;

public class Project {
    private Integer id;
    private String title;
    private Integer cost;
    private ArrayList<Employee> members;

    /**
     * constructor without parameters
     */
    public Project() {
    }

    /**
     * constructor with title parameter
     * @param title
     */
    public Project(String title) {
        this.title = title;
    }

    /**
     * second constructor
     * @param title
     * @param cost
     */
    public Project(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    /**
     * third constructor
     * @param id
     * @param title
     * @param cost
     */
    public Project(Integer id, String title, Integer cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    /**
     * fourth constructor
     * @param id
     * @param title
     * @param cost
     * @param members
     */
    public Project(Integer id, String title, Integer cost, ArrayList<Employee> members) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.members = members;
    }

    /**
     * getter id
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter cost
     * @return
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * setter cost
     * @param cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * getter members
     * @return
     */
    public ArrayList<Employee> getMembers() {
        return members;
    }

    /**
     * setter members
     * @param members
     */
    public void setMembers(ArrayList<Employee> members) {
        this.members = members;
    }

    /**
     * method for get information about project
     * @return
     */
    @Override
    public String toString() {
        String text = ("Project {\nid = " + getId() +
                " title = " + getTitle() +
                " cost = " + getCost() +
                " members: "
        );

        for (Employee employee: getMembers()) {
            text = text + "\n" + employee.toString();
        }

        return text + "\n}";
    }
}

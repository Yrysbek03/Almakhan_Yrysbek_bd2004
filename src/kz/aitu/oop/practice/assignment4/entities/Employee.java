package kz.aitu.oop.practice.assignment4.entities;

public class Employee {
    private Integer id;
    private String name;
    private String surname;
    private Integer salary;

    /**
     * constructor without parameters
     */
    public Employee(){}

    /**
     * second constructor
     * @param name
     * @param surname
     * @param salary
     */
    public Employee(String name, String surname, Integer salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    /**
     * third constructor
     * @param id
     * @param name
     * @param surname
     * @param salary
     */
    public Employee(Integer id, String name, String surname, Integer salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    /**
     * getter of id
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
     * getter name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter surname
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * setter surname
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * getter salary
     * @return
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * setter salary
     * @param salary
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * method for get information about employee
     * @return
     */
    @Override
    public String toString(){
        return ("Employee {id = " + getId() +
                " name = " + getName() +
                " surname = " + getSurname() +
                " salary = " + getSalary() + "}");
    }
}

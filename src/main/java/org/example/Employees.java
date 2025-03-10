package org.example;

public class Employees {
    private int id;
    private String name;
    private String Gender;
    private String Dept;

    public Employees(int id, String name, String gender, String dept) {
        this.id = id;
        this.name = name;
        Gender = gender;
        Dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Dept='" + Dept + '\'' +
                '}';
    }
}

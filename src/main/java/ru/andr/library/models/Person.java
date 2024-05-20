package ru.andr.library.models;

public class Person {
    private int id;
    private String fullName;
    private int birthYear;

    public Person() {}

    public Person(String fullName, int birthOfYear) {
        this.fullName = fullName;
        this.birthYear = birthOfYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

}

package com.netcracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*[a-z.]")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]*[a-z.]")
    private String middleName;

    @Pattern(regexp = "[A-Z][a-z]*[a-z.]")
    @NotNull
    private String lastName;

    @NotNull
    private int age;

    private double salary;


    private String job;

    @Pattern(regexp = "[A-Za-z0-9][_A-Za-z0-9-.]{0,20}[A-Za-z0-9]@[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9](\\.[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9])*(\\.(ru|com|org|net))")
    @NotNull
    private String email;

    public User() {
    }

    public User(@NotNull @Pattern(regexp = "[A-Z][a-z]*[a-z.]") String firstName, @Pattern(regexp = "[A-Z][a-z]*[a-z.]") String middleName, @Pattern(regexp = "[A-Z][a-z]*[a-z.]") @NotNull String lastName, @NotNull int age, double salary, String job, @Pattern(regexp = "[A-Za-z0-9][_A-Za-z0-9-.]{0,20}[A-Za-z0-9]@[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9](\\.[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9])*(\\.(ru|com|org|net))") @NotNull String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.job = job;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "firstName:"+firstName+"; middleName:"+middleName+"; lastName:"+lastName+"; age:"+age+"; salary:"+salary+"; job:"+job+"; email:"+email;
    }
}

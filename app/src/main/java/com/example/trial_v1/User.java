package com.example.trial_v1;

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

public class User {

    private String name, email, profession, phone;
    private int age;

    public User() {
    }

    public User(String name, String email, String profession, String phone, int age) {
        this.name = name;
        this.email = email;
        this.profession = profession;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

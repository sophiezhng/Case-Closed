package com.example.caseclosedfunctional;

public class User {
    public String fullName;
    public String email;
    public int age = 18;
    public  boolean nursingHome = false;

    public User(String fullName, String email){
        this.email = email;
        this.fullName = fullName;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setNH(boolean nursingHome){
        this.nursingHome = nursingHome;
    }

}

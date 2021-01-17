package com.example.caseclosedfunctional;

import android.os.Bundle;
import java.util.*;
public class User {
    public String fullName;
    public String email;
    public int age = 18;
    public boolean nursingHome = false;
    public boolean plan = false;
    public int condition = 0;
    public String Uid = "";

    public User(String fullName, String email){
        this.email = email;
        this.fullName = fullName;
    }
    public User(String fullName, String email, int age, boolean nursingHome, boolean plan, int condition, String Uid){
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.nursingHome = nursingHome;
        this.plan = plan;
        this.condition = condition;
        this.Uid = Uid;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setNH(boolean nursingHome){
        this.nursingHome = nursingHome;
    }
    public void setCondition(int condition){
        this.condition = condition;
    }
    public void setPlan(boolean plan){
         this.plan = plan;
    }

    public Bundle getUserBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("email", email);
        bundle.putInt("age", age);
        bundle.putBoolean("nursingHome", nursingHome);
        bundle.putBoolean("plan", plan);
        bundle.putInt("condition", condition);
        bundle.putString("Uid", Uid);
        return bundle;
    }

    public static User userFromBundle (Bundle bundle){
        return new User(bundle.getString("fullName"), bundle.getString("email"), bundle.getInt("age"), bundle.getBoolean("nursingHome"), bundle.getBoolean("plan"), bundle.getInt("condition"), bundle.getString("Uid"));
    }
}

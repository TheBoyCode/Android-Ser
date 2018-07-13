package com.example.a1.androidser;

import java.util.ArrayList;

public class User {
    public String Email;
    public String Password;
    public PointCode point;
    public String id;
    public ArrayList<PointCode> points;
    private User(){}
    private static User Intence;
    public static User getIntence() {
        if(Intence==null) {
            Intence = new User();
        }
        return Intence;
    }
}

package com.example.dimichspb.cypruscarrentals;

import java.util.ArrayList;

public class User {
    public String name;
    public String city;

    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("Harry", "San Diego"));
        users.add(new User("Maria", "San Francisco"));
        users.add(new User("Sarah", "San Marino"));
        return users;
    }
}

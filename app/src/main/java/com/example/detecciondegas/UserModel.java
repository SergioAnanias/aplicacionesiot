package com.example.detecciondegas;

import java.io.Serializable;

public class UserModel implements Serializable {
    private int id;
    private String Username;
    private String Restaurant;

    public int getId(){
        return id;
    }
    public String getUsername(){
        return Username;
    }
    public String getRestaurant(){
        return Restaurant;
    }
    public UserModel(Integer id, String username, String restaurant){
        this.id=id;
        this.Username=username;
        this.Restaurant = restaurant;

    }


}

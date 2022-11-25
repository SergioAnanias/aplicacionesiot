package com.example.detecciondegas;


import java.io.Serializable;

public class localModel implements Serializable {
    private String rut;
    private String direccion;
    private String nombre;
    private int userID;
    public String getRut(){
        return rut;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getNombre(){
        return nombre;
    }
    public localModel(String rut, String direccion, String nombre,int userID){
        this.rut=rut;
        this.direccion=direccion;
        this.nombre= nombre;
        this.userID=userID;
    }


}

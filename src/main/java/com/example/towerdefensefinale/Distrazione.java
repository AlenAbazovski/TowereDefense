package com.example.towerdefensefinale;
import  javafx.scene.paint.Color;

public class Distrazione {
    private double x, y;
    private String tipo;
    private Color colore;

    public Distrazione(double x , double y, String tipo){
        this.x = x;
        this. y = y;
        this.tipo = tipo;


        if(tipo.equals(("Ciotola"))){
            this.colore = Color.ORANGE;
        }else{
            this.colore = Color.PINK;
        }
    }
}

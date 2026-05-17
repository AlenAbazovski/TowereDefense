package com.example.towerdefensefinale;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import  javafx.scene.paint.Color;

public class Distrazione {
    private double x, y;
    private Image sprite;


    public Distrazione(double x, double y) {
        this.x = x;
        this.y = y;
        try {
            this.sprite = new Image(getClass().getResourceAsStream("/img/topo.png"));
        }catch (Exception e){
            System.out.println("Errore");
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void disegna(GraphicsContext gx){
        if(sprite != null){
            gx.drawImage(sprite, x, y, 48, 48);
        }
    }
}

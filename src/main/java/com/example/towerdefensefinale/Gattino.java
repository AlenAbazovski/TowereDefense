package com.example.towerdefensefinale;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class Gattino {
    private double x, y;
    private double velocità;
    private boolean fermo;
    private Image sprite;
    private Color colore;

    public Gattino(double velocità, String nomefile) {
        this.x = -60; // Parte da sinistra
        this.y = 150 + (Math.random() * 300); // altezza casuale
        this.velocità = velocità;
        this.fermo = false;

        try {
            this.sprite = new Image(getClass().getResourceAsStream("/ ciccione.png"));
        } catch (Exception e) {
            ;
            System.out.println("Errore nel caricamento di: ciccione.png");
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocità() {
        return velocità;
    }

    public void setVelocità(double velocità) {
        this.velocità = velocità;
    }

    public boolean isFermo() {
        return fermo;
    }

    public void setFermo(boolean fermo) {
        this.fermo = fermo;
    }



    public void muovi(){
        if(!fermo){
            this.x += this.velocità;
        }
    }

    public void disegna(GraphicsContext gx){
        if (sprite != null) {
            gx.drawImage(sprite, x, y, 64, 64);
        }
    }
}


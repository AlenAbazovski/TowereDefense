package com.example.towerdefensefinale;

import javafx.scene.paint.Color;

public class Gattino {
    private double x, y;
    private double velocità;
    private boolean fermo;
    private Color colore;

    public Gattino(double velocità, Color colore) {
        this.x = -30; // Parte da sinistra
        this.y = 150 + (Math.random() * 300); // altezza casuale
        this.velocità = velocità;
        this.fermo = false;
        this.colore = colore;
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

    public Color getColore() {
        return colore;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }

    public void muovi(){
        if(!fermo){
            this.x += this.velocità;
        }
    }
}


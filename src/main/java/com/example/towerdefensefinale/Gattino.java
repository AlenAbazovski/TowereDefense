package com.example.towerdefensefinale;

public class Gattino {
    private double x, y;
    private double velocità;
    private boolean distratto;
    private int tipo; // 1:Pigro, 2:Scheggia, 3: Ciccione
    private int resistenza;

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

    public boolean isDistratto() {
        return distratto;
    }

    public void setDistratto(boolean distratto) {
        this.distratto = distratto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getResistenza() {
        return resistenza;
    }

    public void setResistenza(int resistenza) {
        this.resistenza = resistenza;
    }

    public Gattino(int tipo) {
        this.tipo = tipo;
        this.x = -30; // Parte da sinistra fuori schermo
        this.y = 150 + (Math.random() * 300); // Posizione verticale casuale
        this.distratto = false;

        if (tipo == 1) { // pigro
            this.velocità = 0.6;
            this.resistenza = 1;
        } else if (tipo == 2) {//scheggia
            this.velocità = 3.5;
            this.resistenza = 2;
        } else { // cicione
            this.velocità = 0.4;
            this.resistenza = 4; // serve piu cibo per fermarlo
        }
    }

    public void muovi(){
        if(!distratto){
            this.x += this.velocità;
        }
    }
}


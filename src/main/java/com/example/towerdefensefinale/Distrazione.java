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
            // Tentativo 1: Cerca partendo dalla radice assoluta delle risorse
            java.io.InputStream stream = getClass().getResourceAsStream("/img/topo.png");

            // Tentativo 2: Se il primo fallisce, prova il percorso relativo
            if (stream == null) {
                stream = getClass().getResourceAsStream("img/topo.png");
            }

            if (stream != null) {
                this.sprite = new Image(stream);
            } else {
                System.out.println("Attenzione: non trovo 'topo.png' dentro src/main/resources/img/");
            }
        } catch (Exception e) {
            System.out.println("Errore nel caricamento del file del topo");
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
            gx.drawImage(sprite, x - 24, y -24, 48, 48);
        }else{
            gx.setFill(Color.BROWN);
            gx.fillOval(x - 10, y - 5, 20, 10);
        }
    }
}

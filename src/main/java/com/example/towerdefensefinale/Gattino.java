package com.example.towerdefensefinale;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Gattino {
    private double x, y;
    private double velocità;
    private boolean fermo;
    private Image sprite;
    private Color colore;
    private boolean vicinoAllaMeta = false;

    public Gattino(double velocità, String nomefile, Color colore) {
        this.x = -30; // Parte da sinistra
        double altezza = javafx.stage.Screen.getPrimary().getBounds().getHeight();
        this.y = altezza * 0.62 + (Math.random() * altezza * 0.26);
        this.velocità = velocità;
        this.fermo = false;
        this.colore = colore;

        try {
            // Cerchiamo l'immagine partendo dalla cartella 'img' posizionata nella radice delle risorse
            java.io.InputStream stream = getClass().getResourceAsStream("/img/" + nomefile);

            if (stream == null) {
                // Alternativa di ripiego se la struttura dei moduli richiede un percorso relativo
                stream = getClass().getResourceAsStream("img/" + nomefile);
            }

            if (stream != null) {
                this.sprite = new Image(stream);
            } else {
                System.out.println("Impossibile trovare il file: /img/" + nomefile);
            }
        } catch (Exception e) {
            System.out.println("Errore generico nel caricamento di: " + nomefile);
        }
    }


    protected Image caricaImmagine(String nome) {
        try {
            java.io.InputStream s = getClass().getResourceAsStream("/img/" + nome);
            if (s == null) s = getClass().getResourceAsStream("img/" + nome);
            if (s != null) return new Image(s);
        } catch (Exception e) {
            System.out.println("Errore caricamento: " + nome);
        }
        return null;
    }

    public void disegna(GraphicsContext gx) {
        // Comportamento originale con sprite singolo
        if (sprite != null) {
            gx.drawImage(sprite, x, y, 160, 160);
        } else {
            gx.setFill(colore);
            gx.fillOval(x, y, 30, 30);
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


    public boolean isFermo() {
        return fermo;
    }

    public void setFermo(boolean fermo) {
        this.fermo = fermo;
    }


    public void muovi() {
        if (!fermo) {
            this.x += this.velocità;

        }
    }

    public boolean isVicinoAllaMeta() { return vicinoAllaMeta; }
    public void setVicinoAllaMeta(boolean v) { this.vicinoAllaMeta = v; }







    public Color getColore() {
        return colore;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }

}





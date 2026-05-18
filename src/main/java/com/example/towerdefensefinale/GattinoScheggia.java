package com.example.towerdefensefinale;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GattinoScheggia extends Gattino{

    private Image frame1, frame2, frame3;
    private int frameCorrente = 0;
    private int frameCounter = 0;
    private final int FRAME_VELOCITA = 6; // cambia frame ogni 6 tick

    public GattinoScheggia() {
        //chiama il costruttore della madre con velocità 5.00 e colore bianco
        super(6.5, "scheggia.png", Color.WHITE);

        frame1 = caricaImmagine("scheggia1.png");
        frame2 = caricaImmagine("scheggia2.png");
        frame3 = caricaImmagine("scheggia3.png");
    }

    private Image caricaImmagine(String nome) {
        try {
            java.io.InputStream s = getClass().getResourceAsStream("/img/" + nome);
            if (s == null) s = getClass().getResourceAsStream("img/" + nome);
            if (s != null) return new Image(s);
        } catch (Exception e) {
            System.out.println("Errore caricamento: " + nome);
        }
        return null;
    }

    @Override
    public void disegna(GraphicsContext gx) {
        Image daMostrare;

        if (isVicinoAllaMeta()) {
            // Vicino alle polpette: usa solo scheggia3 (animazione attacco)
            daMostrare = frame3;
        } else {
            // Corsa normale: alterna scheggia1 e scheggia2
            frameCounter++;
            if (frameCounter >= FRAME_VELOCITA) {
                frameCounter = 0;
                frameCorrente = (frameCorrente + 1) % 2; // cicla solo tra 0 e 1
            }
            daMostrare = (frameCorrente == 0) ? frame1 : frame2;
        }

        if (daMostrare != null) {
            gx.drawImage(daMostrare, getX(), getY(), 160, 160);
        } else {
            gx.setFill(getColore());
            gx.fillOval(getX(), getY(), 30, 30);
        }
    }
}


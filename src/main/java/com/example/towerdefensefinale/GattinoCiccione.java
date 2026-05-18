package com.example.towerdefensefinale;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GattinoCiccione extends Gattino{
    private Image frame1, frame2, frame3, frame4;
    private int frameCorrente = 0;
    private int frameCounter = 0;
    // Il ciccione è lento quindi cambia frame più lentamente dello scheggia
    private final int FRAME_VELOCITA_CAMMINATA = 12;
    private final int FRAME_VELOCITA_ATTACCO = 15;
    private boolean animazioneAttaccoCompletata = false;

    public GattinoCiccione() {
        //chiama il costruttore della madre con velocita 0.6 e colore DARKSLATEGREY
        super(2.8, "ciccione.png", Color.DARKSLATEGREY);

        frame1 = caricaImmagine("ciccione1.png");
        frame2 = caricaImmagine("ciccione2.png");
        frame3 = caricaImmagine("ciccione3.png");
        frame4 = caricaImmagine("ciccione4.png");
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
            if (animazioneAttaccoCompletata) {
                // --- AGGIUNTO: animazione completata, rimane bloccato su ciccione4 ---
                daMostrare = frame4;
            } else {
                // --- AGGIUNTO: mostra ciccione3, poi ciccione4, poi blocca ---
                frameCounter++;
                if (frameCounter >= FRAME_VELOCITA_ATTACCO) {
                    frameCounter = 0;
                    frameCorrente++; // avanza di uno, non in loop

                    if (frameCorrente >= 2) {
                        // Ha mostrato frame3 (indice 0) e frame4 (indice 1): animazione finita
                        frameCorrente = 1;
                        animazioneAttaccoCompletata = true;
                    }
                }
                daMostrare = (frameCorrente == 0) ? frame3 : frame4;
            }
        } else {
            // Camminata normale: alterna ciccione1 e ciccione2 in loop
            frameCounter++;
            if (frameCounter >= FRAME_VELOCITA_CAMMINATA) {
                frameCounter = 0;
                frameCorrente = (frameCorrente + 1) % 2;
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


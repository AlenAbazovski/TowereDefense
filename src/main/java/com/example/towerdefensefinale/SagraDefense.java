package com.example.towerdefensefinale;

import com.example.towerdefensefinale.Distrazione;
import com.example.towerdefensefinale.Gattino;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class SagraDefense extends Application {

    // Liste per gestire gatti e ciotole
    private ArrayList<Gattino> listaGattini = new ArrayList<>();
    private ArrayList<Distrazione> listaCiotole = new ArrayList<>();

    private Image immagineSfondo;

    // Variabili del gioco
    private int puntiCoccole = 100;
    private int polpettePerse = 0;
    private boolean giocoFinito = false;

    @Override
    public void start(Stage stage) {
        immagineSfondo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/SfondoCucina.png")));
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);
        GraphicsContext gx = canvas.getGraphicsContext2D();

        // Gestione del click per piazzare le ciotole
        canvas.setOnMouseClicked(e -> {
            if (!giocoFinito && puntiCoccole >= 20) {
                listaCiotole.add(new Distrazione(e.getX(), e.getY()));
                puntiCoccole -= 20;
            } else if (puntiCoccole < 20) {
                System.out.println("non hai abbastanza punti cccole! Ne hai solo " +  puntiCoccole);

            }
        });

        // Loop di gioco infinito
        new AnimationTimer() {
            int counter = 0;

            @Override
            public void handle(long l) {
                if (!giocoFinito) {
                    counter++;

                    // 1. Cancella e ridisegna lo sfondo della cucina
                    gx.drawImage(immagineSfondo, 0, 0, 800, 600);

                    // 2. Fai nascere un gattino nuovo ogni 2 secondi (120 frame)
                    if (counter >= 120) {
                        creaGattoCasuale();
                        counter = 0;
                    }

                    // 3. UNICO CICLO PER I GATTINI (Muove, controlla collisioni e disegna)
                    for (int i = 0; i < listaGattini.size(); i++) {
                        Gattino g = listaGattini.get(i);
                        g.muovi();

                        // Controlliamo se questo gattino 'g' tocca una delle ciotole
                        for (int j = 0; j < listaCiotole.size(); j++) {
                            Distrazione d = listaCiotole.get(j);

                            double distanzaX = Math.abs(g.getX() - d.getX());
                            double PlainY = Math.abs(g.getY() - d.getY());

                            // Se il gattino è vicino alla ciotola
                            if (distanzaX < 30 && PlainY < 30) {
                                g.setFermo(true);       // Il gatto si ferma a mangiare!
                                listaCiotole.remove(j); // La ciotola sparisce perché è stata mangiata
                                j--;                    // Sistema l'indice delle ciotole
                            }
                        }

                        // Se il gattino arriva alla fine dello schermo a destra (ruba il cibo della nonna)
                        if (g.getX() > 720 && !g.isFermo()) {
                            polpettePerse++;
                            listaGattini.remove(i);
                            i--; // Sistema l'indice dei gattini
                            continue; // Salta al prossimo gatto senza disegnarlo
                        }

                        // Disegna il gattino nella sua posizione attuale
                        g.disegna(gx);
                    }

                    for (int k = 0; k < listaCiotole.size(); k++) {
                        Distrazione d = listaCiotole.get(k);
                        d.disegna(gx); // <--- Chiama il metodo disegna() che carica topo.png!
                    }

                    // 4. UNICO CICLO PER DISEGNARE LE CIOTOLE RIMASTE A TERRA
                    gx.setFill(Color.BROWN);
                    for (int i = 0; i < listaCiotole.size(); i++) {
                        Distrazione d = listaCiotole.get(i);
                        // Disegna la ciotola come un ovale schiacciato sul pavimento
                        gx.fillOval(d.getX() - 10, d.getY() - 5, 20, 10);
                    }

                    // 5. Controllo fine partita
                    if (polpettePerse >= 5) {
                        giocoFinito = true;
                        gx.setFill(Color.RED);
                        gx.fillText("GAME OVER: Troppi gatti golosi!", 300, 300);
                    }
                }
            } // Chiude il metodo handle
        }.start(); // <--- ORA CHIUDE E FA PARTIRE L'ANIMATION TIMER CORRETTAMENTE!

        stage.setScene(new Scene(root));
        stage.setTitle("Sagra defense -Aiuta la nonna!");
        stage.show();
    }

    // Il metodo helper deve stare qui, staccato da tutto, dentro la classe principale
    private void creaGattoCasuale() {
        double r = Math.random();
        if (r < 0.33) {
            listaGattini.add(new GattinoScheggia());
        } else if (r < 0.66) {
            listaGattini.add(new GattinoCiccione());
        } else {
            listaGattini.add(new GattinoPigro());
        }
    }
}
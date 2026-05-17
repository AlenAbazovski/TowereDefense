package com.example.towerdefensefinale;

import com.example.towerdefensefinale.Distrazione;
import com.example.towerdefensefinale.Gattino;
import javafx.animation.Animation;
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

    //liste per gestire gatti e ciotole
    private ArrayList<Gattino> listaGattini = new ArrayList<>();
    private ArrayList<Distrazione> listaCiotole = new ArrayList<>();

    private Image immagineSfondo;

    //variabili del gioco
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

        //gestione del click per piazzare le ciotole
        canvas.setOnMouseClicked(e -> {
            if (!giocoFinito && puntiCoccole >= 20) {
                listaCiotole.add(new Distrazione(e.getX(), e.getY()));
                puntiCoccole -= 20;
            }
        });

        //loop di gioco infinito
        new AnimationTimer() {
            int counter = 0;

            @Override
            public void handle(long l) {
                if (!giocoFinito) {
                    counter++;
                    gx.drawImage(immagineSfondo, 0, 0, 800, 600);

                    for (Gattino g : listaGattini){
                        g.muovi();
                        g.disegna(gx);
                    }

                    for (int i = 0; i < listaGattini.size();i++ ){
                        Gattino g = listaGattini.get(i);//prendo il gattino alla posizione i
                        g.muovi();
                        g.disegna(gx);
                    }

                    //disegna le ciotole (distrazioni) piazzate a terra
                    gx.setFill(Color.BROWN);
                    for(int i = 0; i < listaCiotole.size(); i++){
                        Distrazione d = listaCiotole.get(i); // prende le ciotole alla poszione i
                        gx.fillOval(d.getX(), d.getY(), 20, 30);
                    }

                }
            }
        }.start();
        stage.setScene(new Scene(root));
        stage.setTitle("Sagra defense -Aiuta la nonna!");
        stage.show();
    }



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



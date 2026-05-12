package com.example.towerdefensefinale;

import com.example.towerdefensefinale.Distrazione;
import com.example.towerdefensefinale.Gattino;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.ArrayList;

public class SagraDefense extends Application{

    //liste per gestire gatti e ciotole
    private ArrayList<Gattino> listaGattini = new ArrayList<>();
    private ArrayList<Distrazione> listaCiotole = new ArrayList<>();

    //variabili del gioco
    private int puntiCoccole = 100;
    private int polpettePerse = 0;
    private boolean giocoFinito = false;


    @Override
    public void start(Stage stage ){
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
        new AnimationTimer(){
            int counter = 0;

            @Override
            public void handle(long l) {

            }
        }
    }
}



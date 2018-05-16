/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.GameController;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.players.PlayerCollection;
import hive.model.players.TeamColor;
import hive.model.players.decisions.HumanDecision;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {
//

    BorderPane pane;
    GameController controller;
    public InterfacePlateauMain mainGauche;
    public InterfacePlateauMain mainDroite;
    public InterfaceRuche ruche;

    public InterfacePlateau(PlayerCollection col, CacheImage c, Stage stage, String joueur1, String joueur2, DoubleProperty prop) {
        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        controller = new GameController(DefaultGame.get(new HumanDecision(), new HumanDecision()));

        InterfacePlateauMain mainGauche = new InterfacePlateauMain(col, joueur1, Color.AZURE, c, pane.heightProperty(), controller,this, TeamColor.WHITE);
        InterfacePlateauMain mainDroite = new InterfacePlateauMain(col, joueur2, Color.BURLYWOOD, c, pane.heightProperty(), controller, this,TeamColor.BLACK);

        StackPane centerPane = new StackPane();
        ScrollPane p = new ScrollPane();
        p.setHvalue(0.5);
        p.setVvalue(0.5);
        ruche  = new InterfaceRuche(c, (int) stage.getWidth(), (int) stage.getHeight(), controller);
        ruche.setHandler(this);


//        p.setOnDragDetected((event) -> {
//            double x= -event.getX();
//            System.out.println(x);
//            p.setHvalue(x/20);
//
//        });

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);

        ruche.isResizable();
        centerPane.getChildren().add(ruche);
        p.setContent(centerPane);

        pane.setCenter(p);
        pane.setLeft(mainGauche);
        pane.setRight(mainDroite);
        this.getChildren().add(pane);
    }

}

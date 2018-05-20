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

    public InterfacePlateau(PlayerCollection colJ1, PlayerCollection colJ2, CacheImage c, Stage stage, String joueur1, String joueur2) {
        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        pane.prefHeightProperty().bind(stage.heightProperty());
        controller = new GameController(DefaultGame.get(new HumanDecision(), new HumanDecision()));

        InterfacePlateauMain mainGauche = new InterfacePlateauMain(colJ1,stage, joueur1, c, controller, this, TeamColor.WHITE);
        InterfacePlateauMain mainDroite = new InterfacePlateauMain(colJ2,stage, joueur2, c, controller, this, TeamColor.BLACK);

        StackPane centerPane = new StackPane();
        ScrollPane p = new ScrollPane();

        ruche = new InterfaceRuche(c, controller);
        ruche.setHandler(this);
//        p.setOnDragDetected((event) -> {
//            double x= -event.getX();
//            System.out.println(x);
//            p.setHvalue(x/20);
//
//        });
        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        p.setContent(centerPane);
        
        p.setHvalue(0.5);
        p.setVvalue(0.5);
        pane.setCenter(p);
        pane.setLeft(mainGauche);
        pane.setRight(mainDroite);
        this.getChildren().add(pane);
    }
    
    public InterfacePlateauMain getInterfacePlateauMain(TeamColor color)
    {
        return null;
    }

}

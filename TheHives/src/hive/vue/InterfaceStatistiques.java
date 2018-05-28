/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceStatistiques extends Interface {

    private final Label stat;

    public InterfaceStatistiques(Scene scene, Stage primaryStage, Controller controller, CacheImage c) {
        super(scene, primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

        stat = new Label();

        setTextWithCurrentLanguage();

        BorderPane bp = new BorderPane();
        bp.prefHeightProperty().bind(pane.heightProperty());
        bp.prefWidthProperty().bind(pane.widthProperty());

        AnchorPane top = new AnchorPane();
        top.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        top.prefWidthProperty().bind(bp.widthProperty());

        top.getChildren().add(droite);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        top.getChildren().add(boutonRetourMenu);

        StackPane spS = new StackPane();
        spS.prefWidthProperty().bind(bp.widthProperty().multiply(0.3));
        spS.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(pancarte, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundFond);
        //stat.setFont(new Font(police, width / 35));
        spS.setBackground(background);
        stat.prefHeightProperty().bind(spS.heightProperty().multiply(0.8));
        stat.prefWidthProperty().bind(spS.widthProperty().multiply(0.9));
        stat.setAlignment(Pos.CENTER);
        stat.setTextFill(Color.web("#fbe5b5"));

        spS.getChildren().add(stat);

         AnchorPane.setLeftAnchor(spS, (double) tailleDeCase + 15);
        AnchorPane.setRightAnchor(spS, (double) tailleDeCase + 15);
        AnchorPane.setTopAnchor(spS, (double) 5);
        AnchorPane.setBottomAnchor(spS, (double) 5);
        top.getChildren().add(spS);
        bp.setTop(top);

        StackPane liste_sp = new StackPane();
        liste_sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.8));
        liste_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.77));
        ListView<Label> liste = new ListView<>();
        liste.prefHeightProperty().bind(liste_sp.heightProperty().multiply(0.8));
        liste.prefWidthProperty().bind(liste_sp.widthProperty().multiply(0.9));

        for (Map.Entry<String, Integer> entry : controller.scoresGesture.getScorePerPlayer().entrySet()) {
            String playerName = entry.getKey();
            Integer playerScore = entry.getValue();

            Label playerNameScore = new Label(playerName + "   =   " + String.valueOf(playerScore));
            playerNameScore.setAlignment(Pos.CENTER);
            liste.getItems().add(playerNameScore);
        }
        liste_sp.getChildren().add(liste);
        bp.setCenter(liste_sp);

        StackPane bottom = new StackPane();
        bottom.prefWidthProperty().bind(bp.widthProperty().multiply(0.9));
        bottom.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        bp.setBottom(bottom);
        StackPane left = new StackPane();
        left.prefWidthProperty().bind(bp.widthProperty().multiply(0.1));
        left.prefHeightProperty().bind(bp.heightProperty().multiply(0.77));
        bp.setLeft(left);
        StackPane right = new StackPane();
        right.prefWidthProperty().bind(bp.widthProperty().multiply(0.1));
        right.prefHeightProperty().bind(bp.heightProperty().multiply(0.77));
        bp.setRight(right);

        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);

        this.panePrincipale.getChildren().add(pane);
    }

    @Override
    public void setTextWithCurrentLanguage() {
        stat.setText(controller.gestionnaireLangage.getText("text_statistiques"));
    }
}

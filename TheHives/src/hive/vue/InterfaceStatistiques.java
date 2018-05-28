/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceStatistiques extends Interface
{

    private final Label stat;

    public InterfaceStatistiques(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());




        pane.getChildren().add(droite);
        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);

        stat = new Label();

        setTextWithCurrentLanguage();

        stat.setFont(new Font(police, width / 35));
        stat.setAlignment(Pos.CENTER);
        stat.setMinSize(width / 60, 30);
        stat.setMaxSize(width / 2, 70);
        AnchorPane.setTopAnchor(stat, (double) tailleDeCase * 0.6);
        AnchorPane.setLeftAnchor(stat, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(stat, (double) tailleDeCase * 2);
        pane.getChildren().add(stat);

        ListView<Label> liste = new ListView<>();

        for (Map.Entry<String, Integer> entry : controller.scoresGesture.getScorePerPlayer().entrySet())
        {
            String playerName = entry.getKey();
            Integer playerScore = entry.getValue();

            Label playerNameScore = new Label(playerName + "   =   " + String.valueOf(playerScore));
            liste.getItems().add(playerNameScore);
        }

        AnchorPane.setTopAnchor(liste, (double) tailleDeCase);
        AnchorPane.setLeftAnchor(liste, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(liste, (double) tailleDeCase * 2);
        AnchorPane.setBottomAnchor(liste, (double) tailleDeCase);
        pane.getChildren().add(liste);

        this.panePrincipale.getChildren().add(pane);
    }

    @Override
    public void setTextWithCurrentLanguage() {
        stat.setText(controller.gestionnaireLangage.getText("text_statistiques"));
    }
}

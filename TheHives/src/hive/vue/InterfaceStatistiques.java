/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.StatistiqueGesture;
import java.util.HashMap;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public final class InterfaceStatistiques extends Interface
{

    private final Label stat;
    private final StackPane stackPanneau;
    private final ListView<HBox> listViewScores;

    public InterfaceStatistiques(Scene scene, Stage primaryStage, Controller controller, CacheImage c)
    {
        super(scene, primaryStage, controller, c);

        stat = new Label();
        stackPanneau = new StackPane();
        listViewScores = new ListView<>();

        setTextWithCurrentLanguage();
        setObjetsGraphiques();
        AnchorPane pane = placerObjetsGraphiques();

        this.panePrincipale.getChildren().add(pane);
    }

    private void setObjetsGraphiques()
    {
        stat.setTextFill(Color.web("#fbe5b5"));

        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(pancarte, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background _background = new Background(backgroundFond);
        stackPanneau.setBackground(_background);

        /* LIST_VIEW */
        for (Map.Entry<String, HashMap<String, String>> entry : StatistiqueGesture.getPlayersScores().entrySet())
        {
            String playerName = entry.getKey();
            HashMap<String, String> playerScores = entry.getValue();

            HBox hBoxPlayerNameScores = new HBox();
            VBox vBoxPlayerName = new VBox();
            VBox vBoxPlayerScores = new VBox();
            
            vBoxPlayerName.getChildren().add(new Label(playerName));
            
            for (Map.Entry<String, String> entry2 : playerScores.entrySet())
            {
                String opponentName = entry2.getKey();
                String score = entry2.getValue();
                vBoxPlayerScores.getChildren().add(new Label(opponentName + " : " + score));
            }
            
            vBoxPlayerName.setAlignment(Pos.CENTER_LEFT);
            vBoxPlayerScores.setAlignment(Pos.CENTER_LEFT);
            
            vBoxPlayerName.prefWidthProperty().bind(hBoxPlayerNameScores.widthProperty().divide(4));
            vBoxPlayerScores.prefWidthProperty().bind(hBoxPlayerNameScores.widthProperty().multiply(0.75));
            
            hBoxPlayerNameScores.getChildren().add(vBoxPlayerName);
            hBoxPlayerNameScores.getChildren().add(vBoxPlayerScores);
            
            hBoxPlayerNameScores.prefWidthProperty().bind(listViewScores.widthProperty());
            
            listViewScores.getItems().add(hBoxPlayerNameScores);
        }
    }

    private AnchorPane placerObjetsGraphiques()
    {
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

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

        stackPanneau.prefWidthProperty().bind(bp.widthProperty().multiply(0.3));
        stackPanneau.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));

        stat.prefHeightProperty().bind(stackPanneau.heightProperty().multiply(0.8));
        stat.prefWidthProperty().bind(stackPanneau.widthProperty().multiply(0.9));
        stat.setAlignment(Pos.CENTER);

        stackPanneau.getChildren().add(stat);

        AnchorPane.setLeftAnchor(stackPanneau, (double) tailleDeCase + 15);
        AnchorPane.setRightAnchor(stackPanneau, (double) tailleDeCase + 15);
        AnchorPane.setTopAnchor(stackPanneau, (double) 5);
        AnchorPane.setBottomAnchor(stackPanneau, (double) 5);
        top.getChildren().add(stackPanneau);
        bp.setTop(top);

        StackPane liste_sp = new StackPane();
        liste_sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.8));
        liste_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.77));

        listViewScores.prefHeightProperty().bind(liste_sp.heightProperty().multiply(0.8));
        listViewScores.prefWidthProperty().bind(liste_sp.widthProperty().multiply(0.9));

        liste_sp.getChildren().add(listViewScores);

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

        return pane;
    }

    @Override
    public void setTextWithCurrentLanguage()
    {
        stat.setText(controller.gestionnaireLangage.getText("text_statistiques"));
    }
}

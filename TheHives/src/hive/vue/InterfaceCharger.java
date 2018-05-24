/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import hive.model.game.Game;
import java.awt.Dimension;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Adeline
 */
public class InterfaceCharger extends Interface {


    public InterfaceCharger(Stage primaryStage, Controller controller, CacheImage c) throws IOException {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 15);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 10);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);


        Label choix = new Label(); // Scegliere partita salvata, Gespeichertes Spiel wählen
        Button valider = new Button();

        valider.setText(controller.gestionnaireLangage.getText("text_valider"));
        choix.setText(controller.gestionnaireLangage.getText("text_choisir_partie"));

        choix.setFont(new Font(police, width / 35));
        choix.setAlignment(Pos.CENTER);
        choix.setMinSize(width / 60, 30);
        choix.setMaxSize(width / 2, 70);

        final ComboBox parties = new ComboBox();
        for (int j = 0; j < 10; j++) {
            parties.getItems().add(j);
        }

        parties.setMaxSize(tailleDeCase * 3, tailleDeCase / 2);
        parties.setMinSize(tailleDeCase * 3, tailleDeCase / 2);
        AnchorPane.setTopAnchor(choix, (double) height / 10);
        AnchorPane.setLeftAnchor(choix, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(choix, (double) tailleDeCase * 2);
        //AnchorPane.setBottomAnchor(choix, (double) height/1.1);
        pane.getChildren().add(choix);

        AnchorPane.setTopAnchor(parties, (double) height / 4);
        AnchorPane.setLeftAnchor(parties, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(parties, (double) tailleDeCase * 2);
        //AnchorPane.setBottomAnchor(parties, (double) height/1.3);
        pane.getChildren().add(parties);

        StackPane valider_sp = new StackPane();
        valider.setFont(new Font(police, tailleDeCase * 0.23));
        valider_sp.getChildren().add(valider);
        valider_sp.setMaxSize(tailleDeCase, 40);
        valider_sp.setMinSize(tailleDeCase, 40);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(parties.getValue());
                Game game = controller.chargerGame("test.xml");
                controller.goToPlateau(game);
            }
        });
        if (height == max_screen_height) {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase * 1.5);
        } else {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase);
        }
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        AnchorPane.setLeftAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        AnchorPane.setRightAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        pane.getChildren().add(valider_sp);
        this.panePrincipale.getChildren().add(pane);
        this.getChildren().add(panePrincipale);

    }

    public void majRetourPreference() {
    }

}

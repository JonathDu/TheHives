/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.SavesGesture;
import hive.model.game.Game;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Adeline
 */
public class InterfaceCharger extends Interface {

    private final Label choix;
    private final Button valider;

    public InterfaceCharger(Scene scene, Stage primaryStage, Controller controller, CacheImage c) throws IOException {
        super(scene, primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

        choix = new Label(); // Scegliere partita salvata, Gespeichertes Spiel wählen
        valider = new Button();
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

        StackPane spR = new StackPane();
        spR.prefWidthProperty().bind(bp.widthProperty().multiply(0.3));
        spR.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        //TODO a modifier
        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(pancarte, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundFond);

        spR.setBackground(background);
        choix.prefHeightProperty().bind(spR.heightProperty().multiply(0.8));
        choix.prefWidthProperty().bind(spR.widthProperty().multiply(0.9));
        choix.setAlignment(Pos.CENTER);
        choix.setTextFill(Color.web("#fbe5b5"));
        spR.getChildren().add(choix);

        AnchorPane.setRightAnchor(spR, 0.0);
        AnchorPane.setLeftAnchor(spR, 0.0);
        AnchorPane.setTopAnchor(spR, 0.0);

        spR.setAlignment(Pos.CENTER);
        top.getChildren().add(spR);
        bp.setTop(top);

        AnchorPane ap = new AnchorPane();
        StackPane parties_sp = new StackPane();
        parties_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        parties_sp.prefWidthProperty().bind(bp.widthProperty());
        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView partiesIm = new ImageView(fleche);
        partiesIm.setPreserveRatio(true);
        partiesIm.fitWidthProperty().bind(scene.widthProperty().divide(15));
        final ComboBox<StackPane> parties = new ComboBox();
        StackPane text_sp = new StackPane();
        Label text = new Label();
        text.setText("Cliquez pour choisir");
        text.setTextFill(Color.web("#fbe5b5"));
        ImageView fleche_Im = new ImageView(fleche);
        fleche_Im.setPreserveRatio(true);
        fleche_Im.fitWidthProperty().bind(scene.widthProperty().divide(3));
        text_sp.getChildren().add(fleche_Im);
        text_sp.getChildren().add(text);

        for (String fileName : SavesGesture.getSavedFileNames()) {
            ImageView flecheIm = new ImageView(fleche);
            flecheIm.setPreserveRatio(true);
            flecheIm.fitWidthProperty().bind(scene.widthProperty().divide(3));
            String[] parts = fileName.split(".xml");
            Label label = new Label(parts[0]);
            label.setTextFill(Color.web("#fbe5b5"));
            StackPane x = new StackPane();
            x.getChildren().add(flecheIm);
            x.getChildren().add(label);
            parties.getItems().add(x);
        }
        parties.setValue(text_sp);
        parties.setBackground(Background.EMPTY);
        parties_sp.getChildren().add(parties);

        AnchorPane.setTopAnchor(parties_sp, (double) 5);
        AnchorPane.setLeftAnchor(parties_sp, (double) 5);
        AnchorPane.setBottomAnchor(parties_sp, (double) 5);
        AnchorPane.setRightAnchor(parties_sp, (double) 5);
        ap.getChildren().add(parties_sp);

        bp.setCenter(ap);

        StackPane valider_sp = new StackPane();
        valider_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.3));
        valider_sp.prefWidthProperty().bind(bp.widthProperty());
        Image val = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView valIm = new ImageView(val);

        valIm.setPreserveRatio(true);
        valIm.fitWidthProperty().bind(scene.widthProperty().divide(5));
        valider_sp.getChildren().add(valIm);
        valider.setTextFill(Color.web("#fbe5b5"));
        valider.setBackground(Background.EMPTY);
        valider_sp.getChildren().add(valider);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean isOk = false;
                String selectedFileName = ((Label) parties.getSelectionModel().getSelectedItem().getChildren().get(1)).getText();
                for (String fileName : SavesGesture.getSavedFileNames()) {
                    if ((selectedFileName + ".xml").equals(fileName)) {
                        isOk = true;
                    }
                }
                if (isOk) {
                    Game game = SavesGesture.loadGame(selectedFileName + ".xml");
                    controller.goToPlateau(game);
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Veuillez séléctionner une partie");
                    a.show();
                }
            }
        });

        bp.setBottom(valider_sp);
        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);

        this.panePrincipale.getChildren().add(pane);

    }

    @Override
    public void setTextWithCurrentLanguage() {
        valider.setText(controller.gestionnaireLangage.getText("text_valider"));
        choix.setText(controller.gestionnaireLangage.getText("text_choisir_partie"));
    }
}

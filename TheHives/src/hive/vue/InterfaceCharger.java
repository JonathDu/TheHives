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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
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

    public InterfaceCharger(Stage primaryStage, Controller controller, CacheImage c) throws IOException {
        super(primaryStage, controller, c);
        
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        choix = new Label(); // Scegliere partita salvata, Gespeichertes Spiel wählen
        valider = new Button();
        setTextWithCurrentLanguage();
        
        BorderPane bp = new BorderPane();
        bp.prefHeightProperty().bind(pane.heightProperty());
        bp.prefWidthProperty().bind(pane.widthProperty());
        
        AnchorPane top = new AnchorPane();
        top.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        top.prefWidthProperty().bind(bp.widthProperty());
       
        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 10);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        top.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        top.getChildren().add(boutonPleinEcran);

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
        //choix.setFont(new Font(police, tailleDeCase *0.2));
        choix.prefHeightProperty().bind(spR.heightProperty().multiply(0.8));
        choix.prefWidthProperty().bind(spR.widthProperty().multiply(0.9));
        choix.setAlignment(Pos.CENTER);
        choix.setTextFill(Color.web("#fbe5b5"));
        spR.getChildren().add(choix);
        
        AnchorPane.setLeftAnchor(spR, (double) tailleDeCase+15);
        AnchorPane.setRightAnchor(spR, (double) tailleDeCase+15);
        AnchorPane.setTopAnchor(spR, (double) 5);
        AnchorPane.setBottomAnchor(spR, (double) 5);
        top.getChildren().add(spR); 
        bp.setTop(top);
        
        double flecheLargeur = tailleDeCase * 4 - 30;
        double flecheHauteur = flecheLargeur / 7.24;
        AnchorPane ap = new AnchorPane();
        StackPane parties_sp = new StackPane();
        parties_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        parties_sp.prefWidthProperty().bind(bp.widthProperty());
        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView partiesIm = new ImageView(fleche);
        partiesIm.setFitHeight(flecheHauteur);
        partiesIm.setFitWidth(flecheLargeur);
        //parties_sp.getChildren().add(partiesIm);
        final ComboBox<StackPane> parties = new ComboBox();
        StackPane text_sp = new StackPane();
        Label text = new Label();
        text.setText("Cliquez pour choisir");
        text.setTextFill(Color.web("#fbe5b5"));
        //text.setFont(new Font(police, tailleDeCase * 0.23));
            ImageView fleche_Im = new ImageView(fleche);
            fleche_Im.setFitHeight(flecheHauteur);
            fleche_Im.setFitWidth(flecheLargeur);
        text_sp.getChildren().add(fleche_Im);
        text_sp.getChildren().add(text);
        
        for (String fileName : SavesGesture.getSavedFileNames()) {
            ImageView flecheIm = new ImageView(fleche);
            flecheIm.setFitHeight(flecheHauteur*0.5);
            flecheIm.setFitWidth(flecheLargeur);
            Label label = new Label(fileName);
            label.setTextFill(Color.web("#fbe5b5"));
            //label.setFont(new Font(police, tailleDeCase * 0.15));
            StackPane x = new StackPane();
            x.getChildren().add(flecheIm);
            x.getChildren().add(label);
            parties.getItems().add( x );
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
        valIm.setFitHeight(flecheHauteur);
        valIm.setFitWidth(flecheLargeur*0.6);
        valider_sp.getChildren().add(valIm);
        //valider.setFont(new Font(police, tailleDeCase * 0.23));
        valider.setTextFill(Color.web("#fbe5b5"));
        valider.setBackground(Background.EMPTY);
        valider_sp.getChildren().add(valider);
        //valider_sp.setMaxSize(tailleDeCase, 40);
        //valider_sp.setMinSize(tailleDeCase, 40);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedFileName = ((Label)parties.getSelectionModel().getSelectedItem().getChildren().get(1)).getText();
                Game game = SavesGesture.loadGame(selectedFileName);
                controller.goToPlateau(game);
            }
        });
        /*if (height == max_screen_height) {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase * 1.5);
        } else {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase);
        }*/
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        //AnchorPane.setLeftAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        //AnchorPane.setRightAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        //pane.getChildren().add(valider_sp);
        bp.setBottom(valider_sp);
        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);
        
        this.panePrincipale.getChildren().add(pane);

       /* AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 10);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);



        choix.setFont(new Font(police, tailleDeCase *0.3));
        choix.setAlignment(Pos.CENTER);
        choix.setTextFill(Color.web("#fbe5b5"));
        
        StackPane sp = new StackPane();
        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte.png");
        ImageView pancarteIm = new ImageView(pancarte);
        pancarteIm.setFitHeight(tailleDeCase * 0.8);
        pancarteIm.setFitWidth(tailleDeCase * 0.8 * 5.09);
        sp.getChildren().add(pancarteIm);
        sp.getChildren().add(choix);
        AnchorPane.setTopAnchor(sp, (double) tailleDeCase*0.15);
        AnchorPane.setLeftAnchor(sp, (double) tailleDeCase);
        AnchorPane.setRightAnchor(sp, (double) tailleDeCase);
        pane.getChildren().add(sp);
        
        double flecheLargeur = tailleDeCase * 4 - 30;
        double flecheHauteur = flecheLargeur / 7.24;
        
        StackPane parties_sp = new StackPane();
        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView partiesIm = new ImageView(fleche);
        partiesIm.setFitHeight(flecheHauteur);
        partiesIm.setFitWidth(flecheLargeur);
        //parties_sp.getChildren().add(partiesIm);
        final ComboBox<StackPane> parties = new ComboBox();
        StackPane text_sp = new StackPane();
        Label text = new Label();
        text.setText("Cliquez pour choisir");
        text.setTextFill(Color.web("#fbe5b5"));
        text.setFont(new Font(police, tailleDeCase * 0.23));
            ImageView fleche_Im = new ImageView(fleche);
            fleche_Im.setFitHeight(flecheHauteur);
            fleche_Im.setFitWidth(flecheLargeur);
        text_sp.getChildren().add(fleche_Im);
        text_sp.getChildren().add(text);
        
        for (String fileName : SavesGesture.getSavedFileNames()) {
            ImageView flecheIm = new ImageView(fleche);
            flecheIm.setFitHeight(flecheHauteur*0.5);
            flecheIm.setFitWidth(flecheLargeur);
            Label label = new Label(fileName);
            label.setTextFill(Color.web("#fbe5b5"));
            label.setFont(new Font(police, tailleDeCase * 0.15));
            StackPane x = new StackPane();
            x.getChildren().add(flecheIm);
            x.getChildren().add(label);
            parties.getItems().add( x );
        }
        parties.setValue(text_sp);
        parties.setBackground(Background.EMPTY);
        parties_sp.getChildren().add(parties);

        AnchorPane.setTopAnchor(parties_sp, (double) tailleDeCase*0.15 + tailleDeCase*0.9);
        AnchorPane.setLeftAnchor(parties_sp, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(parties_sp, (double) tailleDeCase * 2);
        //AnchorPane.setBottomAnchor(parties, (double) height/1.3);
        pane.getChildren().add(parties_sp);
        
        
        StackPane valider_sp = new StackPane();
        Image val = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView valIm = new ImageView(val);
        valIm.setFitHeight(flecheHauteur);
        valIm.setFitWidth(flecheLargeur);
        valider_sp.getChildren().add(valIm);
        valider.setFont(new Font(police, tailleDeCase * 0.23));
        valider.setTextFill(Color.web("#fbe5b5"));
        valider.setBackground(Background.EMPTY);
        valider_sp.getChildren().add(valider);
        valider_sp.setMaxSize(tailleDeCase, 40);
        valider_sp.setMinSize(tailleDeCase, 40);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedFileName = ((Label)parties.getSelectionModel().getSelectedItem().getChildren().get(1)).getText();
                Game game = SavesGesture.loadGame(selectedFileName);
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
        this.panePrincipale.getChildren().add(pane);*/

    }
    
    @Override
    public void setTextWithCurrentLanguage()
    {
        valider.setText(controller.gestionnaireLangage.getText("text_valider"));
        choix.setText(controller.gestionnaireLangage.getText("text_choisir_partie"));
    }
}

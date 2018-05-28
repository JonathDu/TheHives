/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceCredits extends Interface {

    private final Label credits;
    private final Label ia;
    private final Label ihm;
    private final Label design;
    private final Label mdj;
    private final Label traduction1;
    private final Label traduction2;

    public InterfaceCredits(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        credits = new Label();
        ia = new Label();
        ihm = new Label();
        design = new Label();
        mdj = new Label();
        traduction1 = new Label();
        traduction2 = new Label();

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

        StackPane spC = new StackPane();
        spC.prefWidthProperty().bind(bp.widthProperty().multiply(0.3));
        spC.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte1.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(pancarte, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundFond);
        spC.setBackground(background);
        credits.setAlignment(Pos.CENTER);
        credits.prefHeightProperty().bind(spC.heightProperty().multiply(0.8));
        credits.prefWidthProperty().bind(spC.widthProperty().multiply(0.9));
        credits.setTextFill(Color.web("#fbe5b5"));
        spC.getChildren().add(credits);
        AnchorPane.setLeftAnchor(spC, (double) tailleDeCase + 15);
        AnchorPane.setRightAnchor(spC, (double) tailleDeCase + 15);
        AnchorPane.setTopAnchor(spC, (double) 5);
        AnchorPane.setBottomAnchor(spC, (double) 5);
        top.getChildren().add(spC);
        bp.setTop(top);

        StackPane bois = new StackPane();
        bois.prefWidthProperty().bind(bp.widthProperty().multiply(0.9));
        bois.prefHeightProperty().bind(bp.heightProperty().multiply(0.77));
        Image plateau = c.getImage("PlateauCentral.png");
        BackgroundImage backgroundPlateau = new BackgroundImage(plateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundPlateau);
        bois.setBackground(background);

        GridPane grille = new GridPane();
        int ligne = 100 / 8;
        int colonne = 100 / 1;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne);
        grille.prefHeightProperty().bind(bois.heightProperty());
        grille.prefWidthProperty().bind(bois.widthProperty());
        double hauteurDeGrille = tailleDeCase * 4.2;
        double hauteurDeLigne = hauteurDeGrille / 6;
        StackPane ia_sp = new StackPane();
        ia_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        ia_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        ia.setTextFill(Color.web("#fbe5b5"));
        ia.prefHeightProperty().bind(ia_sp.heightProperty());
        ia.prefWidthProperty().bind(ia_sp.widthProperty());
        ia.setAlignment(Pos.CENTER);
        ia_sp.getChildren().add(ia);
        grille.add(ia_sp, 0, 1);
        StackPane ihm_sp = new StackPane();
        ihm_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        ihm_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        ihm.setTextFill(Color.web("#fbe5b5"));
        ihm.prefHeightProperty().bind(ihm_sp.heightProperty());
        ihm.prefWidthProperty().bind(ihm_sp.widthProperty());
        ihm.setAlignment(Pos.CENTER);
        ihm_sp.getChildren().add(ihm);
        grille.add(ihm_sp, 0, 2);
        StackPane design_sp = new StackPane();
        design_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        design_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        design.setTextFill(Color.web("#fbe5b5"));
        design.setAlignment(Pos.CENTER);
        design.prefHeightProperty().bind(design_sp.heightProperty());
        design.prefWidthProperty().bind(design_sp.widthProperty());
        design_sp.getChildren().add(design);
        grille.add(design_sp, 0, 3);
        StackPane mdj_sp = new StackPane();
        mdj_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        mdj_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        mdj.setTextFill(Color.web("#fbe5b5"));
        mdj.prefHeightProperty().bind(mdj_sp.heightProperty());
        mdj.prefWidthProperty().bind(mdj_sp.widthProperty());
        mdj.setAlignment(Pos.CENTER);
        mdj_sp.getChildren().add(mdj);
        grille.add(mdj_sp, 0, 4);
        StackPane traduction1_sp = new StackPane();
        traduction1_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        traduction1_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        traduction1.setTextFill(Color.web("#fbe5b5"));
        traduction1.prefHeightProperty().bind(traduction1_sp.heightProperty());
        traduction1.prefWidthProperty().bind(traduction1_sp.widthProperty());
        traduction1.setAlignment(Pos.CENTER);
        traduction1_sp.getChildren().add(traduction1);
        grille.add(traduction1_sp, 0, 5);
        StackPane traduction2_sp = new StackPane();
        traduction2_sp.prefHeightProperty().bind(bois.heightProperty().divide(0.6));
        traduction2_sp.prefWidthProperty().bind(bois.widthProperty().multiply(0.9));
        traduction2.setTextFill(Color.web("#fbe5b5"));
        traduction2.prefHeightProperty().bind(traduction2_sp.heightProperty());
        traduction2.prefWidthProperty().bind(traduction2_sp.widthProperty());
        traduction2.setAlignment(Pos.CENTER);
        traduction2_sp.getChildren().add(traduction2);
        grille.add(traduction2_sp, 0, 6);

        bois.getChildren().add(grille);

        bp.setCenter(bois);

        StackPane bottom = new StackPane();
        bottom.prefWidthProperty().bind(bp.widthProperty().multiply(0.9));
        bottom.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        bp.setBottom(bottom);

        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);
        this.panePrincipale.getChildren().add(pane);

    }

    @Override
    public void setTextWithCurrentLanguage() {
        credits.setText(controller.gestionnaireLangage.getText("text_credit"));
        ia.setText(controller.gestionnaireLangage.getText("text_membres_IA"));
        ihm.setText(controller.gestionnaireLangage.getText("text_membres_IHM"));
        design.setText(controller.gestionnaireLangage.getText("text_membres_design"));
        mdj.setText(controller.gestionnaireLangage.getText("text_membres_moteur"));
        traduction1.setText(controller.gestionnaireLangage.getText("text_membres_traduction1"));
        traduction2.setText(controller.gestionnaireLangage.getText("text_membres_traduction2"));
    }
}

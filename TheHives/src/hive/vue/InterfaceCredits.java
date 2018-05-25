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
import javafx.scene.layout.GridPane;
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

    public InterfaceCredits(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 25);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 20);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);

        credits = new Label();
        ia = new Label();
        ihm = new Label();
        design = new Label();
        mdj = new Label();

        setTextWithCurrentLanguage();

        credits.setFont(new Font(police, width / 35));
        credits.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(credits, (double) tailleDeCase * 0.6);
        AnchorPane.setLeftAnchor(credits, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(credits, (double) tailleDeCase * 2);
        pane.getChildren().add(credits);

        StackPane bois = new StackPane();
        Image plateau = c.getImage("PlateauCentral.png");
        ImageView plateauIm = new ImageView(plateau);
        plateauIm.setFitHeight((tailleDeCase * 6.56) / 1.6);
        plateauIm.setFitWidth(tailleDeCase * 6.56);
        bois.getChildren().add(plateauIm);

        GridPane grille = new GridPane();
        int ligne = 100 / 6;
        int colonne = 100 / 1;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(tailleDeCase * 6.4);
        grille.setMinWidth(tailleDeCase * 6.4);
        grille.setMaxHeight(tailleDeCase * 4.2);
        grille.setMinHeight(tailleDeCase * 4.2);
        double hauteurDeGrille = tailleDeCase * 4.2;
        double hauteurDeLigne = hauteurDeGrille / 6;

        ia.setFont(new Font(police, tailleDeCase * 0.17));
        ia.setTextFill(Color.web("#fbe5b5"));
        ia.setAlignment(Pos.CENTER);
        StackPane ia_sp = new StackPane();
        ia_sp.getChildren().add(ia);
        grille.add(ia_sp, 0, 1);
        ihm.setFont(new Font(police, tailleDeCase * 0.17));
        ihm.setTextFill(Color.web("#fbe5b5"));
        ihm.setAlignment(Pos.CENTER);
        StackPane ihm_sp = new StackPane();
        ihm_sp.getChildren().add(ihm);
        grille.add(ihm_sp, 0, 2);
        design.setFont(new Font(police, tailleDeCase * 0.17));
        design.setTextFill(Color.web("#fbe5b5"));
        design.setAlignment(Pos.CENTER);
        StackPane design_sp = new StackPane();
        design_sp.getChildren().add(design);
        grille.add(design_sp, 0, 3);
        mdj.setFont(new Font(police, tailleDeCase * 0.17));
        mdj.setTextFill(Color.web("#fbe5b5"));
        mdj.setAlignment(Pos.CENTER);
        StackPane mdj_sp = new StackPane();
        mdj_sp.getChildren().add(mdj);
        grille.add(mdj_sp, 0, 4);

        bois.getChildren().add(grille);

        AnchorPane.setTopAnchor(bois, (double) tailleDeCase * 0.6);
        AnchorPane.setBottomAnchor(bois, (double) tailleDeCase * 0.3);
        AnchorPane.setLeftAnchor(bois, (double) tailleDeCase * 0.8);
        AnchorPane.setRightAnchor(bois, (double) tailleDeCase * 0.8);
        pane.getChildren().add(bois);

        this.panePrincipale.getChildren().add(pane);

    }
    
    @Override
    public void setTextWithCurrentLanguage()
    {
        credits.setText(controller.gestionnaireLangage.getText("text_credit"));
        ia.setText(controller.gestionnaireLangage.getText("text_membres_IA"));
        ihm.setText(controller.gestionnaireLangage.getText("text_membres_IHM"));
        design.setText(controller.gestionnaireLangage.getText("text_membres_design"));
        mdj.setText(controller.gestionnaireLangage.getText("text_membres_moteur"));
    }
}

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceRegles extends Interface
{

    private AnchorPane pane;

    private Label regles;
    private Label but;
    private Label debut;
    private Label deplacement;
    private Label tour;
    private Label araignee;
    private Label fourmi;
    private Label reine;
    private Label sauterelle;
    private Label scarabee;
    private Label exception;
    private Label jeu;
    private Label insecte;
    private String rep;

    public InterfaceRegles(Stage primaryStage, Controller controller, CacheImage c)
    {
        super(primaryStage, controller, c);

        rep = controller.gestionnaireLangage.getText("text_regle_image_rep");

        pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        /*AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 10);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);*/

        regles(police, width, height, tailleDeCase, maxJoueur, minJoueur, rep);

    }

    public InterfaceRegles(Stage primaryStage, Controller controller, CacheImage c, boolean fenetre)
    {
        super(primaryStage, controller, c);

        rep = controller.gestionnaireLangage.getText("text_regle_image_rep");

        pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        regles(police, width, height, tailleDeCase, maxJoueur, minJoueur, rep);
    }

    private void regles(String police, int width, int height, int tailleDeCase, int maxJoueur, int minJoueur, String rep)
    {
        //BorderPane choisir = new BorderPane();
        regles = new Label();
        but = new Label();
        debut = new Label();
        deplacement = new Label();
        tour = new Label();
        araignee = new Label();
        fourmi = new Label();
        reine = new Label();
        sauterelle = new Label();
        scarabee = new Label();
        exception = new Label();
        jeu = new Label();
        insecte = new Label();

        
        double tailleLettres = tailleDeCase*0.2;
        setTextWithCurrentLanguage();

        

        StackPane sp_centre = new StackPane();
        sp_centre.prefWidthProperty().bind(primaryStage.widthProperty());

        /*GridPane centre = new GridPane();
        //centre.prefWidthProperty().bind(primaryStage.widthProperty());
        //centre.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.8));
        centre.setMaxWidth(width*0.99);
        centre.setMinWidth(width*0.99);
        centre.setMaxHeight(tailleDeCase * 3.6);
        centre.setMinHeight(tailleDeCase * 3.6);
        Outils.fixerRepartition(centre, Outils.HORIZONTAL, (tailleDeCase/height)*100, 100-tailleDeCase/height*100*2, tailleDeCase/height*100);
        Outils.fixerRepartition(centre, Outils.VERTICAL, 25, 50, 25);*/
        BorderPane bp = new BorderPane();
        bp.prefHeightProperty().bind(primaryStage.heightProperty());
        bp.prefWidthProperty().bind(primaryStage.widthProperty());
        
        AnchorPane top = new AnchorPane();
        top.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        top.prefWidthProperty().bind(bp.widthProperty());
       
        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 10);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        AnchorPane.setBottomAnchor(boutonPreference, (double) 5);
        top.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setBottomAnchor(boutonPleinEcran, (double) 5);
        top.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setBottomAnchor(boutonRetourMenu, (double) 5);
        top.getChildren().add(boutonRetourMenu);
        
        
        
        
        StackPane spR = new StackPane();
        spR.prefWidthProperty().bind(bp.widthProperty().multiply(0.7));
        spR.prefHeightProperty().bind(bp.heightProperty().multiply(0.1));
        Image pancarte = c.getImage("plusDeBoutons/plusDeBoutons/Pancarte.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(pancarte, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundFond);
       
        spR.setBackground(background);
        regles.setFont(new Font(police, tailleDeCase *0.3));
        regles.prefHeightProperty().bind(spR.heightProperty().multiply(0.8));
        regles.prefWidthProperty().bind(spR.widthProperty().multiply(0.9));
        regles.setAlignment(Pos.CENTER);
        regles.setTextFill(Color.web("#fbe5b5"));
        spR.getChildren().add(regles);
        //AnchorPane.setTopAnchor(spR, (double) tailleDeCase*0.15);
        //AnchorPane.setLeftAnchor(spR, (double) tailleDeCase);
        //AnchorPane.setRightAnchor(spR, (double) tailleDeCase);
       // pane.getChildren().add(spR);
       //centre.add(spR, 1, 0);
        
        AnchorPane.setLeftAnchor(spR, (double) tailleDeCase+15);
        AnchorPane.setRightAnchor(spR, (double) tailleDeCase+15);
        AnchorPane.setTopAnchor(spR, (double) 5);
        AnchorPane.setBottomAnchor(spR, (double) 5);
        top.getChildren().add(spR); 
        bp.setTop(top);

        Image regle = c.getImage("Regles/PanneauAfficheRegle1.png");
       
        StackPane regles1 = new StackPane();
        regles1.prefWidthProperty().bind(bp.widthProperty().multiply(0.25));
        regles1.prefHeightProperty().bind(bp.heightProperty());
        BackgroundSize regle1Size = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage regle1Fond = new BackgroundImage(regle, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, regle1Size);
        background = new Background(regle1Fond);
        regles1.setBackground(background);
        //ImageView reglesIm1 = new ImageView(regle);
        //reglesIm1.fitHeightProperty().bind(regles1.heightProperty().multiply(0.9));
        //reglesIm1.fitWidthProperty().bind(regles1.widthProperty().multiply(0.9));
        //reglesIm1.setPreserveRatio(true);
        //reglesIm1.fitWidthProperty().bind(regles1.widthProperty());
        //reglesIm1.fitHeightProperty().bind(regles1.heightProperty()); 
        //reglesIm1.setPreserveRatio(true);
//        reglesIm1.setFitHeight(tailleDeCase * 3.6);
//        reglesIm1.setFitWidth(width*0.99 * 0.25);
        //AnchorPane.setTopAnchor(reglesIm1, (double) tailleDeCase*1.5);
        //AnchorPane.setLeftAnchor(reglesIm1, (double) 10);
        //pane.getChildren().add(reglesIm1);
//        regles1.getChildren().add(reglesIm1);

        GridPane placement = new GridPane();
        int ligne = 100 / 9;
        int colonne = 100 / 1;
        Outils.fixerRepartition(placement, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(placement, Outils.VERTICAL, colonne);
        /*placement.setMaxWidth(width*0.99 * 0.2);
        placement.setMinWidth(width*0.99 * 0.2);
        placement.setMaxHeight(tailleDeCase * 3.6);
        placement.setMinHeight(tailleDeCase * 3.6);*/
        placement.prefWidthProperty().bind(regles1.widthProperty().multiply(0.8));
        placement.prefHeightProperty().bind(regles1.heightProperty().multiply(0.5));
        double hauteurDeGrille = tailleDeCase * 2.4;
        double hauteurDeLigne = hauteurDeGrille / 4;

        jeu.setFont(new Font(police, tailleLettres+5));
        jeu.setAlignment(Pos.CENTER);
        //jeu.setMinSize(minJoueur, 30);
        //jeu.setMaxSize(maxJoueur, 70);
        jeu.setTextFill(Color.web("#fbe5b5"));
        StackPane j = new StackPane();
        j.getChildren().add(jeu);
        placement.add(j, 0, 1);

        but.setFont(new Font(police, tailleLettres));
//        but.setAlignment(Pos.CENTER);
//        but.setMinSize(minJoueur, 30);
//        but.setMaxSize(maxJoueur, 70);
        but.setTextFill(Color.web("#fbe5b5"));
        StackPane b = new StackPane();
        b.getChildren().add(but);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            bp.getChildren().remove(bp.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/butDuJeu.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        placement.add(b, 0, 2);
        debut.setFont(new Font(police, tailleLettres));
        debut.setAlignment(Pos.CENTER);
        debut.setTextFill(Color.web("#fbe5b5"));
//        debut.setMinSize(minJoueur, 30);
//        debut.setMaxSize(maxJoueur, 70);
        StackPane d = new StackPane();
        d.getChildren().add(debut);
        d.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            bp.getChildren().remove(bp.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Début de partie.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        placement.add(d, 0, 3);
        deplacement.setFont(new Font(police, tailleLettres));
        deplacement.setAlignment(Pos.CENTER);
        deplacement.setTextFill(Color.web("#fbe5b5"));
//        deplacement.setMinSize(minJoueur, 30);
//        deplacement.setMaxSize(maxJoueur, 70);
        StackPane de = new StackPane();
        de.getChildren().add(deplacement);
        de.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            bp.getChildren().remove(bp.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Déplacement.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        placement.add(de, 0, 4);
        tour.setFont(new Font(police, tailleLettres));
        tour.setAlignment(Pos.CENTER);
        tour.setTextFill(Color.web("#fbe5b5"));
//        tour.setMinSize(minJoueur, 30);
//        tour.setMaxSize(maxJoueur, 70);
        StackPane t = new StackPane();
        t.getChildren().add(tour);
        t.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            bp.getChildren().remove(bp.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/deroulementTour.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        placement.add(t, 0, 5);
        regles1.getChildren().add(placement);
        //centre.add(regles1, 0, 1);
        bp.setLeft(regles1);
        
        /*StackPane regles2 = new StackPane();
        ImageView reglesIm2 = new ImageView(regle);
        reglesIm2.setFitHeight(tailleDeCase * 3.6);
        reglesIm2.setFitWidth(width*0.99 * 0.25);
        regles2.getChildren().add(reglesIm2);*/
        
        StackPane regles2 = new StackPane();
        regles2.prefWidthProperty().bind(bp.widthProperty().multiply(0.25));
        regles2.prefHeightProperty().bind(bp.heightProperty());
        BackgroundSize regle2Size = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage regle2Fond = new BackgroundImage(regle, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, regle2Size);
        background = new Background(regle2Fond);
        regles2.setBackground(background);

        GridPane insectes = new GridPane();
        int ligne2 = 100 / 10;
        Outils.fixerRepartition(insectes, Outils.HORIZONTAL, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2);
        Outils.fixerRepartition(insectes, Outils.VERTICAL, colonne);
        /*insectes.setMaxWidth(width*0.99 * 0.2);
        insectes.setMinWidth(width*0.99 * 0.2);
        insectes.setMaxHeight(tailleDeCase * 3.6);
        insectes.setMinHeight(tailleDeCase * 3.6);*/
        
        insectes.prefWidthProperty().bind(regles2.widthProperty().multiply(0.8));
        insectes.prefHeightProperty().bind(regles2.heightProperty().multiply(0.5));
        double hauteurDeLigne2 = hauteurDeGrille / 6;

        insecte.setFont(new Font(police, tailleLettres+5));
        insecte.setAlignment(Pos.CENTER);
//        insecte.setMinSize(minJoueur, 30);
//        insecte.setMaxSize(maxJoueur, 70);
        insecte.setTextFill(Color.web("#fbe5b5"));
        StackPane in = new StackPane();
        in.getChildren().add(insecte);
        insectes.add(in, 0, 1);

        araignee.setFont(new Font(police, tailleLettres));
        araignee.setAlignment(Pos.CENTER);
        araignee.setTextFill(Color.web("#fbe5b5"));
//        araignee.setMinSize(minJoueur, 30);
//        araignee.setMaxSize(maxJoueur, 70);
        StackPane a = new StackPane();
        a.getChildren().add(araignee);
        a.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Araignée.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(a, 0, 2);
        fourmi.setFont(new Font(police, tailleLettres));
        fourmi.setAlignment(Pos.CENTER);
        fourmi.setTextFill(Color.web("#fbe5b5"));
//        fourmi.setMinSize(minJoueur, 30);
//        fourmi.setMaxSize(maxJoueur, 70);
        StackPane f = new StackPane();
        f.getChildren().add(fourmi);
        f.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Fourmis.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(f, 0, 3);
        reine.setFont(new Font(police, tailleLettres));
        reine.setAlignment(Pos.CENTER);
        reine.setTextFill(Color.web("#fbe5b5"));
//        reine.setMinSize(minJoueur, 30);
//        reine.setMaxSize(maxJoueur, 70);
        StackPane r = new StackPane();
        r.getChildren().add(reine);
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Reine.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(r, 0, 4);
        sauterelle.setFont(new Font(police, tailleLettres));
        sauterelle.setAlignment(Pos.CENTER);
        sauterelle.setTextFill(Color.web("#fbe5b5"));
//        sauterelle.setMinSize(minJoueur, 30);
//        sauterelle.setMaxSize(maxJoueur, 70);
        StackPane s = new StackPane();
        s.getChildren().add(sauterelle);
        s.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Sauterelle.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(s, 0, 5);
        scarabee.setFont(new Font(police, tailleLettres));
        scarabee.setAlignment(Pos.CENTER);
        scarabee.setTextFill(Color.web("#fbe5b5"));
//        scarabee.setMinSize(minJoueur, 30);
//        scarabee.setMaxSize(maxJoueur, 70);
        StackPane sc = new StackPane();
        sc.getChildren().add(scarabee);
        sc.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Scarabee.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(sc, 0, 6);
        exception.setFont(new Font(police, tailleLettres));
        exception.setAlignment(Pos.CENTER);
        exception.setTextFill(Color.web("#fbe5b5"));
//        exception.setMinSize(minJoueur, 30);
//        exception.setMaxSize(maxJoueur, 70);
        StackPane e = new StackPane();
        e.getChildren().add(exception);
        e.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            bp.getChildren().remove(bp.getChildren().size() - 1);
            //centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Exception.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.5));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
            //centre.add(sp, 1, 1);
            bp.setCenter(sp);
        });
        insectes.add(e, 0, 7);

        regles2.getChildren().add(insectes);
        //centre.add(regles2, 2, 1);
        bp.setRight(regles2);
        StackPane sp = new StackPane();
        Image image = c.getImage("Regles/" + rep + "/butDuJeu.png");
            sp.prefWidthProperty().bind(bp.widthProperty().multiply(0.3));
            sp.prefHeightProperty().bind(bp.heightProperty());
            BackgroundSize spSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage spFond = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, spSize);
            background = new Background(spFond);
            sp.setBackground(background);
            
            /*ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase*8 - (tailleDeCase*2) * 2);
            Im.setFitWidth(tailleDeCase*8 - (tailleDeCase*2) * 2);
            sp.getChildren().add(Im);*/
            //centre.add(sp, 1, 1);
        //centre.add(sp, 1, 1);
        bp.setCenter(sp);
        
        //sp_centre.getChildren().add(bp);

        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);
        this.panePrincipale.getChildren().add(pane);
    }

    @Override
    public void setTextWithCurrentLanguage()
    {
        regles.setText(controller.gestionnaireLangage.getText("text_regles"));
        but.setText(controller.gestionnaireLangage.getText("text_regle_but"));
        debut.setText(controller.gestionnaireLangage.getText("text_regle_debut"));
        deplacement.setText(controller.gestionnaireLangage.getText("text_regle_deplacement"));
        tour.setText(controller.gestionnaireLangage.getText("text_regle_deroulement"));
        araignee.setText(controller.gestionnaireLangage.getText("text_regle_araignee"));
        fourmi.setText(controller.gestionnaireLangage.getText("text_regle_fourmi"));
        reine.setText(controller.gestionnaireLangage.getText("text_regle_reine"));
        sauterelle.setText(controller.gestionnaireLangage.getText("text_regle_sauterelle"));
        scarabee.setText(controller.gestionnaireLangage.getText("text_regle_scrarabee"));
        exception.setText(controller.gestionnaireLangage.getText("text_regle_exception"));
        jeu.setText(controller.gestionnaireLangage.getText("text_regle_jeu"));
        insecte.setText(controller.gestionnaireLangage.getText("text_regle_insect"));
    }

    @Override
    public void majRetourPreference()
    {
        super.majRetourPreference();
        rep = controller.gestionnaireLangage.getText("text_regle_image_rep");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.thehives.TheHives;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class InterfaceRegles extends Parent {

    private AnchorPane pane;
    Stage primaryStage;
    Controller controller;
    CacheImage c;

    public InterfaceRegles(Stage primaryStage, Controller controller) {
        this.c = new CacheImage();
        this.controller = controller;
        this.primaryStage = primaryStage;
        if (controller.pleinEcran == 1) {
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }

        String rep = "";

        String police = controller.getPolice();

        pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;

        Image fond;
        if(controller.typeTheme=="jour"){
            fond = c.getImage("Design/Fond/fondMontagne.png");
        }
        else{
            fond = c.getImage("Design/Fond/fondNuit.png");
        }
        ImageView fondIm = new ImageView(fond);
        fondIm.fitHeightProperty().bind(primaryStage.heightProperty());
        fondIm.fitWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane.setRightAnchor(fondIm, (double) 0);
        AnchorPane.setLeftAnchor(fondIm, (double) 0);
        AnchorPane.setTopAnchor(fondIm, (double) 0);
        AnchorPane.setBottomAnchor(fondIm, (double) 0);
        pane.getChildren().add(fondIm);

        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences);
        prefIm.setFitHeight(tailleDeCase / 2);
        prefIm.setFitWidth(tailleDeCase / 2 * 1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            /*Preferences p = new Preferences(primaryStage, i);

            pane.getChildren().add(p);
            StackPane pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(tailleDeCase / 2.5);
            ImQ.setFitWidth(tailleDeCase / 2.5);
            pref.getChildren().add(ImQ);
            pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().remove(pane.getChildren().size() - 2, pane.getChildren().size());
                i.goToRegles();

            });
            AnchorPane.setRightAnchor(pref, (double) 5);
            AnchorPane.setTopAnchor(pref, (double) 5);
            pane.getChildren().add(pref);*/

            Preferences p = new Preferences(primaryStage, controller, new CacheImage());
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase / 2 * 1.07 + 15);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);

        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein);
        pleinIm.setFitHeight(tailleDeCase / 2);
        pleinIm.setFitWidth(tailleDeCase / 2 * 1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran=1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 10);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);

        StackPane Menu = new StackPane();
        Image menu = c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png");
        ImageView menuIm = new ImageView(menu);
        menuIm.setFitHeight(tailleDeCase / 2);
        menuIm.setFitWidth(tailleDeCase / 2 * 1.07);
        Menu.getChildren().add(menuIm);
        Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
        AnchorPane.setLeftAnchor(Menu, (double) 5);
        AnchorPane.setTopAnchor(Menu, (double) 5);
        pane.getChildren().add(Menu);

        regles(police, width, height, tailleDeCase, maxJoueur, minJoueur, rep);

    }

    public InterfaceRegles(Stage primaryStage, Controller controller, boolean fenetre) {
        this.c = new CacheImage();
        this.controller = controller;
        this.primaryStage = primaryStage;

        if (controller.pleinEcran == 1) {
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        CacheImage c = new CacheImage();

        String rep = "";

        String police = controller.getPolice();

        pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width / 8;
        int maxJoueur = (int) ((int) width / 2.5);
        int minJoueur = maxJoueur / 2;

        Image fond = c.getImage("Design/Fond/fondMontagne.png");
        ImageView fondIm = new ImageView(fond);
        fondIm.fitHeightProperty().bind(primaryStage.heightProperty());
        fondIm.fitWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane.setRightAnchor(fondIm, (double) 0);
        AnchorPane.setLeftAnchor(fondIm, (double) 0);
        AnchorPane.setTopAnchor(fondIm, (double) 0);
        AnchorPane.setBottomAnchor(fondIm, (double) 0);
        pane.getChildren().add(fondIm);

        regles(police, width, height, tailleDeCase, maxJoueur, minJoueur, rep);
    }

    private void regles(String police, int width, int height, int tailleDeCase, int maxJoueur, int minJoueur, String rep) {
        //BorderPane choisir = new BorderPane();
        //Label regles = new Label();
        Label but = new Label();
        Label debut = new Label();
        Label deplacement = new Label();
        Label tour = new Label();
        Label araignee = new Label();
        Label fourmi = new Label();
        Label reine = new Label();
        Label sauterelle = new Label();
        Label scarabee = new Label();
        Label exception = new Label();
        Label jeu = new Label();
        Label insecte = new Label();

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
        
        StackPane spR = new StackPane();
        Image regles = c.getImage("Regles/" + rep + "/LesRegles.png");
        ImageView reglesIm = new ImageView(regles);
        reglesIm.setFitHeight(tailleDeCase * 0.8);
        reglesIm.setFitWidth(tailleDeCase * 0.8 * 5.09);
        spR.getChildren().add(reglesIm);
        AnchorPane.setTopAnchor(spR, (double) height / 40);
        AnchorPane.setLeftAnchor(spR, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(spR, (double) tailleDeCase * 2);
        pane.getChildren().add(spR);

        StackPane sp_centre = new StackPane();
        sp_centre.prefWidthProperty().bind(primaryStage.widthProperty());

        GridPane centre = new GridPane();
        //centre.prefWidthProperty().bind(primaryStage.widthProperty());
        centre.setMaxWidth(width * 0.99);
        centre.setMinWidth(width * 0.99);
        centre.setMaxHeight(height * 0.6);
        centre.setMinHeight(height * 0.6);
        Outils.fixerRepartition(centre, Outils.HORIZONTAL, 100);
        Outils.fixerRepartition(centre, Outils.VERTICAL, 25, 50, 25);

        StackPane regles1 = new StackPane();
        Image regle = c.getImage("Regles/PanneauAfficheRegle.png");
        ImageView reglesIm1 = new ImageView(regle);
        reglesIm1.setFitHeight(width * 0.99 * 0.25 * 2);
        reglesIm1.setFitWidth(width * 0.99 * 0.25);
        //AnchorPane.setTopAnchor(reglesIm1, (double) tailleDeCase*1.5);
        //AnchorPane.setLeftAnchor(reglesIm1, (double) 10);
        //pane.getChildren().add(reglesIm1);
        regles1.getChildren().add(reglesIm1);

        GridPane placement = new GridPane();
        int ligne = 100 / 6;
        int colonne = 100 / 1;
        Outils.fixerRepartition(placement, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(placement, Outils.VERTICAL, colonne);
        placement.setMaxWidth(width*0.99*0.2);
        placement.setMinWidth(width*0.99*0.2);
        placement.setMaxHeight(width*0.99*0.25*1.7);
        placement.setMinHeight(width*0.99*0.25*1.7);
        double hauteurDeGrille = height*0.4;
        double hauteurDeLigne = hauteurDeGrille/4;

        jeu.setFont(new Font(police, maxJoueur/13));
        jeu.setAlignment(Pos.CENTER);
        jeu.setMinSize(minJoueur, 30);
        jeu.setMaxSize(maxJoueur, 70);
        jeu.setTextFill(Color.web("#fbe5b5"));
        StackPane j = new StackPane();
        j.getChildren().add(jeu);
        placement.add(j, 0, 0);

        but.setFont(new Font(police, maxJoueur/20));
        but.setAlignment(Pos.CENTER);
        but.setMinSize(minJoueur, 30);
        but.setMaxSize(maxJoueur, 70);
        but.setTextFill(Color.web("#fbe5b5"));
        StackPane b = new StackPane();
        b.getChildren().add(but);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/butDuJeu.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        placement.add(b, 0, 1);
        debut.setFont(new Font(police, maxJoueur / 20));
        debut.setAlignment(Pos.CENTER);
        debut.setTextFill(Color.web("#fbe5b5"));
        debut.setMinSize(minJoueur, 30);
        debut.setMaxSize(maxJoueur, 70);
        StackPane d = new StackPane();
        d.getChildren().add(debut);
        d.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Début de partie.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        placement.add(d, 0, 2);
        deplacement.setFont(new Font(police, maxJoueur / 20));
        deplacement.setAlignment(Pos.CENTER);
        deplacement.setTextFill(Color.web("#fbe5b5"));
        deplacement.setMinSize(minJoueur, 30);
        deplacement.setMaxSize(maxJoueur, 70);
        StackPane de = new StackPane();
        de.getChildren().add(deplacement);
        de.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Déplacement.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        placement.add(de, 0, 3);
        tour.setFont(new Font(police, maxJoueur / 20));
        tour.setAlignment(Pos.CENTER);
        tour.setTextFill(Color.web("#fbe5b5"));
        tour.setMinSize(minJoueur, 30);
        tour.setMaxSize(maxJoueur, 70);
        StackPane t = new StackPane();
        t.getChildren().add(tour);
        t.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/deroulementTour.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        placement.add(t, 0, 4);
        regles1.getChildren().add(placement);
        centre.add(regles1, 0, 0);

        /*AnchorPane.setTopAnchor(placement, (double) tailleDeCase*1.5);
        AnchorPane.setLeftAnchor(placement, (double) 10);
        //AnchorPane.setRightAnchor(placement, (double) tailleDeCase*2);
        AnchorPane.setBottomAnchor(placement, (double) tailleDeCase*2);
        pane.getChildren().add(placement);*/
        StackPane regles2 = new StackPane();
        ImageView reglesIm2 = new ImageView(regle);
        reglesIm2.setFitHeight(width * 0.99 * 0.25 * 2);
        reglesIm2.setFitWidth(width * 0.99 * 0.25);
        //AnchorPane.setTopAnchor(reglesIm2, (double) tailleDeCase*1.5);
        //AnchorPane.setLeftAnchor(reglesIm2, (double) 10);
        //pane.getChildren().add(reglesIm1);
        regles2.getChildren().add(reglesIm2);

        GridPane insectes = new GridPane();
        int ligne2 = 100 / 8;
        Outils.fixerRepartition(insectes, Outils.HORIZONTAL, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2);
        Outils.fixerRepartition(insectes, Outils.VERTICAL, colonne);
        insectes.setMaxWidth(width*0.99*0.2);
        insectes.setMinWidth(width*0.99*0.2);
        insectes.setMaxHeight(width*0.99*0.25*1.7);
        insectes.setMinHeight(width*0.99*0.25*1.7);
        double hauteurDeLigne2 = hauteurDeGrille/6;

        insecte.setFont(new Font(police, maxJoueur/13));
        insecte.setAlignment(Pos.CENTER);
        insecte.setMinSize(minJoueur, 30);
        insecte.setMaxSize(maxJoueur, 70);
        insecte.setTextFill(Color.web("#fbe5b5"));
        StackPane in = new StackPane();
        in.getChildren().add(insecte);
        insectes.add(in, 0, 0);

        araignee.setFont(new Font(police, maxJoueur/20));
        araignee.setAlignment(Pos.CENTER);
        araignee.setTextFill(Color.web("#fbe5b5"));
        araignee.setMinSize(minJoueur, 30);
        araignee.setMaxSize(maxJoueur, 70);
        StackPane a = new StackPane();
        a.getChildren().add(araignee);
        a.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Araignée.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(a, 0, 1);
        fourmi.setFont(new Font(police, maxJoueur / 20));
        fourmi.setAlignment(Pos.CENTER);
        fourmi.setTextFill(Color.web("#fbe5b5"));
        fourmi.setMinSize(minJoueur, 30);
        fourmi.setMaxSize(maxJoueur, 70);
        StackPane f = new StackPane();
        f.getChildren().add(fourmi);
        f.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Fourmis.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(f, 0, 2);
        reine.setFont(new Font(police, maxJoueur / 20));
        reine.setAlignment(Pos.CENTER);
        reine.setTextFill(Color.web("#fbe5b5"));
        reine.setMinSize(minJoueur, 30);
        reine.setMaxSize(maxJoueur, 70);
        StackPane r = new StackPane();
        r.getChildren().add(reine);
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Reine.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(r, 0, 3);
        sauterelle.setFont(new Font(police, maxJoueur / 20));
        sauterelle.setAlignment(Pos.CENTER);
        sauterelle.setTextFill(Color.web("#fbe5b5"));
        sauterelle.setMinSize(minJoueur, 30);
        sauterelle.setMaxSize(maxJoueur, 70);
        StackPane s = new StackPane();
        s.getChildren().add(sauterelle);
        s.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Sauterelle.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(s, 0, 4);
        scarabee.setFont(new Font(police, maxJoueur / 20));
        scarabee.setAlignment(Pos.CENTER);
        scarabee.setTextFill(Color.web("#fbe5b5"));
        scarabee.setMinSize(minJoueur, 30);
        scarabee.setMaxSize(maxJoueur, 70);
        StackPane sc = new StackPane();
        sc.getChildren().add(scarabee);
        sc.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Scarabee.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(sc, 0, 5);
        exception.setFont(new Font(police, maxJoueur / 20));
        exception.setAlignment(Pos.CENTER);
        exception.setTextFill(Color.web("#fbe5b5"));
        exception.setMinSize(minJoueur, 30);
        exception.setMaxSize(maxJoueur, 70);
        StackPane e = new StackPane();
        e.getChildren().add(exception);
        e.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Exception.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
            //pane.getChildren().add(sp);
            centre.add(sp, 1, 0);
        });
        insectes.add(e, 0, 6);

        //AnchorPane.setTopAnchor(insectes, (double) tailleDeCase*1.5);
        //AnchorPane.setRightAnchor(insectes, (double) width/6);
        //AnchorPane.setRightAnchor(insectes, (double) tailleDeCase*2);
        //AnchorPane.setBottomAnchor(insectes, (double) tailleDeCase*2);
        regles2.getChildren().add(insectes);
        centre.add(regles2, 2, 0);

        //pane.getChildren().add(insectes);
        StackPane sp = new StackPane();
        Image image = c.getImage("Regles/" + rep + "/butDuJeu.png");
        ImageView Im = new ImageView(image);
        Im.setFitHeight(width - (width / 4) * 2);
        Im.setFitWidth(width - (width / 4) * 2);
        sp.getChildren().add(Im);
        /*AnchorPane.setTopAnchor(sp, (double) tailleDeCase*1.3);
            AnchorPane.setLeftAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setRightAnchor(sp, (double) tailleDeCase*2);
            AnchorPane.setBottomAnchor(sp, (double) tailleDeCase*1.7);*/
        //pane.getChildren().add(sp);
        centre.add(sp, 1, 0);

        sp_centre.getChildren().add(centre);

        AnchorPane.setTopAnchor(sp_centre, (double) height * 0.2);
        AnchorPane.setBottomAnchor(sp_centre, (double) height * 0.1);
        pane.getChildren().add(sp_centre);
        this.getChildren().add(pane);
    }

}

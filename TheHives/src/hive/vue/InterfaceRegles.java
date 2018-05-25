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

        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 15);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 10);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);

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
        //Label regles = new Label();
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

        setTextWithCurrentLanguage();

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
        placement.setMaxWidth(width * 0.99 * 0.2);
        placement.setMinWidth(width * 0.99 * 0.2);
        placement.setMaxHeight(width * 0.99 * 0.25 * 1.7);
        placement.setMinHeight(width * 0.99 * 0.25 * 1.7);
        double hauteurDeGrille = height * 0.4;
        double hauteurDeLigne = hauteurDeGrille / 4;

        jeu.setFont(new Font(police, maxJoueur / 13));
        jeu.setAlignment(Pos.CENTER);
        jeu.setMinSize(minJoueur, 30);
        jeu.setMaxSize(maxJoueur, 70);
        jeu.setTextFill(Color.web("#fbe5b5"));
        StackPane j = new StackPane();
        j.getChildren().add(jeu);
        placement.add(j, 0, 0);

        but.setFont(new Font(police, maxJoueur / 20));
        but.setAlignment(Pos.CENTER);
        but.setMinSize(minJoueur, 30);
        but.setMaxSize(maxJoueur, 70);
        but.setTextFill(Color.web("#fbe5b5"));
        StackPane b = new StackPane();
        b.getChildren().add(but);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
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
        d.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
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
        de.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Déplacement.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        t.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/deroulementTour.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            centre.add(sp, 1, 0);
        });
        placement.add(t, 0, 4);
        regles1.getChildren().add(placement);
        centre.add(regles1, 0, 0);

        StackPane regles2 = new StackPane();
        ImageView reglesIm2 = new ImageView(regle);
        reglesIm2.setFitHeight(width * 0.99 * 0.25 * 2);
        reglesIm2.setFitWidth(width * 0.99 * 0.25);
        regles2.getChildren().add(reglesIm2);

        GridPane insectes = new GridPane();
        int ligne2 = 100 / 8;
        Outils.fixerRepartition(insectes, Outils.HORIZONTAL, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2);
        Outils.fixerRepartition(insectes, Outils.VERTICAL, colonne);
        insectes.setMaxWidth(width * 0.99 * 0.2);
        insectes.setMinWidth(width * 0.99 * 0.2);
        insectes.setMaxHeight(width * 0.99 * 0.25 * 1.7);
        insectes.setMinHeight(width * 0.99 * 0.25 * 1.7);
        double hauteurDeLigne2 = hauteurDeGrille / 6;

        insecte.setFont(new Font(police, maxJoueur / 13));
        insecte.setAlignment(Pos.CENTER);
        insecte.setMinSize(minJoueur, 30);
        insecte.setMaxSize(maxJoueur, 70);
        insecte.setTextFill(Color.web("#fbe5b5"));
        StackPane in = new StackPane();
        in.getChildren().add(insecte);
        insectes.add(in, 0, 0);

        araignee.setFont(new Font(police, maxJoueur / 20));
        araignee.setAlignment(Pos.CENTER);
        araignee.setTextFill(Color.web("#fbe5b5"));
        araignee.setMinSize(minJoueur, 30);
        araignee.setMaxSize(maxJoueur, 70);
        StackPane a = new StackPane();
        a.getChildren().add(araignee);
        a.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Araignée.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        f.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Fourmis.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Reine.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        s.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Sauterelle.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        sc.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Scarabee.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
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
        e.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
        {
            centre.getChildren().remove(centre.getChildren().size() - 1);
            StackPane sp = new StackPane();
            Image image = c.getImage("Regles/" + rep + "/Exception.png");
            ImageView Im = new ImageView(image);
            Im.setFitHeight(width - (width / 4) * 2);
            Im.setFitWidth(width - (width / 4) * 2);
            sp.getChildren().add(Im);
            centre.add(sp, 1, 0);
        });
        insectes.add(e, 0, 6);

        regles2.getChildren().add(insectes);
        centre.add(regles2, 2, 0);

        StackPane sp = new StackPane();
        Image image = c.getImage("Regles/" + rep + "/butDuJeu.png");
        ImageView Im = new ImageView(image);
        Im.setFitHeight(width - (width / 4) * 2);
        Im.setFitWidth(width - (width / 4) * 2);
        sp.getChildren().add(Im);
        centre.add(sp, 1, 0);

        sp_centre.getChildren().add(centre);

        AnchorPane.setTopAnchor(sp_centre, (double) height * 0.2);
        AnchorPane.setBottomAnchor(sp_centre, (double) height * 0.1);
        pane.getChildren().add(sp_centre);
        this.panePrincipale.getChildren().add(pane);
    }

    @Override
    public void setTextWithCurrentLanguage()
    {
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

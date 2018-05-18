/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
import java.awt.Dimension;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceRegles extends Parent {

    public InterfaceRegles(Stage primaryStage, TheHives i) {
         if(i.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
            
            
        }
        
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;
        
        Group utiles3 = new Group();
        Bouton preferences = new Bouton(primaryStage, i, "preferences", pane, "regles");
        Bouton sortie = new Bouton(primaryStage, i, "sortie", pane, "regles");
        Bouton ecran = new Bouton(primaryStage, i, "ecran", pane, "regles");
        utiles3.getChildren().addAll(preferences, sortie, ecran);
        AnchorPane.setRightAnchor(utiles3, (double) 0);
        AnchorPane.setTopAnchor(utiles3, (double) 0);
        pane.getChildren().add(utiles3);
        
        Group utiles1 = new Group();
        Bouton menu = new Bouton(primaryStage, i, "menu", pane, "charger");
        utiles1.getChildren().addAll(menu);
        
        AnchorPane.setLeftAnchor(utiles1, (double) 0);
        AnchorPane.setTopAnchor(utiles1, (double) 0);
        pane.getChildren().add(utiles1);
        
        //BorderPane choisir = new BorderPane();
        Label regles = new Label();
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
        if(i.langue=="Français"){
            regles.setText("Règles");
            but.setText("But du jeu");
            debut.setText("Début de partie");
            deplacement.setText("Déplacement");
            tour.setText("Déroulement tour");
            araignee.setText("Araignée");
            fourmi.setText("Fourmi");
            reine.setText("Reine des abeilles");
            sauterelle.setText("Sauterelle");
            scarabee.setText("Scarabee");
            exception.setText("Exception");
        }
        else if(i.langue=="English"){
            regles.setText("Rules");
            but.setText("Games purpose");
            debut.setText("Start of the game");
            deplacement.setText("Movement");
            tour.setText("Round");
            araignee.setText("Spider");
            fourmi.setText("Ant");
            reine.setText("Queen bee");
            sauterelle.setText("Grasshopper");
            scarabee.setText("Beetle");
            exception.setText("Exception");
        }
        else if(i.langue=="Italiano"){
            regles.setText("Regoli");
            but.setText("L'obiettivo");// del gioco");
            debut.setText("Inizio del gioco");
            deplacement.setText("Spostamento");
            tour.setText("Turno");
            araignee.setText("Ragno");
            fourmi.setText("Formica");
            reine.setText("Ape regina");
            sauterelle.setText("Cavalletta");
            scarabee.setText("Scarabeo");
            exception.setText("Eccezione");
        }
        else if(i.langue=="Русский"){
            regles.setText("Правила");
            but.setText("Цель игры");
            debut.setText("Начало игры");
            deplacement.setText("Перемещение");
            tour.setText("Раунд");
            araignee.setText("Паук");
            fourmi.setText("Муравей");
            reine.setText("Королева пчел");
            sauterelle.setText("Кузнечик");
            scarabee.setText("Жук");
            exception.setText("Исключение");
        }
        else if(i.langue=="Deutsch"){
            regles.setText("Regeln");
            but.setText("Ziel des Spiels");
            debut.setText("Spielbeginn");
            deplacement.setText("Bewegung");
            tour.setText("Runde");
            araignee.setText("Spinne");
            fourmi.setText("Ameise");
            reine.setText("Bienenkönigin");
            sauterelle.setText("Grashüpfer");
            scarabee.setText("Käfer");
            exception.setText("Ausnahme");
             
        }
        regles.setFont(new Font("Copperplate", width/35));
        regles.setAlignment(Pos.CENTER);
        regles.setMinSize(width/60, 30);
        regles.setMaxSize(width/2, 70);
        AnchorPane.setTopAnchor(regles, (double) height/10);
        AnchorPane.setLeftAnchor(regles, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(regles, (double) tailleDeCase*2);
        //AnchorPane.setBottomAnchor(choix, (double) height/1.1);
        pane.getChildren().add(regles);
        
        
        GridPane placement = new GridPane();
        int ligne = 100/4;
        int colonne = 100/1;
        Outils.fixerRepartition(placement, Outils.HORIZONTAL, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(placement, Outils.VERTICAL, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        placement.setMaxWidth(width/14);
        placement.setMinWidth(width/18);
        placement.setMaxHeight(height*0.4);
        placement.setMinHeight(height*0.3);
        double hauteurDeGrille = height*0.4;
        double hauteurDeLigne = hauteurDeGrille/4;
        
        
        but.setFont(new Font("Copperplate", maxJoueur/20));
        but.setAlignment(Pos.CENTER);
        but.setMinSize(minJoueur, 30);
        but.setMaxSize(maxJoueur, 70);
        StackPane b = new StackPane();
        b.getChildren().add(but);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("But de jeu ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane bdj = new StackPane();
                Image butDeJeu = new Image("hive/vue/rsc/Regles/butDuJeu.png");
                ImageView ImBDJ = new ImageView(butDeJeu);
                ImBDJ.setFitHeight(width-(width/4)*2);
                ImBDJ.setFitWidth(width-(width/4)*2);
                bdj.getChildren().add(ImBDJ);

                AnchorPane.setTopAnchor(bdj, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(bdj, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(bdj, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(bdj, (double) tailleDeCase*2);
                pane.getChildren().add(bdj);
            });
        placement.add(b, 0, 0);
        debut.setFont(new Font("Copperplate", maxJoueur/20));
        debut.setAlignment(Pos.CENTER);
        debut.setMinSize(minJoueur, 30);
        debut.setMaxSize(maxJoueur, 70);
        StackPane d = new StackPane();
        d.getChildren().add(debut);
        d.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Début de partie ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane ddp = new StackPane();
                Image debutDePartie = new Image("hive/vue/rsc/Regles/Début de partie.png");
                ImageView ImDDP = new ImageView(debutDePartie);
                ImDDP.setFitHeight(width-(width/4)*2);
                ImDDP.setFitWidth(width-(width/4)*2);
                ddp.getChildren().add(ImDDP);

                AnchorPane.setTopAnchor(ddp, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(ddp, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(ddp, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(ddp, (double) tailleDeCase*2);
                pane.getChildren().add(ddp);
            });
        placement.add(d, 0, 1);
        deplacement.setFont(new Font("Copperplate", maxJoueur/20));
        deplacement.setAlignment(Pos.CENTER);
        deplacement.setMinSize(minJoueur, 30);
        deplacement.setMaxSize(maxJoueur, 70);
        StackPane de = new StackPane();
        de.getChildren().add(deplacement);
        de.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Déplacement ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane dep = new StackPane();
                Image depl = new Image("hive/vue/rsc/Regles/Déplacement.png");
                ImageView ImD = new ImageView(depl);
                ImD.setFitHeight(width-(width/4)*2);
                ImD.setFitWidth(width-(width/4)*2);
                dep.getChildren().add(ImD);

                AnchorPane.setTopAnchor(dep, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(dep, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(dep, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(dep, (double) tailleDeCase*2);
                pane.getChildren().add(dep);
            });
        placement.add(de, 0, 2);
        tour.setFont(new Font("Copperplate", maxJoueur/20));
        tour.setAlignment(Pos.CENTER);
        tour.setMinSize(minJoueur, 30);
        tour.setMaxSize(maxJoueur, 70);
        StackPane t = new StackPane();
        t.getChildren().add(tour);
        t.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Déroulement tour ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane der = new StackPane();
                Image deroul = new Image("hive/vue/rsc/Regles/deroulementTour.png");
                ImageView ImDer = new ImageView(deroul);
                ImDer.setFitHeight(width-(width/4)*2);
                ImDer.setFitWidth(width-(width/4)*2);
                der.getChildren().add(ImDer);

                AnchorPane.setTopAnchor(der, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(der, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(der, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(der, (double) tailleDeCase*2);
                pane.getChildren().add(der);
            });
        placement.add(t, 0, 3);
        
        
        AnchorPane.setTopAnchor(placement, (double) tailleDeCase*1.5);
        AnchorPane.setLeftAnchor(placement, (double) 10);
        //AnchorPane.setRightAnchor(placement, (double) tailleDeCase*2);
        AnchorPane.setBottomAnchor(placement, (double) tailleDeCase*2);
        pane.getChildren().add(placement);
        
        
        
        GridPane insectes = new GridPane();
        int ligne2 = 100/6;
        Outils.fixerRepartition(insectes, Outils.HORIZONTAL, ligne2, ligne2, ligne2, ligne2, ligne2, ligne2);
        Outils.fixerRepartition(insectes, Outils.VERTICAL, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        insectes.setMaxWidth(width/14);
        insectes.setMinWidth(width/18);
        insectes.setMaxHeight(height*0.4);
        insectes.setMinHeight(height*0.3);
        double hauteurDeLigne2 = hauteurDeGrille/6;
        
        araignee.setFont(new Font("Copperplate", maxJoueur/20));
        araignee.setAlignment(Pos.CENTER);
        araignee.setMinSize(minJoueur, 30);
        araignee.setMaxSize(maxJoueur, 70);
        StackPane a = new StackPane();
        a.getChildren().add(araignee);
        a.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Araignée ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane ar = new StackPane();
                Image ara = new Image("hive/vue/rsc/Regles/Araignée.png");
                ImageView ImA = new ImageView(ara);
                ImA.setFitHeight(width-(width/4)*2);
                ImA.setFitWidth(width-(width/4)*2);
                ar.getChildren().add(ImA);

                AnchorPane.setTopAnchor(ar, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(ar, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(ar, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(ar, (double) tailleDeCase*2);
                pane.getChildren().add(ar);
            });
        insectes.add(a, 0, 0);
        fourmi.setFont(new Font("Copperplate", maxJoueur/20));
        fourmi.setAlignment(Pos.CENTER);
        fourmi.setMinSize(minJoueur, 30);
        fourmi.setMaxSize(maxJoueur, 70);
        StackPane f = new StackPane();
        f.getChildren().add(fourmi);
        f.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Fourmi ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane four = new StackPane();
                Image fourm = new Image("hive/vue/rsc/Regles/Fourmis.png");
                ImageView ImF = new ImageView(fourm);
                ImF.setFitHeight(width-(width/4)*2);
                ImF.setFitWidth(width-(width/4)*2);
                four.getChildren().add(ImF);

                AnchorPane.setTopAnchor(four, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(four, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(four, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(four, (double) tailleDeCase*2);
                pane.getChildren().add(four);
            });
        insectes.add(f, 0, 1);
        reine.setFont(new Font("Copperplate", maxJoueur/20));
        reine.setAlignment(Pos.CENTER);
        reine.setMinSize(minJoueur, 30);
        reine.setMaxSize(maxJoueur, 70);
        StackPane r = new StackPane();
        r.getChildren().add(reine);
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Reine ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane re = new StackPane();
                Image rein = new Image("hive/vue/rsc/Regles/Reine.png");
                ImageView ImR = new ImageView(rein);
                ImR.setFitHeight(width-(width/4)*2);
                ImR.setFitWidth(width-(width/4)*2);
                re.getChildren().add(ImR);

                AnchorPane.setTopAnchor(re, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(re, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(re, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(re, (double) tailleDeCase*2);
                pane.getChildren().add(re);
            });
        insectes.add(r, 0, 2);
        sauterelle.setFont(new Font("Copperplate", maxJoueur/20));
        sauterelle.setAlignment(Pos.CENTER);
        sauterelle.setMinSize(minJoueur, 30);
        sauterelle.setMaxSize(maxJoueur, 70);
        StackPane s = new StackPane();
        s.getChildren().add(sauterelle);
        s.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Sauterelle ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane saut = new StackPane();
                Image sauter = new Image("hive/vue/rsc/Regles/Sauterelle.png");
                ImageView ImS = new ImageView(sauter);
                ImS.setFitHeight(width-(width/4)*2);
                ImS.setFitWidth(width-(width/4)*2);
                saut.getChildren().add(ImS);

                AnchorPane.setTopAnchor(saut, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(saut, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(saut, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(saut, (double) tailleDeCase*2);
                pane.getChildren().add(saut);
            });
        insectes.add(s, 0, 3);
        scarabee.setFont(new Font("Copperplate", maxJoueur/20));
        scarabee.setAlignment(Pos.CENTER);
        scarabee.setMinSize(minJoueur, 30);
        scarabee.setMaxSize(maxJoueur, 70);
        StackPane sc = new StackPane();
        sc.getChildren().add(scarabee);
        sc.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Scarabee ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane scar = new StackPane();
                Image scarab = new Image("hive/vue/rsc/Regles/Scarabee.png");
                ImageView ImSc = new ImageView(scarab);
                ImSc.setFitHeight(width-(width/4)*2);
                ImSc.setFitWidth(width-(width/4)*2);
                scar.getChildren().add(ImSc);

                AnchorPane.setTopAnchor(scar, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(scar, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(scar, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(scar, (double) tailleDeCase*2);
                pane.getChildren().add(scar);
            });
        insectes.add(sc, 0, 4);
        exception.setFont(new Font("Copperplate", maxJoueur/20));
        exception.setAlignment(Pos.CENTER);
        exception.setMinSize(minJoueur, 30);
        exception.setMaxSize(maxJoueur, 70);
        StackPane e = new StackPane();
        e.getChildren().add(exception);
        e.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Exception ! ");
                //i.accueil();
                pane.getChildren().remove(pane.getChildren().size()-1);
                StackPane exc = new StackPane();
                Image excep = new Image("hive/vue/rsc/Regles/Exception.png");
                ImageView ImE = new ImageView(excep);
                ImE.setFitHeight(width-(width/4)*2);
                ImE.setFitWidth(width-(width/4)*2);
                exc.getChildren().add(ImE);

                AnchorPane.setTopAnchor(exc, (double) tailleDeCase);
                AnchorPane.setLeftAnchor(exc, (double) tailleDeCase*2);
                AnchorPane.setRightAnchor(exc, (double) tailleDeCase*2);
                AnchorPane.setBottomAnchor(exc, (double) tailleDeCase*2);
                pane.getChildren().add(exc);
            });
        insectes.add(e, 0, 5);
        
        AnchorPane.setTopAnchor(insectes, (double) tailleDeCase*1.5);
        AnchorPane.setRightAnchor(insectes, (double) width/6);
        //AnchorPane.setRightAnchor(insectes, (double) tailleDeCase*2);
        AnchorPane.setBottomAnchor(insectes, (double) tailleDeCase*2);
        pane.getChildren().add(insectes);
        
        
        StackPane init = new StackPane();
        Image initial = new Image("hive/vue/rsc/Regles/butDuJeu.png");
        ImageView ImInit = new ImageView(initial);
        ImInit.setFitHeight(width-(width/4)*2);
        ImInit.setFitWidth(width-(width/4)*2);
        init.getChildren().add(ImInit);
        
        AnchorPane.setTopAnchor(init, (double) tailleDeCase);
        AnchorPane.setLeftAnchor(init, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(init, (double) tailleDeCase*2);
        AnchorPane.setBottomAnchor(init, (double) tailleDeCase*2);
        pane.getChildren().add(init);
        
        
        this.getChildren().add(pane);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.application.Platform;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import hive.thehives.TheHives;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 *
 * @author Adeline
 */
public class InterfaceMenu extends Parent{

    Stage stage;
    int pleinEcran = 0;
    public InterfaceMenu(Stage primaryStage, Controller controller){


        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;

        if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        CacheImage c = new CacheImage();
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

        Image hive = c.getImage("Design/MenuPrincipaux/TheHives.png");
        ImageView hiveIm = new ImageView(hive);
        hiveIm.setFitHeight(tailleDeCase*1.3);
        hiveIm.setFitWidth(tailleDeCase*1.3*1.59);
        AnchorPane.setLeftAnchor(hiveIm, (double) 5);
        AnchorPane.setTopAnchor(hiveIm, (double) 5);
        pane.getChildren().add(hiveIm);

        StackPane hex = new StackPane();
        hex.prefHeightProperty().bind(primaryStage.heightProperty());
        hex.prefWidthProperty().bind(primaryStage.widthProperty());
        Image hexagone = c.getImage("Design/MenuPrincipaux/Hexagone.png");
        ImageView hexagoneIm = new ImageView(hexagone);
        hexagoneIm.setFitHeight(width/2);
        hexagoneIm.setFitWidth(width/2);
        hex.getChildren().add(hexagoneIm);

        Label newGame=new Label();
        Label chargerPartie = new Label();
        Label statistiques = new Label();
        Label credits = new Label();
        Label regles = new Label();

        if(controller.langue=="Français"){
            newGame.setText("Nouvelle partie");
            chargerPartie.setText("Reprendre Partie");
            statistiques.setText("Statistiques");
            credits.setText("Crèdits");
            regles.setText("Règles");
        }
        else if(controller.langue=="English"){
            newGame.setText("New Game");
            chargerPartie.setText("Load Game");
            statistiques.setText("Scores");
            credits.setText("Credits");
            regles.setText("Rules");
        }
        else if(controller.langue=="Italiano"){
            newGame.setText("Nuova Partita");
            chargerPartie.setText("Carica Partita");
            statistiques.setText("Statistica");
            credits.setText("Crediti");
            regles.setText("Regoli");
        }
        else if(controller.langue=="Русский"){
            newGame.setText("Новая Игра");
            chargerPartie.setText("Загрузить Игру");
            statistiques.setText("Статистика");
            credits.setText("Разработчики");
            regles.setText("Правила");
        }
        else if(controller.langue=="Deutsch"){
            newGame.setText("Neues Spiel");
            chargerPartie.setText("Spiel Laden");
            statistiques.setText("Statistik");
            credits.setText("Credits");
            regles.setText("Regeln");
        }

        double flecheLargeur = width/2-30;
        double flecheHauteur = flecheLargeur/7.24;
        double flecheLargeur_en_bas = width/3;
        double flecheHauteur_en_bas = flecheLargeur_en_bas/5.4;

        GridPane menu_hex = new GridPane();
        int ligne = 100/5;
        int colonne = 100/1;
        Outils.fixerRepartition(menu_hex, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(menu_hex, Outils.VERTICAL, colonne);
        menu_hex.setMaxWidth(width/2);
        menu_hex.setMinWidth(width/2);
        menu_hex.setMaxHeight(width/2);
        menu_hex.setMinHeight(width/2);
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/6;
        StackPane NewGame = new StackPane();
        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView flecheImNG = new ImageView(fleche);
        flecheImNG.setFitHeight(flecheHauteur);
        flecheImNG.setFitWidth(flecheLargeur);
        newGame.setFont(new Font(police, flecheHauteur-10));
        newGame.setTextFill(Color.web("#fbe5b5"));
        newGame.setAlignment(CENTER);
        NewGame.getChildren().add(flecheImNG);
        NewGame.getChildren().add(newGame);
        NewGame.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToChoixJoueur();
        });
        menu_hex.add(NewGame, 0, 1);
        StackPane ChargerPartie = new StackPane();
        ImageView flecheImCP = new ImageView(fleche);
        flecheImCP.setFitHeight(flecheHauteur);
        flecheImCP.setFitWidth(flecheLargeur);
        chargerPartie.setFont(new Font(police, flecheHauteur-10));
        chargerPartie.setTextFill(Color.web("#fbe5b5"));
        chargerPartie.setAlignment(CENTER);
        ChargerPartie.getChildren().add(flecheImCP);
        ChargerPartie.getChildren().add(chargerPartie);
        ChargerPartie.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            try {
                controller.goToChargerPartie();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menu_hex.add(ChargerPartie, 0, 2);
        StackPane Regles = new StackPane();
        ImageView flecheImR = new ImageView(fleche);
        flecheImR.setFitHeight(flecheHauteur);
        flecheImR.setFitWidth(flecheLargeur);
        regles.setFont(new Font(police, flecheHauteur-10));
        regles.setTextFill(Color.web("#fbe5b5"));
        regles.setAlignment(CENTER);
        Regles.getChildren().add(flecheImR);
        Regles.getChildren().add(regles);
        Regles.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToRegles();
        });
        menu_hex.add(Regles, 0, 3);
        hex.getChildren().add(menu_hex);
        pane.getChildren().add(hex);

        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences);
        prefIm.setFitHeight(tailleDeCase/2);
        prefIm.setFitWidth(tailleDeCase/2*1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            /*Preferences p = new Preferences(primaryStage, controller);
            pane.getChildren().add(p);
            StackPane pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(tailleDeCase/2.5);
            ImQ.setFitWidth(tailleDeCase/2.5);
            pref.getChildren().add(ImQ);
            pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().remove(pane.getChildren().size()-2, pane.getChildren().size());
                controller.goToMenu();
            });
            AnchorPane.setRightAnchor(pref, (double) 5);
            AnchorPane.setTopAnchor(pref, (double) 5);
            pane.getChildren().add(pref);*/

            Preferences p = new Preferences(primaryStage, controller, "menu");
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 10);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);

        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein);
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran=1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 5);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);

        StackPane Statistiques = new StackPane();
        Image gauche = c.getImage("Design/MenuPrincipaux/FlecheEnBasGauche.png");
        ImageView gaucheIm = new ImageView(gauche);
        gaucheIm.setFitHeight(flecheHauteur_en_bas);
        gaucheIm.setFitWidth(flecheLargeur_en_bas);
        statistiques.setFont(new Font(police, flecheHauteur-10));
        statistiques.setTextFill(Color.web("#fbe5b5"));
        statistiques.setAlignment(CENTER);
        Statistiques.getChildren().add(gaucheIm);
        Statistiques.getChildren().add(statistiques);
        Statistiques.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToStat();
        });
        AnchorPane.setLeftAnchor(Statistiques, (double) 5);
        AnchorPane.setBottomAnchor(Statistiques, (double) height*0.12);
        pane.getChildren().add(Statistiques);

        StackPane Credits = new StackPane();
        Image droite = c.getImage("Design/MenuPrincipaux/FlecheEnBasDroite.png");
        ImageView droiteIm = new ImageView(droite);
        droiteIm.setFitHeight(flecheHauteur_en_bas);
        droiteIm.setFitWidth(flecheLargeur_en_bas);
        credits.setFont(new Font(police, flecheHauteur_en_bas-10));
        credits.setTextFill(Color.web("#fbe5b5"));
        credits.setAlignment(CENTER);
        Credits.getChildren().add(droiteIm);
        Credits.getChildren().add(credits);
        Credits.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToCredits();
        });
        AnchorPane.setRightAnchor(Credits, (double) 5);
        AnchorPane.setBottomAnchor(Credits, (double) height*0.12);
        pane.getChildren().add(Credits);







        /*


        Image imageCase = new Image("hive/vue/rsc/images/case.png");
        Image imageBee = new Image("hive/vue/rsc/images/bee.png");

        Group NG = new Group();
        StackPane NewGame = new StackPane();
        ImageView caseImNG = new ImageView(imageCase);
        caseImNG.setFitHeight(width/4);
        caseImNG.setFitWidth(width/4);

        NewGame.getChildren().add(caseImNG);
        NewGame.getChildren().add(newGame);
        NewGame.setLayoutX(width/4-width/10);
        NewGame.setLayoutY(height/8);
        NG.getChildren().add(NewGame);
        ImageView beeImNG = new ImageView(imageBee);
        beeImNG.setFitHeight(width/10);
        beeImNG.setFitWidth(width/10);
        beeImNG.setLayoutX(width/4-width/9);
        beeImNG.setLayoutY(height/24);
        NG.getChildren().add(beeImNG);
        NG.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            NG.setEffect(shadow);
        });
        NG.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            NG.setEffect(null);
        });
        NG.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("New Game ! ");
            i.goToChoixJoueur();
        });

        //this.getChildren().add(NG);

        Group CP = new Group();
        StackPane ChargerPartie = new StackPane();
        ImageView caseImCP = new ImageView(imageCase);
        caseImCP.setFitHeight(tailleDeCase+width/24);
        caseImCP.setFitWidth(tailleDeCase+width/24);
        chargerPartie.setFont(new Font("Copperplate", tailleDeCase/8));
        chargerPartie.setAlignment(CENTER);
        ChargerPartie.getChildren().add(caseImCP);
        ChargerPartie.getChildren().add(chargerPartie);
        ChargerPartie.setLayoutX(width/2);
        ChargerPartie.setLayoutY(height/14);
        CP.getChildren().add(ChargerPartie);
        ImageView beeImCP = new ImageView(imageBee);
        beeImCP.setFitHeight(tailleDeCase/2);
        beeImCP.setFitWidth(tailleDeCase/2);
        beeImCP.setLayoutX(width/2-tailleDeCase/10);
        beeImCP.setLayoutY(height/30);
        CP.getChildren().add(beeImCP);
        CP.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            CP.setEffect(shadow);
        });
        CP.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            CP.setEffect(null);
        });
        CP.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            try {
                System.out.println("Charger partie ! ");
                i.goToChargerPartie();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //this.getChildren().add(CP);

        Group S = new Group();
        StackPane Statistiques = new StackPane();
        ImageView caseImS = new ImageView(imageCase);
        caseImS.setFitHeight(tailleDeCase);
        caseImS.setFitWidth(tailleDeCase);
        statistiques.setFont(new Font("Copperplate", tailleDeCase/8));
        statistiques.setAlignment(CENTER);
        Statistiques.getChildren().add(caseImS);
        Statistiques.getChildren().add(statistiques);
        Statistiques.setLayoutX(width/14);
        Statistiques.setLayoutY(height/1.6);
        S.getChildren().add(Statistiques);
        ImageView beeImS = new ImageView(imageBee);
        beeImS.setFitHeight(tailleDeCase/2);
        beeImS.setFitWidth(tailleDeCase/2);
        beeImS.setLayoutX(width/14-tailleDeCase/10);
        beeImS.setLayoutY(height/1.75);
        S.getChildren().add(beeImS);
        S.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            S.setEffect(shadow);
        });
        S.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            S.setEffect(null);
        });
        S.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Statistiques ! ");
            i.goToStat();
            //i.accueil();
        });

        //this.getChildren().add(S);

        Group C = new Group();
        StackPane Credits = new StackPane();
        ImageView caseImC = new ImageView(imageCase);
        caseImC.setFitHeight(tailleDeCase);
        caseImC.setFitWidth(tailleDeCase);
        credits.setFont(new Font("Copperplate", tailleDeCase/8));
        credits.setAlignment(CENTER);
        Credits.getChildren().add(caseImC);
        Credits.getChildren().add(credits);
        Credits.setLayoutX(width/2.5);
        Credits.setLayoutY(height/1.8);
        C.getChildren().add(Credits);
        ImageView beeImC = new ImageView(imageBee);
        beeImC.setFitHeight(tailleDeCase/2);
        beeImC.setFitWidth(tailleDeCase/2);
        beeImC.setLayoutX(width/2.5-tailleDeCase/10);
        beeImC.setLayoutY(height/2);
        C.getChildren().add(beeImC);
        C.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            C.setEffect(shadow);
        });
        C.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            C.setEffect(null);
        });
        C.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Crédits ! ");
            //i.accueil();
        });

        //this.getChildren().add(C);


        int tailleDeCase2 = tailleDeCase;
        Group Cases = new Group();

        for (int j = -1; j <= (height/tailleDeCase2)/2; j++) {
            ImageView casesIm = new ImageView(imageCase);
            casesIm.setFitHeight(tailleDeCase2);
            casesIm.setFitWidth(tailleDeCase2);
            casesIm.setLayoutX(width-tailleDeCase2/2);
            casesIm.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/1.33);
            Cases.getChildren().add(casesIm);
        }
        for (int j = 0; j <= (height/tailleDeCase2)/2 +1; j++) {
            if(j==0){
                StackPane Sortie = new StackPane();
                ImageView caseImR = new ImageView(imageCase);
                caseImR.setFitHeight(tailleDeCase);
                caseImR.setFitWidth(tailleDeCase);
                Sortie.getChildren().add(caseImR);
                //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit1.png"));
                //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit2.png"));
                Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit3.png"));
                ImageView sortieIm = new ImageView(imageSortie);
                sortieIm.setFitHeight(tailleDeCase2/2.5);
                sortieIm.setFitWidth(tailleDeCase2/2.5);
                Sortie.getChildren().add(sortieIm);
                Sortie.setLayoutX(width-tailleDeCase2);
                Sortie.setLayoutY(tailleDeCase2*j*1.5);
                Sortie.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                    Sortie.setEffect(shadow);
                });
                Sortie.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                    Sortie.setEffect(null);
                });
                Sortie.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    System.out.println("Sortie ! ");
                    //i.accueil();
                    Platform.exit();
                });
                Cases.getChildren().add(Sortie);
            }
            else{
                ImageView casesIm = new ImageView(imageCase);
                casesIm.setFitHeight(tailleDeCase2);
                casesIm.setFitWidth(tailleDeCase2);
                casesIm.setLayoutX(width-tailleDeCase2);
                casesIm.setLayoutY(tailleDeCase2*j*1.5);
                Cases.getChildren().add(casesIm);
            }
        }
        for (int j = -1; j <= (height/tailleDeCase2)/2; j++) {
            if(j==0){
                StackPane PleinEcran = new StackPane();
                ImageView caseImPE = new ImageView(imageCase);
                caseImPE.setFitHeight(tailleDeCase);
                caseImPE.setFitWidth(tailleDeCase);
                PleinEcran.getChildren().add(caseImPE);
                Image imagePE = new Image(getClass().getResourceAsStream("rsc/images/full.png"));
                ImageView pleinEcranIm = new ImageView(imagePE);
                pleinEcranIm.setFitHeight(tailleDeCase2/2.5);
                pleinEcranIm.setFitWidth(tailleDeCase2/2.5);
                PleinEcran.getChildren().add(pleinEcranIm);
                PleinEcran.setLayoutX(width-tailleDeCase2*1.5);
                PleinEcran.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/1.33);
                PleinEcran.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                    PleinEcran.setEffect(shadow);
                });
                PleinEcran.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                    PleinEcran.setEffect(null);
                });
                PleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    System.out.println("Plein Écran ! ");
                    pleinEcran=1;
                    primaryStage.setFullScreen(true);
                    primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
                    //i.goToMenu();
                });
                Cases.getChildren().add(PleinEcran);
            }
            else{
                ImageView casesIm = new ImageView(imageCase);
                casesIm.setFitHeight(tailleDeCase2);
                casesIm.setFitWidth(tailleDeCase2);
                casesIm.setLayoutX(width-tailleDeCase2*1.5);
                casesIm.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/1.33);
                Cases.getChildren().add(casesIm);
            }

        }
        for (int j = 0; j <= (height/tailleDeCase2)/2 +1; j++) {
            if(j==0){
                StackPane Preferences = new StackPane();
                ImageView caseImR = new ImageView(imageCase);
                caseImR.setFitHeight(tailleDeCase);
                caseImR.setFitWidth(tailleDeCase);
                Preferences.getChildren().add(caseImR);
                Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/settings1.png"));
                //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/settings2.png"));
                //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/settings3.png"));
                ImageView sortieIm = new ImageView(imageSortie);
                sortieIm.setFitHeight(tailleDeCase2/2.5);
                sortieIm.setFitWidth(tailleDeCase2/2.5);
                Preferences.getChildren().add(sortieIm);
                Preferences.setLayoutX(width-tailleDeCase2*2);
                Preferences.setLayoutY(tailleDeCase2*j*1.5);
                Preferences.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                    Preferences.setEffect(shadow);
                });
                Preferences.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                    Preferences.setEffect(null);
                });
                Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    System.out.println("Préférences ! ");
                    //i.accueil();
                    Preferences p = new Preferences(primaryStage, i);
                    this.getChildren().add(p);
                    StackPane pref = new StackPane();
                    Image imageQ = new Image(InterfaceMenu.this.getClass().getResourceAsStream("rsc/images/exit3.png"));
                    ImageView ImQ = new ImageView(imageQ);
                    ImQ.setFitHeight(tailleDeCase/2.5);
                    ImQ.setFitWidth(tailleDeCase/2.5);
                    pref.getChildren().add(ImQ);
                    pref.setLayoutX(width-tailleDeCase);
                    pref.setLayoutY(0);
                    pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                        this.getChildren().remove(this.getChildren().size()-2, this.getChildren().size());
                        i.goToMenu();
                    });

                    this.getChildren().add(pref);
                });
                Cases.getChildren().add(Preferences);
            }
            else{
                ImageView casesIm = new ImageView(imageCase);
                casesIm.setFitHeight(tailleDeCase2);
                casesIm.setFitWidth(tailleDeCase2);
                casesIm.setLayoutX(width-tailleDeCase2*2);
                casesIm.setLayoutY(tailleDeCase2*j*1.5);
                Cases.getChildren().add(casesIm);
            }
        }
        for (int j = -1; j <= (height/tailleDeCase2)/2; j++) {
            if(j==(height/tailleDeCase2)/4){
                Group R = new Group();
                StackPane Regles = new StackPane();
                ImageView caseImR = new ImageView(imageCase);
                caseImR.setFitHeight(tailleDeCase2);
                caseImR.setFitWidth(tailleDeCase2);
                regles.setFont(new Font("Copperplate", tailleDeCase/8));
                regles.setAlignment(CENTER);
                Regles.getChildren().add(caseImR);
                Regles.getChildren().add(regles);
                Regles.setLayoutX(width-tailleDeCase2*2.5);
                Regles.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/1.33);
                R.getChildren().add(Regles);
                ImageView beeImR = new ImageView(imageBee);
                beeImR.setFitHeight(tailleDeCase/2);
                beeImR.setFitWidth(tailleDeCase/2);
                beeImR.setLayoutX(width-tailleDeCase2*2.5-tailleDeCase/10);
                beeImR.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/2.1);
                R.getChildren().add(beeImR);
                R.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                    R.setEffect(shadow);
                });
                R.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                    R.setEffect(null);
                });
                R.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    System.out.println("Règles ! ");
                    i.goToRegles();
                    //i.accueil();
                });

                Cases.getChildren().add(R);
            }
            else{
                ImageView casesIm = new ImageView(imageCase);
                casesIm.setFitHeight(tailleDeCase2);
                casesIm.setFitWidth(tailleDeCase2);
                casesIm.setLayoutX(width-tailleDeCase2*2.5);
                casesIm.setLayoutY(tailleDeCase2*j+tailleDeCase2/2*j+tailleDeCase2/1.33);
                Cases.getChildren().add(casesIm);
            }
        }*/
        //this.getChildren().add(Cases);
        //group.getChildren().add(pane);

        this.getChildren().add(pane);


    }
}

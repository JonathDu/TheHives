/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.application.Platform;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

/**
 *
 * @author Adeline
 */
public class InterfaceMenu extends Parent{
    
    public InterfaceMenu(int height, int width, Stage primaryStage, TheHives i){
        
        
        Canvas n = new Canvas();
        Group circles = new Group();
        for (int j = 0; j < 30; j++) {
            Circle circle = new Circle(70 + j * 5, Color.web("yellow", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        circles.setEffect(new BoxBlur(10, 10, 3));
        this.getChildren().add(circles);
        
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;
        
        Image imageCase = new Image("hive/vue/rsc/images/case.png");
        Image imageBee = new Image("hive/vue/rsc/images/bee.png");
        
        Group NG = new Group();
        StackPane NewGame = new StackPane();
        ImageView caseImNG = new ImageView(imageCase);
        caseImNG.setFitHeight(width/5);
        caseImNG.setFitWidth(width/5);
        Label newGame = new Label("Nouvelle partie");
        newGame.setFont(new Font("Arial", width/45));
        newGame.setAlignment(CENTER);
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
        
        this.getChildren().add(NG);
        
        Group CP = new Group();
        StackPane ChargerPartie = new StackPane();
        ImageView caseImCP = new ImageView(imageCase);
        caseImCP.setFitHeight(tailleDeCase);
        caseImCP.setFitWidth(tailleDeCase);
        Label chargerPartie = new Label("Charger partie");
        chargerPartie.setFont(new Font("Arial", tailleDeCase/8));
        chargerPartie.setAlignment(CENTER);
        ChargerPartie.getChildren().add(caseImCP);
        ChargerPartie.getChildren().add(chargerPartie);
        ChargerPartie.setLayoutX(width/2);
        ChargerPartie.setLayoutY(height/12);
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
            System.out.println("Charger partie ! ");
            //i.accueil();
        });
        
        this.getChildren().add(CP);
        
        Group S = new Group();
        StackPane Statistiques = new StackPane();
        ImageView caseImS = new ImageView(imageCase);
        caseImS.setFitHeight(tailleDeCase);
        caseImS.setFitWidth(tailleDeCase);
        Label statistiques = new Label("Statistiques");
        statistiques.setFont(new Font("Arial", tailleDeCase/8));
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
            //i.accueil();
        });
        
        this.getChildren().add(S);
        
        Group C = new Group();
        StackPane Credits = new StackPane();
        ImageView caseImC = new ImageView(imageCase);
        caseImC.setFitHeight(tailleDeCase);
        caseImC.setFitWidth(tailleDeCase);
        Label credits = new Label("Crédits");
        credits.setFont(new Font("Arial", tailleDeCase/8));
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
        
        this.getChildren().add(C);
        
        /*StackPane Regles = new StackPane();
        ImageView caseImR = new ImageView(imageCase);
        caseImR.setFitHeight(tailleDeCase);
        caseImR.setFitWidth(tailleDeCase);
        Label regles = new Label("Règles");
        regles.setAlignment(CENTER);
        Regles.getChildren().add(caseImR);
        Regles.getChildren().add(regles);
        Regles.setLayoutX(width/1.4);
        Regles.setLayoutY(height/2.5);
        Regles.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            Regles.setEffect(shadow);
        });
        Regles.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            Regles.setEffect(null);
        });
        Regles.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Regles ! ");
            //i.accueil();
        });
        this.getChildren().add(Regles);*/
        
    
        
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
                //Image imageSortie = new Image(getClass().getResourceAsStream("Images/exit1.png"));
                //Image imageSortie = new Image(getClass().getResourceAsStream("Images/exit2.png"));
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
                //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings2.png"));
                //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings3.png"));
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
                Label regles = new Label("Règles");
                regles.setFont(new Font("Arial", tailleDeCase/8));
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
        }
        this.getChildren().add(Cases);
        
        
    }
}

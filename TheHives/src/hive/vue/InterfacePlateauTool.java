/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateauTool extends Parent {

    GridPane grille;
    Button boutonHome;
    Button boutonSave;
    Button boutonReplay;
    Button boutonAnnuler;
    Button boutonRejouer;
    Button boutonConseil;
    Button boutonQuitter;
    Button boutonPleinEcran;
    Button bouton1;
    Button bouton2;

    CacheImage c;

    public InterfacePlateauTool(CacheImage c, Stage stage) {
        this.c = c;
        grille = new GridPane();
        grille.prefWidthProperty().bind(stage.widthProperty());
        
        Button bouton = creerBouton("home.png");
        Button bouton1 = creerBouton("home.png");

        for (int i = 0; i < 10; i++) {
            ColumnConstraints r = new ColumnConstraints();
            r.setPercentWidth(10);
            r.setFillWidth(true);
            grille.getColumnConstraints().add(r);

        }
        grille.add(bouton, 0, 0, 1, 1);
        grille.add(bouton1, 9, 0, 1, 1);
        
        this.getChildren().add(grille);
    }

    private Button creerBouton(String path) {
        Button bouton = new Button();
        ImageView image;
        image = new ImageView(c.getImage(path));
        image.setFitWidth(40);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);
        bouton.setGraphic(image);
        bouton.setBackground(Background.EMPTY);
        return bouton;
    }


}

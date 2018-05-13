/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.players.PlayerCollection;
import hive.thehives.TheHives;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfaceJeu extends Parent {

    GridPane grille;

    Stage stage;
    String nomJoueur1;
    String nomJoueur2;

    public InterfaceJeu(PlayerCollection col, TheHives i, Stage stage, String nomJoueur1, String nomJoueur2) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;

        grille = new GridPane();
        //grille.setHgap(10);
        //grille.setVgap(10);
        CacheImage c = new CacheImage();

        
        grille.prefHeightProperty().bind(stage.heightProperty());
        grille.prefWidthProperty().bind(stage.widthProperty());
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, 7, 93);
        Outils.fixerRepartition(grille, Outils.VERTICAL, 100);
        grille.add(new InterfacePlateauTool(c,  stage), 0, 0);
        
        grille.add(new InterfacePlateau(col, c, stage, nomJoueur1, nomJoueur2), 0, 1);

        this.getChildren().add(grille);

    }

}

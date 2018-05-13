/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.players.PlayerCollection;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {
    
    AnchorPane pane;
    
    public InterfacePlateau(PlayerCollection col, CacheImage c, Stage stage, String joueur1, String joueur2) {
        pane = new AnchorPane();
        pane.prefHeightProperty().bind(stage.heightProperty());
        pane.prefWidthProperty().bind(stage.widthProperty());
        InterfacePlateauMain main1 = new InterfacePlateauMain(col, joueur1, Color.AZURE, c);
        InterfacePlateauMain main2 = new InterfacePlateauMain(col, joueur2, Color.AZURE, c);
        
        AnchorPane.setTopAnchor(main1, 0.0);
        AnchorPane.setLeftAnchor(main1, 0.0);
        
        AnchorPane.setTopAnchor(main2, 0.0);
        AnchorPane.setRightAnchor(main2, 0.0);
        
        StackPane centerPane = new StackPane();
        
        InterfaceRuche ruche = new InterfaceRuche(c);
//        ruche.placerPion(new Cell(new TilesStack(new Tile(InsectType.BEETLE, TeamColor.BLACK))), 1, 5);
//        ruche.placerPion(new Cell(new TilesStack(new Tile(InsectType.BEETLE, TeamColor.WHITE))), 2, 5);
//
//        ruche.placerPion(new Cell(new TilesStack(new Tile(InsectType.BEETLE, TeamColor.WHITE))), 9, 20);

        AnchorPane.setTopAnchor(ruche, 0.0);
        AnchorPane.setLeftAnchor(centerPane, 0.0);
        AnchorPane.setRightAnchor(centerPane, 0.0);
        StackPane.setAlignment(ruche, Pos.CENTER);
        centerPane.getChildren().add(ruche);
        
        

        pane.getChildren().add(centerPane);
        pane.getChildren().add(main1);
        pane.getChildren().add(main2);
        
        this.getChildren().add(pane);
    }
    
}

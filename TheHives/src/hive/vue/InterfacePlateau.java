/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.players.PlayerCollection;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {
//
    BorderPane pane;

    public InterfacePlateau(PlayerCollection col, CacheImage c, Stage stage, String joueur1, String joueur2, DoubleProperty prop) {
        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        InterfacePlateauMain main1 = new InterfacePlateauMain(col, joueur1, Color.AZURE, c, prop);
        InterfacePlateauMain main2 = new InterfacePlateauMain(col, joueur2, Color.BURLYWOOD, c, pane.heightProperty());

        StackPane centerPane = new StackPane();
        ScrollPane p = new ScrollPane();
        InterfaceRuche ruche = new InterfaceRuche(c, (int) stage.getWidth(), (int) stage.getHeight());
        
//        p.setOnDragDetected((event) -> {
//            double x= -event.getX();
//            System.out.println(x);
//            p.setHvalue(x/20);
// 
//        });
        
        StackPane.setAlignment(ruche, Pos.TOP_CENTER);

        ruche.isResizable();
        centerPane.getChildren().add(ruche);
        p.setContent(centerPane);
        
        pane.setCenter(p);
        pane.setLeft(main1);
        pane.setRight(main2);
        this.getChildren().add(pane);
    }


}

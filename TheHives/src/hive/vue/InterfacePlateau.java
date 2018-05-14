/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import com.sun.deploy.cache.InMemoryLocalApplicationProperties;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.insects.InsectType;
import hive.model.players.PlayerCollection;
import hive.model.players.TeamColor;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {

    AnchorPane pane;

    public InterfacePlateau(PlayerCollection col, CacheImage c, Stage stage, String joueur1, String joueur2) {
        pane = new AnchorPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        InterfacePlateauMain main1 = new InterfacePlateauMain(col, joueur1, Color.AZURE, c, pane.heightProperty());
        InterfacePlateauMain main2 = new InterfacePlateauMain(col, joueur2, Color.AZURE, c, pane.heightProperty());

        AnchorPane.setTopAnchor(main1, 0.0);
        AnchorPane.setLeftAnchor(main1, 0.0);
        AnchorPane.setTopAnchor(main2, 0.0);
        AnchorPane.setRightAnchor(main2, 0.0);

        StackPane centerPane = new StackPane();

        InterfaceRuche ruche = new InterfaceRuche(c, (int) stage.getWidth(), (int) stage.getHeight());
        


        AnchorPane.setTopAnchor(centerPane, 13.0);
        AnchorPane.setLeftAnchor(centerPane, 0.0);
        AnchorPane.setBottomAnchor(centerPane, 0.0);
        AnchorPane.setRightAnchor(centerPane, 0.0);
        StackPane.setAlignment(ruche, Pos.TOP_CENTER);

        centerPane.getChildren().add(ruche);

        pane.getChildren().add(centerPane);
        pane.getChildren().add(main1);
        pane.getChildren().add(main2);

        this.getChildren().add(pane);
    }

}

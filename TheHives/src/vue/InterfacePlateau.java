/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {

    AnchorPane pane;

    public InterfacePlateau(Stage stage) {
        pane = new AnchorPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        InterfacePlateauMain main1 = new InterfacePlateauMain("Joueur1", Color.AZURE);
        InterfacePlateauMain main2 = new InterfacePlateauMain("Autre joueur", Color.AZURE);

        AnchorPane.setTopAnchor(main1, 0.0);
        AnchorPane.setLeftAnchor(main1, 0.0);
        AnchorPane.setTopAnchor(main2, 0.0);
        AnchorPane.setRightAnchor(main2, 0.0);

        Node p = Outils.separation();

        AnchorPane.setTopAnchor(p, 0.0);
        AnchorPane.setLeftAnchor(p, 0.0);
        AnchorPane.setBottomAnchor(p, 0.0);
        AnchorPane.setRightAnchor(p, 0.0);
        
        pane.getChildren().add(p);
        pane.getChildren().add(main1);
        pane.getChildren().add(main2);

        this.getChildren().add(pane);
    }

}

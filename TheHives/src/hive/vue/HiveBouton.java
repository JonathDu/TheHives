/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
class HiveBouton extends StackPane {

    public HiveBouton(Image image, Stage stage) {
        super();
        ImageView menuIm = new ImageView(image);
        menuIm.setSmooth(true);
        menuIm.setCache(true);
        
        menuIm.fitWidthProperty().bind(stage.widthProperty().divide(15));
        menuIm.setPreserveRatio(true);
        
        
//        int tailleDeCase = (width / 8 > height / 6) ? height / 6 : width / 8;
//        menuIm.setFitHeight(tailleDeCase/2);
//        menuIm.setFitWidth(tailleDeCase/2 * 1.07);
        getChildren().add(menuIm);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author jonathan
 */
class HiveBouton extends StackPane {

    public HiveBouton(Image image, int width) {
        super();
        ImageView menuIm = new ImageView(image);
        menuIm.setSmooth(true);
        menuIm.setCache(true);
        menuIm.setFitHeight(width / 8 / 2);
        menuIm.setFitWidth(width / 8 / 2 * 1.07);
        getChildren().add(menuIm);
    }
}

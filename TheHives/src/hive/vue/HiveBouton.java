/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author jonathan
 */
class HiveBouton extends StackPane {

    private final ImageView menuIm;

    public HiveBouton(Image image, Scene scene) {
        super();
        menuIm = new ImageView(image);
        menuIm.setSmooth(true);
        menuIm.setCache(true);

        menuIm.fitWidthProperty().bind(scene.widthProperty().add(scene.heightProperty()).divide(40));
        menuIm.setPreserveRatio(true);

        getChildren().add(menuIm);
    }

    public void setSize(DoubleBinding size) {
        menuIm.fitWidthProperty().bind(size);

    }
}

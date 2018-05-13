/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import java.util.Hashtable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jonathan
 */
public class CacheImage {
    private Hashtable<String, Image> table;
    
    public CacheImage(){
        this.table = new Hashtable();
    }
    
    public ImageView getImage(String path){
        Image image;
        
        image = table.get(path);
        if(image == null){
            image = new Image(path);
            
            table.put(path, image);
        }
        return new ImageView(image);
    }
}

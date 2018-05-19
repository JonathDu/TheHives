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
    
    public Image getImage(String path){
        Image image;
        
        image = table.get("hive/vue/rsc/images/"+path);
        if(image == null){
            image = new Image("hive/vue/rsc/images/"+path);
            
            table.put("hive/vue/rsc/images/"+path, image);
        }
        return  image;
    }
}

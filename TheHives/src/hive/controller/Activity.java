/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import java.util.Observable;
import javafx.scene.Parent;

/**
 *
 * @author Thomas
 */
public abstract class Activity
{
    Parent graphics;
    Observable when_start;
    Observable when_pause;
    Observable when_stop;
    
    public Activity(Parent graphics)
    {
        this.graphics = graphics;
    }
    
    public abstract void start();
    
    public abstract void pause();
    
    public abstract void stop();
}

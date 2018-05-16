/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import static java.lang.Math.sqrt;

/**
 *
 * @author jonathan
 */
  class Hexagon {
    double [] points;
    double center;
    double hauteur;
    
    public Hexagon(double side){
      center = getH(side);
      hauteur = sqrt(Math.pow(-center, 2) - Math.pow(side, 2));
      points = new double[12];
      //     X                          Y
      points[0] = center;        points[1] = 0.0;
      points[2] = 2*center;      points[3] = hauteur;
      points[4] = 2*center;      points[5] = hauteur + side;
      points[6] = center;        points[7] = side * 2;
      points[8] = 0.0;           points[9] = side + hauteur;
      points[10] = 0.0;          points[11] = hauteur;

    }

    private double getH(double side) {
      return ((sqrt(3)/2)*side);
    }
    public double [] getPoints(){
      return points;
    }
  }

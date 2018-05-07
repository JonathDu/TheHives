/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import util.hexagons.Hexagon;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public interface InsectBehaviour
{
    public ArrayList<Hexagon> getPossibleDestinations(Hexagon source);
}

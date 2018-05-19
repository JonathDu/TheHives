/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import java.util.function.Predicate;
import util.hexagons.iterators.InfiniteNeighborsIterator;
import util.iterators.CountingIterator;

/**
 *
 * @author Thomas
 */
public class HexagonUtil
{
    // gives the number of groups (consecutive neighbors) that verifies the predicate
    public static <E> int nbGroups(Hexagon<E> hexagon, Predicate<E> predicate)
    {
        // neighbors iterator to turn twice around
        CountingIterator<Hexagon<E>> neighbors = new CountingIterator<>(new InfiniteNeighborsIterator<>(hexagon), 12);

        // turn twice around the neighbors gives the number or connex groups
        boolean visiting_group = false;
        int nb_groups = 0;
        while(neighbors.hasNext())
        {
            Hexagon<E> neighbor = neighbors.next();
            if(!predicate.test(neighbor.value()))
            {
                if(visiting_group)
                    visiting_group = false;
            }
            else
            {
                if(!visiting_group)
                {
                    visiting_group = true;
                    ++nb_groups;
                }
            }
        }
        // dividing by 2 gives the real number of connex groups
        // (but if the hexagon is surrounded by hexagons that verifies the predicate we would count 1 group, so we don't divide)
        if(nb_groups != 1)
            nb_groups /= 2;
        
        // hexagon is at most connected with 3 groups
        assert nb_groups <= 3;
        
        return nb_groups;
    }
}

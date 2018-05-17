/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class PathAtLengthIterator<E> implements Iterator<Hexagon<E>>
{
    Queue<Hexagon<E>> queue;
    // for each length we map the extremity of a path to his predecessors
    List<Map<Hexagon<E>, Set<Hexagon<E>>>> seen;
    Predicate<Neighbor<E>> predicate;
    
    public PathAtLengthIterator(Hexagon<E> center, Predicate<Neighbor<E>> predicate, int n)
    {
        this.queue = new ArrayDeque<>();
        
        this.seen = new ArrayList<>(n);
        this.predicate = predicate;
        
        queue.add(center);
        
        int to_remove = 1;
        for(int length = 0; length < n; ++length)
        {
            to_remove = calculateFromLength(length, to_remove);
        }
    }
    
    // calculate pathes from path of size length
    public int calculateFromLength(int length, int to_remove)
    {
        assert length >= 1;
        Map<Hexagon<E>, Set<Hexagon<E>>> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < to_remove; ++i)
        {
            Hexagon<E> removed = queue.remove();
            
            for(HexagonSide side : HexagonSide.values())
            {
                Set<Hexagon<E>> befores = new HashSet<>();
                Neighbor<E> neighbor = new Neighbor(removed, side, removed.getNeighbor(side));
                assert neighbor.hexagon != null;
                if(predicate.test(neighbor) && completesPath(neighbor.hexagon, neighbor.origin, length))
                {
                    ++count;
                    queue.add(neighbor.hexagon);
                    befores.add(neighbor.origin);
                }
                if(!befores.isEmpty())
                    map.put(neighbor.hexagon, befores);
            }
        }
        seen.add(map);
        return count;
    }
    
    // calculate if hexagon completes a sub-path of size length, which means the hexagon does not belong to the path
    private boolean completesPath(Hexagon<E> hexagon, Hexagon<E> path_extremity, int length)
    {
        assert length >= 0;
        // if the extremity of the sub path is equal to hexagon, it does not complete the path (subtracking)
        if(hexagon.equals(path_extremity))
            return false;
        // if the path has no predecessor
        if(length == 0)
            return true;
        // for each predecessor of the sub-path with path_extremity as extremity
        Iterator<Hexagon<E>> befores = seen.get(length - 1).get(path_extremity).iterator();
        while(befores.hasNext())
        {
            Hexagon<E> before = befores.next();
            // if it completes the sub-path then it completes the path
            if(completesPath(hexagon, before, length - 1))
                return true;
            // otherwise let's see an other path
        }
        // if the hexagon does not complete any sub-path, then it does not complete the whole path
        return false;
    }

    @Override
    public boolean hasNext()
    {
        return !queue.isEmpty();
    }

    @Override
    public Hexagon<E> next()
    {
        assert hasNext();
        Hexagon<E> h = queue.remove();
        return h;
    }
}

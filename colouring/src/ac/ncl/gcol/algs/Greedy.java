package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AbstractGraph;
import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

/**
 * An implementation of the Greedy algorithm for the Graph colouring problem.
 */
public class Greedy extends AbstractGraphColouring {
    public Greedy(boolean shuffled)
    {
        this.shuffle = shuffled;
    }

    @Override
    public HashMap<Integer, HashSet<Vertex>> colour(Graph g) {
        solution = new HashMap<>();
        var adjList = ((AdjListGraph) g).adjList;
        ArrayList<Vertex> vertices;

        if(this.shuffle)
            vertices = this.shuffleVertices(g.getVertices());
        else
            vertices = g.getVertices();


        for(Vertex v: vertices)
        {
            var neighbours = adjList.get(v);

            int col;
            for(col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                boolean available = true;
                var colSet = solution.get(col);

                for(Vertex n : neighbours)
                {
                    if (colSet.contains(n)) {
                        available = false;
                        break;
                    }
                }

                if(available)
                {
                    colSet.add(v);
                    break;
                }

            }
        }

        for(Integer colour: solution.keySet())
        {
            for(Vertex v: solution.get(colour))
            {
                v.setColour(colour);
            }
        }
        return solution;
    }

}

package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public abstract class AbstractGraphColouring implements GraphColouring{
    protected boolean shuffle = false;
    protected HashMap<Integer, HashSet<Vertex>> solution;
    @Override
    public abstract HashMap<Integer, HashSet<Vertex>> colour(Graph g);

    @Override
    public ArrayList<Vertex> shuffleVertices(ArrayList<Vertex> vertices) {
        int n = vertices.size();
        Random rn = new Random();
        while(n > 1){
            Vertex x = vertices.get(n-1);
            int ind = rn.nextInt(n-1);
            vertices.set(n-1, vertices.get(ind));
            vertices.set(ind, x);
            n--;
        }
        return vertices;
    }


    @Override
    public boolean isSafe(ArrayList<Edge> edges) {
        for(Edge e: edges)
        {
            if(e.getSrc().getColour() == e.getDest().getColour())
                return false;
        }
        return true;
    }
}

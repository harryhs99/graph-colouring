package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

public abstract class AbstractGraphColouring implements GraphColouring{
    protected boolean sorted = false;
    protected boolean shuffle = false;
    protected HashMap<Integer, HashSet<Vertex>> solution;
    protected long numChecks;
    @Override
    public abstract HashMap<Integer, HashSet<Vertex>> colour(Graph g);

    @Override
    public long getNumChecks()
    {
        return this.numChecks;
    }


    protected ArrayList<Vertex> shuffleVertices(ArrayList<Vertex> vertices) {
        int n = vertices.size();
        Random rn = new Random();
        while(n > 1){
            Vertex v = vertices.get(n-1);
            int idx = rn.nextInt(n-1);
            vertices.set(n-1, vertices.get(idx));
            vertices.set(idx, v);
            n--;
        }
        this.numChecks += vertices.size();
        return vertices;
    }


    protected ArrayList<Vertex> sortByDegree(ArrayList<Vertex> v) {
        Collections.sort(v);
        this.numChecks += (long) (v.size() * Math.log(v.size()));
        return v;
    }



    protected boolean isSafe(ArrayList<Vertex> neighbours, int col) {
        long checks = 0;
        for(Vertex n: neighbours)
        {
            if(n.getColour() == col)
            {
                this.numChecks += checks;
                return false;
            }
            checks++;
        }
        this.numChecks += checks;
        return true;
    }

    protected boolean isSafe(ArrayList<Vertex> vertices, int[] neighbours, int col)
    {
        long checks = 0;
        for(int i = 0; i < neighbours.length; i++)
        {
            if(neighbours[i] == 1)
            {
                if(vertices.get(i).getColour() == col)
                {
                    this.numChecks += checks;
                    return false;
                }
            }
            checks++;
        }
        this.numChecks += checks;
        return true;
    }

}

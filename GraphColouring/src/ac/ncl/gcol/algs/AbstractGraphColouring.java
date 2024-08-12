package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

/**
 *  An abstract class for the abstraction of Graph colouring algorithms
 */
public abstract class AbstractGraphColouring implements GraphColouring{
    protected boolean sorted = false;
    protected boolean shuffle = false;
    protected HashMap<Integer, HashSet<Vertex>> solution;
    protected long numChecks;

    /**
     * Colours a graph and returns the solution it generates
     * @param g A Graph object
     * @return The solution generated by the algorithm
     */
    @Override
    public abstract HashMap<Integer, HashSet<Vertex>> colour(Graph g);

    /**
     * Returns the number of operations an algorithm uses in colouring a graph.
     * @return int - number of operations used
     */
    @Override
    public long getNumChecks()
    {
        return this.numChecks;
    }


    /**
     * Shuffles the ordering of the vertices in a graph pseudo-randomly
     * @param vertices List containing the vertices of a graph
     * @return returns list of vertices in a shuffled order
     */
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


    /**
     * Sorts the vertices of a graph by order of degree (high to low).
     * @param v List containing vertices of a graph
     * @return List of vertices sorted by order of degree
     */
    protected ArrayList<Vertex> sortByDegree(ArrayList<Vertex> v) {
        Collections.sort(v);
        this.numChecks += (long) (v.size() * Math.log(v.size()));
        return v;
    }


    /**
     * Checks if a colour assignment is safe for an AdjListGraph to do and causes no conflicts.
     * @param neighbours List containing the neighbours of a vertex being coloured
     * @param col the colour to be assigned
     * @return returns true if safe to assign colour, false if not
     */
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

    /**
     * Checks if a colour assignment is safe for an AdjMatrixGraph to do and causes no conflicts.
     * @param vertices List containing the neighbours of a vertex being coloured
     * @param neighbours array containing all other vertices: 1 if neighbour, 0 if not.
     * @param col the colour to be assigned
     * @return returns true if safe to assign colour, false if not
     */
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

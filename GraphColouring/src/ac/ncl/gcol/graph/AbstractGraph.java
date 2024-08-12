package ac.ncl.gcol.graph;

import ac.ncl.gcol.exceptions.SolutionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An abstract class to represent a graph in computer memory.
 */
public abstract class AbstractGraph implements Graph{
    protected int order, size;
    protected ArrayList<Edge> edges;
    protected ArrayList<Vertex> vertices;
    protected int[] degrees;
    protected int maxDeg, maxNode;
    protected boolean hasSolution;
    protected HashMap<Integer, HashSet<Vertex>> colouring;

    /**
     * Gets a colouring assigned to a graph
     * @return Colouring solution assigned to graph
     * @throws SolutionNotFoundException if no solution is assigned to the graph
     */
    @Override
    public HashMap<Integer, HashSet<Vertex>> getColouring() throws SolutionNotFoundException {
        if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
        return new HashMap<>(this.colouring);
    }

    /**
     * Sets a colouring for the graph
     * @param solution the colouring to be assigned
     */
    @Override
    public void setColouring(HashMap<Integer, HashSet<Vertex>> solution)
    {
        this.colouring = solution;
        hasSolution = true;
        assignColours();
    }

    /**
     * Retrieves the order of the graph i.e. number of vertices.
     * @return int - order of the graph
     */
    @Override
    public int getOrder() {
        return this.order;
    }

    /**
     * Retrieves the size of the graph i.e. number of edges
     * @return int - size of the graph
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Calculates the density of the graph. Number of edges divided by number of possible edges.
     * @return float - density of the graph
     */
    @Override
    public float getDensity() {
        return (float) (2 * this.size)/(this.order * (this.order - 1));
    }

    /**
     * Retrieves the list of edges of the graph.
     * @return List containing edges of the graph
     */
    @Override
    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(this.edges);
    }

    /**
     * Retrieves the list of vertices of the graph.
     * @return List containing vertices of the graph
     */
    @Override
    public ArrayList<Vertex> getVertices() {
        return new ArrayList<>(this.vertices);
    }

    /**
     * Retrieves the degrees of each vertex in the graph.
     * @return int[] containing the degrees of each vertex in the graph
     */
    @Override
    public int[] getDegrees() {
        return this.degrees.clone();
    }

    /**
     * Retrieves the maximum degree contained in the graph.
     * @return int - maximum degree contained in the graph
     */
    @Override
    public int getMaxDeg() {
        return maxDeg;
    }

    /**
     * Retrieves the maximum vertex in the graph
     * @return int maximum vertex in the graph
     */
    @Override
    public int getMaxNode() {
        return maxNode;
    }

    /**
     * Prints the graph to the terminal
     */
    @Override
    public abstract void printGraph();


    private void assignColours()
    {
        for(Integer colour: colouring.keySet())
        {
            for(Vertex v: colouring.get(colour))
            {
                v.setColour(colour);
            }
        }
    }

    protected void assignDegrees()
    {
        int idx = 0;
        for(Vertex v: vertices)
        {
            v.setDegree(degrees[idx++]);
        }
    }

    /**
     * Prints the colouring to the terminal
     * @throws SolutionNotFoundException if no colouring has been assigned to the graph
     */
    @Override
    public void printSolution() throws SolutionNotFoundException {
        for(Vertex v: this.vertices)
        {
            if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
            System.out.println(v.getName() + " -> " + v.getColour());
        }
    }

    /**
     * Checks if a solution is valid and prints any clashes to the terminal.
     * @return True if valid colouring, false if not.
     * @throws SolutionNotFoundException if no colouring has been assigned to the graph
     */
    @Override
    public boolean validSolution() throws SolutionNotFoundException
    {
        if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
        boolean valid = true;
        for(Edge e: edges)
        {
            if(e.getSrc().getColour() == e.getDest().getColour())
            {
                Vertex u = e.getSrc();
                Vertex v = e.getDest();
                System.out.println("Clash between " + u + " and " + v);
                valid = false;
            }
        }
        return valid;
    };
}

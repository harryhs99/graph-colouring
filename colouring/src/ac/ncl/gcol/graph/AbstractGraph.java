package ac.ncl.gcol.graph;

import ac.ncl.gcol.exceptions.SolutionNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractGraph implements Graph{
    protected int order, size;
    protected ArrayList<Edge> edges;
    protected ArrayList<Vertex> vertices;
    protected int[] degrees;
    protected int maxDeg, maxNode;
    protected boolean hasSolution;
    protected HashMap<Integer, HashSet<Vertex>> colouring;

    @Override
    public HashMap<Integer, HashSet<Vertex>> getColouring() throws SolutionNotFoundException {
        if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
        return new HashMap<>(this.colouring);
    }

    @Override
    public void setColouring(HashMap<Integer, HashSet<Vertex>> solution)
    {
        this.colouring = solution;
        hasSolution = true;
        assignColours();
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public float getDensity() {
        return (float) (2 * this.size)/(this.order * (this.order - 1));
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(this.edges);
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return new ArrayList<>(this.vertices);
    }

    @Override
    public int[] getDegrees() {
        return this.degrees.clone();
    }

    @Override
    public int getMaxDeg() {
        return maxDeg;
    }

    @Override
    public int getMaxNode() {
        return maxNode;
    }


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


    public void clearColouring()
    {
        this.colouring = new HashMap<>();
        hasSolution = false;
    }


    @Override
    public void printSolution() throws SolutionNotFoundException {
        for(Vertex v: this.vertices)
        {
            if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
            System.out.println(v.getName() + " -> " + v.getColour());
        }
    }
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

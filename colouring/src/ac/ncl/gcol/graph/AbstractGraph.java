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
        return this.colouring;
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
        return this.edges;
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    @Override
    public int[] getDegrees() {
        return this.degrees;
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

    @Override
    public void printSolution() throws SolutionNotFoundException {
        for(Vertex v: this.vertices)
        {
            if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
            System.out.println(v.getName() + " -> " + v.getColour());
        }
    }
    @Override
    public abstract boolean validSolution() throws SolutionNotFoundException;
}

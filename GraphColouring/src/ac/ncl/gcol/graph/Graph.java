package ac.ncl.gcol.graph;

import ac.ncl.gcol.exceptions.SolutionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * A class to store the data of a graph read in from a file
 */
public interface Graph {
    public HashMap<Integer, HashSet<Vertex>> getColouring() throws SolutionNotFoundException;
    public void setColouring(HashMap<Integer, HashSet<Vertex>> solution);
    public int getOrder();
    public int getSize();
    public float getDensity();
    public ArrayList<Edge> getEdges();
    public ArrayList<Vertex> getVertices();
    public int[] getDegrees();
    public void printGraph();
    public int getMaxDeg();
    public int getMaxNode();
    public void printSolution() throws SolutionNotFoundException;
    public boolean validSolution() throws SolutionNotFoundException;
}

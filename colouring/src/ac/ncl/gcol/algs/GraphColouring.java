package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface GraphColouring {
    public HashMap<Integer, HashSet<Vertex>> colour(Graph g);
    public ArrayList<Vertex> shuffleVertices(ArrayList<Vertex> v);
    public boolean isSafe(ArrayList<Edge> e);
}

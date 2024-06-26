package ac.ncl.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class to represent a graph input in DIMACS format
 */
public class DIMACSGraph {
    private final int V;// number of vertices
    private LinkedList<Integer> adjList[];

    public DIMACSGraph(int nv)
    {
        this.V = nv;
        adjList = new LinkedList[V];
    }

    public void addVertex(int v)
    {
        adjList[v] = new LinkedList<>();
    }

    public void addEdge(int a, int b)
    {
        adjList[a].add(b);
        adjList[b].add(a);
    }
}

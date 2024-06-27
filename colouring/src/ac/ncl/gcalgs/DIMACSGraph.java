package ac.ncl.gcalgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A class to represent a graph input in DIMACS format
 */
public class DIMACSGraph {
    private int V;
    private int E;
    private HashMap<Integer, LinkedList<Integer>> adjList;

    public DIMACSGraph()
    {
        adjList = new HashMap<>();
    }

    public void setGraphOrder(int V)
    {
        this.V = V;
    }

    public void setGraphSize(int E)
    {
        this.E = E;
    }


    public int getGraphOrder()
    {
        return V == 0 ? this.adjList.size() : V;
    }

    public int getGraphSize()
    {
        return E;
    }
    public HashMap<Integer, LinkedList<Integer>> getAdjList()
    {
        return this.adjList;
    }
    public void setAllVertices(int V)
    {
        for(int i = 1; i < V + 1; i++)
            adjList.put(i, new LinkedList<>());
    }

    public void addEdge(int a, int b)
    {
        this.adjList.get(a).add(b);
    }
}

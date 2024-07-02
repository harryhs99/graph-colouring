package ac.ncl.gcalgs;

import java.util.*;

/**
 * A class to represent a graph input in DIMACS format
 */
public class DIMACSGraph {
    private int V, E;
    private Vertex[] adjList;

    public DIMACSGraph(int v, int e)
    {
        this.V = v;
        this.E = e;
        adjList = new Vertex[v + 1];

        for(int i = 1; i <= V; i++)
        {
            adjList[i] = new Vertex(i);
        }
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
        return V == 0 ? this.adjList.length : V;
    }

    public int getGraphSize()
    {
        return E;
    }

    public void addEdge(int a, int b)
    {
        Vertex v = new Vertex(b);
        adjList[a].addNeigbour(v);

    }

    public List<Vertex> getNeighbours(int v)
    {
        return adjList[v].neighbours;
    }

    public void printNeighbours()
    {
        for(int i = 1; i < adjList.length; i++)
        {
            System.out.println(i + ": " + getNeighbours(i));
        }
    }

    public void printGraph()
    {
        System.out.println("Order: " + getGraphOrder() + " , Size: " + getGraphSize());
        for(int i = 1; i < adjList.length; i++)
        {
            for(int j = 0; j < adjList[i].neighbours.size(); j++)
            {
                Vertex src = adjList[i];
                Vertex dest = adjList[i].neighbours.get(j);
                System.out.println(src.getNumber() + " -> " + dest.getNumber());
            }
        }
    }




}

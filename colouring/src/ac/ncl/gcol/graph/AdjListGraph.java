package ac.ncl.gcol.graph;

import ac.ncl.gcol.exceptions.SolutionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjListGraph extends AbstractGraph {
    public HashMap<Vertex, ArrayList<Vertex>> adjList = new HashMap<>();
    private boolean hasSolution = false;

    public AdjListGraph(int order, int size, ArrayList<Edge> edges, ArrayList<Vertex> vertices, int[] degrees, int maxDeg, int maxNode)
    {
        this.order = order;
        this.size = size;
        this.edges = edges;
        this.vertices = vertices;
        this.degrees = degrees;
        this.maxDeg = maxDeg;
        this.maxIdx = maxNode;
        this.convertToAdjList();
    }

    private void convertToAdjList()
    {
        for(Vertex v: vertices)
        {
            adjList.put(v, new ArrayList<>());
        }
        for(Edge e: edges)
        {
            if(!adjList.get(e.getSrc()).contains(e.getDest()))
                adjList.get(e.getSrc()).add(e.getDest());
            if(!adjList.get(e.getDest()).contains(e.getSrc()))
                adjList.get(e.getDest()).add(e.getSrc());
        }
    }

    public Map<Vertex, ArrayList<Vertex>> getAdjList()
    {
        return this.adjList;
    }

    @Override
    public void printGraph() {
        System.out.println(adjList);
    }

    public void printSolution() throws SolutionNotFoundException {
        for(Vertex v: this.vertices)
        {
            if(v.getColour() == -1) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
            System.out.println(v.getName() + " -> " + v.getColour());
        }
        hasSolution = true;
    }

    public boolean validSolution() throws SolutionNotFoundException {
        if(!hasSolution) throw new SolutionNotFoundException("Must first run an algorithm to find a solution");
        boolean valid = true;
        for(Vertex v : vertices)
        {
            var neighbours = adjList.get(v);
            for(Vertex n : neighbours)
            {
                if(v.getColour() == n.getColour())
                {
                    System.out.println("Clash with: " + v + " and " + n);
                    valid = false;
                }
            }

        }
        return valid;
    }

}

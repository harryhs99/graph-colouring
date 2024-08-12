package ac.ncl.gcol.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjListGraph extends AbstractGraph {
    private final HashMap<Vertex, ArrayList<Vertex>> adjList = new HashMap<>();

    public AdjListGraph(int order, int size, ArrayList<Edge> edges, ArrayList<Vertex> vertices, int[] degrees, int maxDeg, int maxNode)
    {
        this.order = order;
        this.size = size;
        this.edges = edges;
        this.vertices = vertices;
        this.degrees = degrees;
        this.maxDeg = maxDeg;
        this.maxNode = maxNode;
        this.convertToAdjList();
        assignDegrees();
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
        return new HashMap<>(this.adjList);
    }

    @Override
    public void printGraph() {
        System.out.println(adjList);
    }

}

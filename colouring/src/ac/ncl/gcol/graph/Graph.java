package ac.ncl.gcol.graph;

import java.util.LinkedList;

/**
 * A class to store the data of a graph read in from a file
 */
public class Graph {
    // number of vertices and edges
    private final int V, E;
    // array containing edges
    private final Edge[] edges;
    // array of degrees
    private final  int[] degrees;
    // highest degree as number and index of node with the highest deg
    private final int maxDeg, maxNode;

    public Graph(int V, int E, Edge[] edges, int[] degrees, int maxDeg, int maxNode )
    {
        this.V = V;
        this.E = E;
        this.edges = edges;
        this.degrees = degrees;
        this.maxDeg = maxDeg;
        this.maxNode = maxNode;
    }

    public int getV(){
        return V;
    }

    public int getE(){
        return E;
    }

    public Edge[] getEdgesArr()
    {
        return edges;
    }

    public int[] getDegreesArr() {
        return degrees;
    }

    public int getMaxDeg() {
        return maxDeg;
    }

    public int getMaxNodeIndex() {
        return maxNode;
    }

    public int getMaxNode()
    {
        return maxNode + 1;
    }

    public int[][] toAdjMatrix()
    {
        int[][] adjMatrix = new int[V][V];

        for(Edge e: this.edges)
        {
            adjMatrix[e.getU()][e.getV()] = 1;
            adjMatrix[e.getV()][e.getU()] = 1;
        }

        return adjMatrix;
    }

    public LinkedList<Integer>[] toAdjList()
    {
        LinkedList<Integer>[] adjList = new LinkedList[V];

        for(int i = 0; i < V; i ++){
            adjList[i] = new LinkedList<>();
        }

        for(Edge e: this.edges)
        {
            adjList[e.getU()].add(e.getV());
            adjList[e.getV()].add(e.getU());
        }

        return adjList;
    }


}

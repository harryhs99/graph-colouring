package ac.ncl.gcol.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class to store the data of a graph read in from a file
 */
public interface Graph {

    public int getOrder();
    public int getSize();
    public float getDensity();
    public ArrayList<Edge> getEdges();
    public ArrayList<Vertex> getVertices();
    public int[] getDegrees();
    public void printGraph();

    public int getMaxDeg();
    public int getMaxNode();




    //    // number of vertices and edges
//    private final int order, size;
//    // array containing edges
//    private final ArrayList<Edge> edges;
//    // array of degrees
//    private final  ArrayList<Vertex> degrees;
//    // highest degree as number and index of node with the highest deg
//    private final int maxDeg, maxNode;
//
//    public Graph(int V, int E, Edge[] edges, int[] degrees, int maxDeg, int maxNode )
//    {
//        this.V = V;
//        this.E = E;
//        this.edges = edges;
//        this.degrees = degrees;
//        this.maxDeg = maxDeg;
//        this.maxNode = maxNode;
//    }
//
//    public int getV(){
//        return V;
//    }
//
//    public int getE(){
//        return E;
//    }
//
//    public Edge[] getEdgesArr()
//    {
//        return edges;
//    }
//
//    public int[] getDegreesArr() {
//        return degrees;
//    }
//
//    public int getMaxDeg() {
//        return maxDeg;
//    }
//
//    public int getMaxNodeIndex() {
//        return maxNode;
//    }
//
//    public int getMaxNode()
//    {
//        return maxNode + 1;
//    }
//
//    public int[][] toAdjMatrix()
//    {
//        int[][] adjMatrix = new int[V][V];
//
//        for(Edge e: this.edges)
//        {
//            adjMatrix[e.getU()][e.getV()] = 1;
//            adjMatrix[e.getV()][e.getU()] = 1;
//        }
//
//        return adjMatrix;
//    }
//
//    public LinkedList<Vertex>[] toAdjList()
//    {
//        LinkedList<Vertex>[] adjList = new LinkedList[V];
//
//        for(int i = 0; i < V; i ++){
//            adjList[i] = new LinkedList<>();
//        }
//
//        for(Edge e: this.edges)
//        {
//            adjList[e.getU()].add(new Vertex(e.getV() + 1));
//            adjList[e.getV()].add(new Vertex(e.getU() + 1));
//        }
//
//        return adjList;
//    }


}

package ac.ncl.gcol.graph;


import java.util.ArrayList;
import java.util.Arrays;

public class AdjMatrixGraph extends AbstractGraph {
    private final int[][] adjMatrix;

    public AdjMatrixGraph(int order, int size, ArrayList<Edge> edges, ArrayList<Vertex> vertices, int[] degrees, int maxDeg, int maxNode)
    {
        this.order = order;
        this.size = size;
        this.edges = edges;
        this.vertices = vertices;
        this.degrees = degrees;
        this.maxDeg = maxDeg;
        this.maxNode = maxNode;
        this.adjMatrix = new int[order][order];
        this.convertToAdjMatrix();
        assignDegrees();
    }
    private void convertToAdjMatrix()
    {
        for(Edge e: edges)
        {
            int uIdx = e.getSrc().getName() - 1;
            int vIdx = e.getDest().getName() - 1;
            adjMatrix[uIdx][vIdx] = 1;
            adjMatrix[vIdx][uIdx] = 1;
        }
    }
    public int[][] getAdjMatrix()
    {
        return adjMatrix;
    }
    @Override
    public void printGraph() {
        for(int i = 0; i < order; i++)
        {
            System.out.println(Arrays.toString(adjMatrix[i]));
        }
    }

}

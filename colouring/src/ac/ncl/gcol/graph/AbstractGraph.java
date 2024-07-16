package ac.ncl.gcol.graph;

import java.util.ArrayList;

public abstract class AbstractGraph implements Graph{
    protected int order, size;
    protected ArrayList<Edge> edges;
    protected ArrayList<Vertex> vertices;
    protected int[] degrees;
    protected int maxDeg, maxIdx;

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public float getDensity() {
        return (float) (2 * this.size)/(this.order * (this.order - 1));
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    @Override
    public int[] getDegrees() {
        return this.degrees;
    }

    @Override
    public int getMaxDeg() {
        return maxDeg;
    }

    @Override
    public int getMaxNode() {
        return maxIdx;
    }

    @Override
    public abstract void printGraph();
}

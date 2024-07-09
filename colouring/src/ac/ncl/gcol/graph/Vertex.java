package ac.ncl.gcol.graph;

public class Vertex implements Comparable<Vertex> {
    private final int idx;
    private int deg;

    public Vertex(int idx, int deg) {
        this.idx = idx;
        this.deg = deg;
    }

    public int compareTo(Vertex v)
    {
        return this.deg - v.deg;
    }
}

package ac.ncl.gcol.graph;

public class Edge {
    private final Vertex src;
    private final Vertex dest;

    public Edge(Vertex u, Vertex v)
    {
        this.src = u;
        this.dest = v;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    @Override
    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Edge e)) return false;
        return (this.src.getName() == e.src.getName() && this.dest.getName() == e.dest.getName()) ||
                (this.src.getName() == e.dest.getName() && this.dest.getName() == e.src.getName());
    }

    @Override
    public int hashCode()
    {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + src.getName();
        hc = multiplier * hc + dest.getName();
        return hc;
    }

    @Override
    public String toString()
    {
        return "edge";
    }
}

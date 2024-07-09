package ac.ncl.gcol.graph;

public class Edge {
    private final int u;
    private final int v;

    public Edge(int u, int v)
    {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Edge e)) return false;
        return (this.u == e.u && this.v == e.v) || (this.v == e.u && this.u == e.v);
    }

    @Override
    public int hashCode()
    {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + u;
        hc = multiplier * hc + v;
        return hc;
    }

    @Override
    public String toString()
    {
        return this.u + " <--> " + this.v;
    }
}

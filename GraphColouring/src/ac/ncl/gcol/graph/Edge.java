package ac.ncl.gcol.graph;

/**
 * A class to represent an Edge that is a component of a Graph.
 */
public class Edge {
    private final Vertex src;
    private final Vertex dest;

    public Edge(Vertex u, Vertex v)
    {
        this.src = u;
        this.dest = v;
    }

    /**
     * Retrieves the source Vertex of the Edge
     * @return source Vertex
     */
    public Vertex getSrc() {
        return src;
    }

    /**
     * Retrieves the destination Vertex of the Edge
     * @return destination Vertex
     */
    public Vertex getDest() {
        return dest;
    }

    /**
     * Overrides the Object equals method to provide logical equals for an Edge.
     * Two edges are equal if both the src are equal and if both dest are equal OR
     * if src equals dest of the other and vice versa.
     * @param rhs The Edge object being compared
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Edge e)) return false;
        return (this.src.getName() == e.src.getName() && this.dest.getName() == e.dest.getName()) ||
                (this.src.getName() == e.dest.getName() && this.dest.getName() == e.src.getName());
    }

    /**
     * Overrides the hashCode to comply with equals.
     * @return int - hasCode of Object
     */
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

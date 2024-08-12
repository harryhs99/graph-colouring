package ac.ncl.gcol.graph;

/**
 * A class to represent a Vertex that is a component of a Graph.
 */
public class Vertex implements Comparable<Vertex> {
    private final int name;
    private int degree;
    private int saturation;
    private int colour = -1;

    public Vertex(int name) {
        this.name = name;
        this.degree = 0;
        this.saturation = 0;
    }

    /**
     * Retrieves the Vertex label.
     * @return int - Vertex label
     */
    public int getName()
    {
        return this.name;
    }

    /**
     * Retrieves the degree of the Vertex i.e. number of neighbours
     * @return int - Vertex degree
     */
    public int getDegree()
    {
        return this.degree;
    }

    /**
     * Retrieves the saturation of the vertex i.e. number of neighbouring colours.
     * @return int - Vertex saturation
     */
    public int getSaturation()
    {
        return this.saturation;
    }

    /**
     * Retrieves the colour assigned to the vertex. -1 means no colour has been assigned.
     * @return int - colour assigned to Vertex
     */
    public int getColour()
    {
        return this.colour;
    }

    /**
     * Sets the degree of the Vertex with the input.
     * @param d int - the saturation to be assigned to this Vertex
     */
    public void setDegree(int d)
    {
        this.degree = d;
    }

    /**
     * Decrements the degree of this Vertex
     */
    public void decDegree() {
        this.degree--;
    }

    /**
     * Increments the saturation of this Vertex
     */
    public void incSaturation()
    {
        this.saturation++;
    }

    /**
     * Sets the saturation of this Vertex.
     * @param sat int - the saturation to be set
     */
    public void setSaturation(int sat) { this.saturation = sat; }

    /**
     * Assigns a colour to this Vertex.
     * @param c int - the colour to be assigned
     */
    public void setColour(int c)
    {
        this.colour = c;
    }

    /**
     * Overrides the equals method to provide logical equals. Equal if the Vertex name is the same.
     * @param rhs the Vertex to be compared.
     * @return true if equal, false if not
     */
    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Vertex v)) return false;
        return (this.name == v.name);
    }

    /**
     * Overrides hashCode to comply with equals.
     * @return int - hashCode of this Object
     */
    @Override
    public int hashCode()
    {
        int hc = 17;
        int multiplier = 37;
        hc += multiplier * hc + this.name;
        return hc;
    }

    /**
     * Implements Comparable to provide ordering for Vertices. Vertices are first ordered by Saturation (High to Low),
     * then by Degree (High to Low), and then by name (Low to High)
     * @param v the object to be compared.
     * @return int - to provide correct ordering
     */
    @Override
    public int compareTo(Vertex v)
    {
        if(this == v) return 0;
        if (this.saturation != v.saturation) return v.saturation - this.saturation;
        if (this.degree != v.degree) return v.degree - this.degree;
        return this.name - v.name;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.name);
        // return "[vertex:" + this.name + ", Sat: " + this.saturation + ", Deg: " + this.degree + "]";
    }

}

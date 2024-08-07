package ac.ncl.gcol.graph;

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

    public int getName()
    {
        return this.name;
    }


    public int getDegree()
    {
        return this.degree;
    }

    public int getSaturation()
    {
        return this.saturation;
    }

    public int getColour()
    {
        return this.colour;
    }

    public void setDegree(int d)
    {
        this.degree = d;
    }

    public void decDegree() {
        this.degree--;
    }

    public void incSaturation()
    {
        this.saturation++;
    }

    public void setSaturation(int sat) { this.saturation = sat; }

    public void setColour(int c)
    {
        this.colour = c;
    }


    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Vertex v)) return false;
        return (this.name == v.name);
    }

    @Override
    public int hashCode()
    {
        int hc = 17;
        int multiplier = 37;
        hc += multiplier * hc + this.name;
        return hc;
    }


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

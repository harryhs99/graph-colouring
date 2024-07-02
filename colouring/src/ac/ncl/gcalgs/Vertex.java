package ac.ncl.gcalgs;

import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private final int number;
    private int colour, satur;
    List<Vertex> neighbours;

    public Vertex(int number)
    {
        this.number = number;
        this.colour = -1;
        this.satur = 0;
        this.neighbours = new LinkedList<>();
    }

    public int getNumber()
    {
        return this.number;
    }

    public int getColour()
    {
        return this.colour;
    }

    public int getSatur()
    {
        return this.satur;
    }

    public void setColour(int c)
    {
        this.colour = c;
    }

    public void addNeigbour(Vertex v)
    {
        neighbours.add(v);
        this.satur += 1;
    }

    public boolean isNeigbour(Vertex v)
    {
        return neighbours.contains(v);
    }

    @Override
    public boolean equals(Object rhs)
    {
        if(this == rhs) return true;
        if(!(rhs instanceof Vertex)) return false;
        Vertex v = (Vertex) rhs;
        return this.number == v.number;
    }
    public int compareTo(Vertex v)
    {
        return this.number - v.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}

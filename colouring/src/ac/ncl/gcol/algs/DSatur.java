package ac.ncl.gcol.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DSatur {
    // number of nodes
    private final int V;
    // Graph in form of AdjMatrix
    private final int[][] GMat;
    // Graph in form of AdjList
    private final LinkedList<Integer>[] GList;
    // List containing the adjacent colours of each vertex
    private LinkedList<Integer>[] adjCols;
    // To track node saturation
    private int[] satur, colours, degrees;
    // To track the maximum colours desired and number of colours used
    private int maxCol, coloured;
    // Final solution (if found)
    public int colouring;
    public DSatur(int[][] adjMat, LinkedList<Integer>[] adjList, int[] degrees, int V)
    {
        this.V = V;
        GMat = adjMat;
        GList = adjList;
        this.degrees = degrees;

        satur = new int[V];
        colours = new int[V];
        Arrays.fill(colours, -1);
        adjCols = new LinkedList[V];
    }

    private int getMaxSatV()
    {
        int maxSat = -1;
        int maxV = -1;
        for(int i = 0; i < V; i++)
        {
            if(colours[i] == -1)
            {
                if(satur[i] > maxSat)
                {
                    maxSat = satur[i];
                    maxV = i;
                } else if (satur[i] == maxSat) {
                    if(degrees[i] > degrees[maxV])
                        maxV = i;
                }
            }
        }
        return maxV;
    }

    private boolean assignCol(int currV)
    {
        if(coloured == V) return true;

        for(int col = 1; col < maxCol; col++)
        {
            if(!adjCols[currV].contains(col))
            {
                colours[currV] = col;
                coloured++;

                LinkedList<Integer> neighbours = GList[currV];
                ArrayList<Integer> newSat = new ArrayList<>();

                for(int n: neighbours)
                {
                    if(!adjCols[n].contains(col))
                    {
                        satur[n]++;
                        newSat.add(n);
                        adjCols[n].add(col);
                    }
                }

                if(assignCol(getMaxSatV())) return true;

                for(int s: newSat)
                {
                    adjCols[s].remove((Integer) col);
                    satur[s]--;
                }
                colours[currV] = -1;
                coloured--;
            }
        }
        return false;
    }

    public boolean colour(int maxCol)
    {
        Arrays.fill(adjCols, new LinkedList<>());
        this.maxCol = maxCol;
        coloured = 0;
        int nextV = getMaxSatV();
        boolean solved = assignCol(nextV);
        return solved;
    }
}

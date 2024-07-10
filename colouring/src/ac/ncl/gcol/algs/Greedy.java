package ac.ncl.gcol.algs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * An implementation of the Greedy algorithm for the Graph colouring problem.
 */
public class Greedy {
    final int V;
    int K;
    final int[][] GMat;
    final LinkedList<Integer>[] GList;
    int[] colouring;
    boolean[] used;

    public Greedy(int[][] adjMatrix, LinkedList<Integer>[] adjList, int V)
    {
        this.V = V;
        GMat = adjMatrix;
        GList = adjList;
        colouring = new int[V];
        Arrays.fill(colouring, -1);
        used = new boolean[V];
        Arrays.fill(used, false);
    }

    public int[] colour(boolean randStart) {
        // use random class to generate a random start point if desired else do it in order
        Random rand = new Random();
        int idx = randStart ? rand.nextInt(V) : 0;
        System.out.println("StartNode: " + idx);

        // set the starting node to first colour
        colouring[idx] = 0;

        // iterate through the remaining vertices
        for(int u = randStart ? 0 : 1; u < V; u++)
        {

            for(int v = 0; v < V; v++)
            {
                // mark all the colours that cannot be used for this node
                if(GMat[u][v] == 1){
                    if(colouring[v] != -1) {
                        used[colouring[v]] = true;
                    }
                }
            }

            // find the lowest colour than can be assigned to this node
            int col;
            for(col = 0; col<V; col++)
                if(!used[col])
                    break;

            // assign that colour to this node
            colouring[u] = col;

            for(int v = 0; v < V; v++)
            {
                // unmark all the colours marked previously ready for the next iteration
                if(GMat[u][v] == 1) {
                    if(colouring[v] != -1)
                        used[colouring[v]] = false;
                }
            }
        }
        return colouring;
    }

    public int getK()
    {
        int max = 0;
        for(int i = 0; i < colouring.length; i++)
            if(colouring[i] > max) max = colouring[i];
        this.K = max + 1;
        return this.K;
    }

    public boolean checkValid()
    {
        for(int i = 0; i < V; i++)
        {
            for(Integer j: GList[i])
            {
                if(colouring[j] == colouring[i])
                    return false;
            }
        }
        return true;
    }



}

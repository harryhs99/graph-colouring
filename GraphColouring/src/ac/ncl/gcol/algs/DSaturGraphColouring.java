package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

/**
 * A class to represent the DSATUR algorithm
 */
public class DSaturGraphColouring extends AbstractGraphColouring {

    /**
     * Colours an AdjListGraph using DSATUR and returns the solution.
     * @param g AdjListGraph
     * @return DSATUR solution
     */
    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.numChecks = 0;
        solution = new HashMap<>();
        var adjList = g.getAdjList();
        HashMap<Vertex, HashSet<Integer>> adjCol = new HashMap<>();
        Queue<Vertex> unassigned = new PriorityQueue<>();

        // put all the vertices in the queue
        int N = g.getOrder();
        numChecks += N;
        for(Vertex x: g.getVertices())
        {
            adjCol.put(x, new HashSet<>());
            unassigned.add(x);
            numChecks += (long) Math.log(N);
        }

        while(!unassigned.isEmpty()) {
            Vertex v = unassigned.poll();
            var neighbours = adjList.get(v);

            int col;
            for(col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);

                if(isSafe(neighbours, col))
                {
                    colSet.add(v);
                    v.setColour(col);
                    break;
                }
               numChecks++;
            }

            // go through all the neighbours and update the degrees and saturation
            numChecks += v.getDegree();
            for (Vertex n : neighbours)
            {
                if(n.getColour() == -1)
                {
                    unassigned.remove(n);
                    adjCol.get(n).add(col);
                    n.setSaturation(adjCol.get(n).size());
                    n.decDegree();
                    unassigned.add(n);
                    numChecks += (long) (2 * Math.log(unassigned.size()));
                }
            }

        }
        return solution;
    }

    /**
     * Colours an AdjMatrixGraph using DSATUR and returns the solution.
     * @param g AdjMatrixGraph
     * @return DSATUR solution
     */
    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g)
    {
        this.numChecks = 0;
        solution = new HashMap<>();
        var adjMatrix = g.getAdjMatrix();
        ArrayList<Vertex> vertices = g.getVertices();
        ArrayList<Vertex> remainingVertices = g.getVertices();
        Queue<Vertex> unassigned = new PriorityQueue<>(g.getOrder());

        while(!remainingVertices.isEmpty()) {
            unassigned.addAll(remainingVertices);
            int z = remainingVertices.size();
            this.numChecks += (long) (z * Math.log(z));
            Vertex v = unassigned.poll();
            this.numChecks++;
            int vIdx = v.getName() - 1;
            int[] neighbours = adjMatrix[vIdx];
            this.numChecks++;

            for(int col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);

                if(isSafe(vertices, neighbours, col))
                {
                    colSet.add(v);
                    v.setColour(col);
                    break;
                }
                this.numChecks++;
            }

            for(int i = 0; i < g.getOrder(); i++)
            {
                if(neighbours[i] == 1)
                {
                    Vertex n = vertices.get(i);
                    n.incSaturation();
                }
                this.numChecks++;
            }
            this.numChecks += unassigned.size();
            unassigned.clear();
            this.numChecks += remainingVertices.size();
            remainingVertices.remove(v);
        }
        return solution;
    }


    @Override
    public HashMap<Integer, HashSet<Vertex>> colour(Graph g) {
        HashMap<Integer, HashSet<Vertex>> solution;
        if(g instanceof AdjListGraph) solution = colourAdjList((AdjListGraph) g);
        else solution = colourAdjMatrix((AdjMatrixGraph) g);
        g.setColouring(solution);
        return solution;
    }

    @Override
    public String toString() {
        return "DSatur";
    }
}

package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

public class DSaturGraphColouring extends AbstractGraphColouring {

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.numChecks = 0;
        solution = new HashMap<>();
        var adjList = g.getAdjList();
        ArrayList<Vertex> remainingVertices = g.getVertices();
        Queue<Vertex> unassigned = new PriorityQueue<>(g.getOrder());

        while(!remainingVertices.isEmpty()) {
            unassigned.addAll(remainingVertices);
            this.numChecks += remainingVertices.size();
            Vertex v = unassigned.poll();
            this.numChecks++;
            var neighbours = adjList.get(v);
            this.numChecks++;

            for(int col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);

                if(isSafe(neighbours, col))
                {
                    colSet.add(v);
                    v.setColour(col);
                    break;
                }
                this.numChecks++;
            }

            for (Vertex n : neighbours)
            {
                n.incSaturation();
                this.numChecks++;
            }
            this.numChecks += unassigned.size();
            unassigned.clear();
            this.numChecks += remainingVertices.size();
            remainingVertices.remove(v);
        }
        return solution;
    }

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
            this.numChecks += remainingVertices.size();
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

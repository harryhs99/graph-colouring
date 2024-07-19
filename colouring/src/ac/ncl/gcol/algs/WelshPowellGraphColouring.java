package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

public class WelshPowellGraphColouring extends AbstractGraphColouring {

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.solution = new HashMap<>();
        var adjList = g.getAdjList();
        ArrayList<Vertex> vertices = g.getVertices();
        HashSet<Vertex> coloured = new HashSet<>();

        this.sortByDegree(vertices);


        int colour = 0;
        while(!(coloured.size() == g.getOrder()))
        {
            Vertex v = vertices.removeFirst();
            if(v.getColour() != -1) continue;
            this.numChecks++;

            if(!solution.containsKey(colour)) solution.put(colour, new HashSet<>());

            var colSet = solution.get(colour);

            v.setColour(colour);
            colSet.add(v);
            coloured.add(v);

            ArrayList<Vertex> neighbours = adjList.get(v);
            ArrayList<Vertex> notNeighbour = new ArrayList<>();
            long checks = 0;
            for(Vertex u : vertices)
            {
                if(!neighbours.contains(u)) notNeighbour.add(u);
                checks++;
            }

            for(Vertex u : notNeighbour)
            {
                if(u.getColour() == -1)
                {
                    if(!isSafe(adjList.get(u), colour)) continue;
                    u.setColour(colour);
                    colSet.add(u);
                    coloured.add(u);
                }
                checks++;
            }
            this.numChecks += checks;
            colour++;
            this.numChecks++;
        }

        return solution;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g) {
        this.solution = new HashMap<>();
        var adjList = g.getAdjMatrix();
        ArrayList<Vertex> vertices = g.getVertices();

        this.sortByDegree(vertices);

        for(Vertex v: vertices) {}
        return null;
    }

    @Override
    public HashMap<Integer, HashSet<Vertex>> colour(Graph g) {
        HashMap<Integer, HashSet<Vertex>> solution;
        if(g instanceof AdjListGraph) solution = colourAdjList((AdjListGraph) g);
        else solution = colourAdjMatrix((AdjMatrixGraph) g);
        g.setColouring(solution);
        return solution;
    }

}

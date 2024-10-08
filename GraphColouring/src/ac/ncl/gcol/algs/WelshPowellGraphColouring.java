package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

/**
 * An implementation of the Welsh-Powell algorithm for finding solutions to the Graph Colouring Problem.
 */
public class WelshPowellGraphColouring extends AbstractGraphColouring {

    /**
     * Colours an AdjListGraph using WELSH-POWELL.
     * @param g AdjListGraph
     * @return WELSH-POWELL solution
     */
    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.numChecks = 0;
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

            if(!solution.containsKey(colour)) solution.put(colour, new HashSet<>());

            var colSet = solution.get(colour);

            v.setColour(colour);
            colSet.add(v);
            coloured.add(v);

            ArrayList<Vertex> neighbours = adjList.get(v);
            ArrayList<Vertex> notNeighbour = new ArrayList<>();

            numChecks += vertices.size();
            for(Vertex u : vertices)
            {
                if(u.getColour() != -1) continue;
                if(!neighbours.contains(u)) {
                    notNeighbour.add(u);
                    numChecks += neighbours.size();
                }
            }

            numChecks += notNeighbour.size();
            for(Vertex u : notNeighbour)
            {
                if(u.getColour() == -1)
                {
                    if(isSafe(adjList.get(u), colour))
                    {
                        u.setColour(colour);
                        colSet.add(u);
                        coloured.add(u);
                    }
                }
            }
            colour++;
            this.numChecks++;
        }

        return solution;
    }

    /**
     * Colours an AdjMatrixGraph using WELSH-POWELL.
     * @param g AdjMatrixGraph
     * @return WELSH-POWELL solution
     */
    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g) {
        this.numChecks = 0;
        this.solution = new HashMap<>();
        var adjMatrix = g.getAdjMatrix();

        ArrayList<Vertex> orderedVertices = g.getVertices();
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

            int vIdx = v.getName() - 1;

            int[] neighbours = adjMatrix[vIdx];
            ArrayList<Vertex> notNeighbour = new ArrayList<>();

            long checks = 0;
            for(int i = 0; i < neighbours.length; i++)
            {

                if(neighbours[i] == 0 && i != vIdx)
                {
                    Vertex u = orderedVertices.get(i);
                    if(u.getColour() != -1) continue;
                    notNeighbour.add(u);
                }
                checks += 1;
            }

            this.sortByDegree(notNeighbour);

            for(Vertex u : notNeighbour)
            {
                if(u.getColour() == -1)
                {
                    if(isSafe(orderedVertices, adjMatrix[u.getName() - 1], colour))
                    {
                        u.setColour(colour);
                        colSet.add(u);
                        coloured.add(u);
                    }
                    checks++;
                }
                checks++;
            }
            this.numChecks += checks;
            colour++;
            this.numChecks++;
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
    public String toString()
    {
        return "Welsh-Powell 2";
    }

}

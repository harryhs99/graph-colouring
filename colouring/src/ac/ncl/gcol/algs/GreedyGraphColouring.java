package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.*;

import java.util.*;

/**
 * An implementation of the Greedy algorithm for the Graph colouring problem.
 */
public class GreedyGraphColouring extends AbstractGraphColouring {
    public GreedyGraphColouring(boolean shuffled)
    {
        this.shuffle = shuffled;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        solution = new HashMap<>();
        var adjList = g.adjList;
        ArrayList<Vertex> vertices;

        if(this.shuffle)
            vertices = this.shuffleVertices(g.getVertices());
        else
            vertices = g.getVertices();


        for(Vertex v: vertices)
        {
            var neighbours = adjList.get(v);

            int col;
            for(col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                boolean available = true;
                var colSet = solution.get(col);

                for(Vertex n : neighbours)
                {
                    if (colSet.contains(n)) {
                        available = false;
                        break;
                    }
                }

                if(available)
                {
                    colSet.add(v);
                    break;
                }
            }
        }
        return solution;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g)
    {
        solution = new HashMap<>();
        var adjMatrix = g.getAdjMatrix();
        ArrayList<Vertex> vertices;

        if(this.shuffle)
            vertices = this.shuffleVertices(g.getVertices());
        else
            vertices = g.getVertices();


        for(Vertex v: vertices)
        {
            int vIdx = v.getName() - 1;
            int col;
            for(col = 0; col < g.getOrder(); col++)
            {
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);
                boolean available = true;

                for(int i = 0; i < g.getOrder(); i++)
                {
                    if(adjMatrix[vIdx][i] == 1)
                    {
                        int uIdx = i + 1;
                        Vertex u = new Vertex(uIdx);
                        if(colSet.contains(u))
                        {
                            available = false;
                            break;
                        }
                    }
                }

                if(available)
                {
                    colSet.add(v);
                    break;
                }
            }
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
}

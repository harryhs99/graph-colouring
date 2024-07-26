package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.*;

import java.util.*;

/**
 * An implementation of the Greedy algorithm for the Graph colouring problem.
 */
public class GreedyGraphColouring extends AbstractGraphColouring {
    private final String greedyType;

    /**
     * A constructor for a GreedyGraphColouring object that will colour the vertices in the order they are read.
     */
    public GreedyGraphColouring() {
        this.greedyType = "In Order";
    }

    /**
     * A constructor for a GreedyGraphColouring object that will colour the vertices either as sorted by degree
     * or in a shuffled order.
     *
     * @param sortShuffle boolean: true if sorted, false if shuffled.
     */
    public GreedyGraphColouring(boolean sortShuffle)
    {
        if(sortShuffle)
        {
            this.sorted = true;
            this.greedyType = "Sorted";
        }
        else
        {
            this.shuffle = true;
            this.greedyType = "Shuffled";
        }
    }

    public String getGreedyType() {
        return this.greedyType;
    }

    private ArrayList<Vertex> selectOrdering(ArrayList<Vertex> vertices)
    {
        // If desired, shuffle the vertices in a random order
        if(this.shuffle)
            return this.shuffleVertices(vertices);
        else if (this.sorted)
            return this.sortByDegree(vertices);
        else
            return vertices;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.numChecks = 0;

        // Initialise store for the colouring solution
        solution = new HashMap<>();


        var adjList = g.getAdjList();
        ArrayList<Vertex> vertices = selectOrdering(g.getVertices());

        // Iterate through each vertex in the order specified above
        for(Vertex v: vertices)
        {
            // Retrieve the neighbours of the current vertex
            var neighbours = adjList.get(v);

            // Iterate through each colour set
            for(int col = 0; col < g.getOrder(); col++)
            {
                // If new colour set required add it to the solution
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);

                /* If the colour is available then add it to the colour set and move on to the next
                vertex. */
                if(isSafe(neighbours, col))
                {
                    colSet.add(v);
                    v.setColour(col);
                    break;
                }
                this.numChecks++;
            }
            this.numChecks++;
        }
        return solution;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g)
    {
        // Initialise store for the colouring solution
        solution = new HashMap<>();

        var adjMatrix = g.getAdjMatrix();
        ArrayList<Vertex> vertices;

        // If desired, shuffle the vertices or do it in the input order
        if(this.shuffle)
            vertices = this.shuffleVertices(g.getVertices());
        else if (this.sorted)
            vertices = this.sortByDegree(g.getVertices());
        else
            vertices = g.getVertices();

        // Iterate through each vertex in the order specified above
        for(Vertex v: vertices)
        {
            // With it being adjMatrix use index of a Vertex which is = name - 1
            int vIdx = v.getName() - 1;

            // Iterate through all the colour sets and see if the node will fit in
            for(int col = 0; col < g.getOrder(); col++)
            {
                // If we require a new colour set add it to the map
                if(!solution.containsKey(col)) solution.put(col, new HashSet<>());

                var colSet = solution.get(col);

                /* If the colour is available for the vertex add it to the set and move on to
                 the next vertex. */
                if(isSafe(g.getVertices(), adjMatrix[vIdx], col))
                {
                    colSet.add(v);
                    v.setColour(col);
                    break;
                }
              //  this.numChecks++;
            }
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

        return "Greedy " + this.greedyType;
    }
}

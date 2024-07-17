package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.*;

import java.util.*;

/**
 * An implementation of the Greedy algorithm for the Graph colouring problem.
 */
public class GreedyGraphColouring extends AbstractGraphColouring {

    /**
     * A constructor for a GreedyGraphColouring object that will colour the vertices in the order they are read.
     */
    public GreedyGraphColouring() {}

    /**
     * A constructor for a GreedyGraphColouring object that will colour the vertices either as sorted by degree
     * or in a shuffled order.
     *
     * @param sortShuffle boolean: true if sorted, false if shuffled.
     */
    public GreedyGraphColouring(boolean sortShuffle)
    {
        if(sortShuffle)
            this.sorted = true;
        else this.shuffle = true;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g) {
        this.numChecks = 0;

        // Initialise store for the colouring solution
        solution = new HashMap<>();

        var adjList = g.adjList;
        ArrayList<Vertex> vertices;

        // If desired, shuffle the vertices in a random order
        if(this.shuffle)
            vertices = this.shuffleVertices(g.getVertices());
        else if (this.sorted)
            vertices = this.sortByDegree(g.getVertices());
        else
            vertices = g.getVertices();


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

                boolean available = true;
                var colSet = solution.get(col);

                /* Iterate through the neighbours to check if colour set contains any neighbours.
                 If the colour set contains a neighbour then mark the colour is not available and
                 break the loop. */
                int check = 0;
                for(Vertex n : neighbours)
                {
                    check++;
                    if (colSet.contains(n)) {
                        available = false;
                        break;
                    }
                }
                this.numChecks += check;

                /* If the colour is available then add it to the colour set and move on to the next
                vertex. */
                if(available)
                {
                    colSet.add(v);
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
                boolean available = true;

                /* Iterate through all the neighbours of the vertex checking if it is in
                 the colour set. If the colour set contains a neighbour then set
                 colour availability to false and break out the loop. */
                int checks = 0;
                for(int i = 0; i < g.getOrder(); i++)
                {
                    checks += 2;
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
                this.numChecks += checks;

                /* If the colour is available for the vertex add it to the set and move on to
                 the next vertex. */
                if(available)
                {
                    colSet.add(v);
                    break;
                }
                this.numChecks++;
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
}

package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

public class RLFGraphColouring extends AbstractGraphColouring {

    /**
     * A method to choose the first vertex of the colour set being constructed.
     * Determined by vertex with the highest degree.
     *
     * @param X Set containing candidate uncoloured vertices
     * @param dX  int Array containing the degrees of vertices in subgraph X
     * @return Vertex - the vertex with the highest degree in subgraph X
     */
    private Vertex chooseFirst(Set<Vertex> X, int[] dX)
    {
        Vertex v = null;
        int max = -1;
        numChecks += X.size();
        for(Vertex u: X)
        {
            int uIdx = u.getName() - 1;
            if(dX[uIdx] > max)
            {
                max = dX[uIdx];
                v = u;
            }
        }
        return v;
    }

    /**
     * A method to choose the next vertices of a colour set once the first vertex has been chosen.
     * The next vertex is decided by the one with the most neighbours in Y, and ties are broken by
     * the vertex with the minimum degree.
     *
     * @param X Set of candidate uncoloured vertices
     * @param dX int Array containing degrees of vertices in subgraph X
     * @param dY int Array containing degrees of vertices in subgraph Y
     *
     * @return Vertex - vertex with most neighbours in subgraph Y (ties broken by lowest degree in subgraph X)
     */
    private Vertex chooseNext(Set<Vertex> X, int[] dX, int[] dY)
    {
        Vertex next = null;
        int max = -1, min = Integer.MAX_VALUE;
        numChecks += X.size();
        for(Vertex u: X)
        {
            int uIdx = u.getName() - 1;
            // assign to next if more neighbours Y OR if equal assign if smaller degree in X
            if((dY[uIdx] > max) || ((dY[uIdx] == max) && (dX[uIdx] < min)))
            {
                max = dY[uIdx];
                min = dX[uIdx];
                next = u;
            }
        }
        return next;
    }

    /**
     * Updates the subgraphs of X and Y and the degrees of subgraphs X and Y (monitored using dX and dY) after a vertex
     * v has been assigned to a colour set. This is for an AdjListGraph input.
     *
     * @param v Vertex that has been coloured
     * @param adjList adjacency list of the graph being coloured
     * @param X subgraph of uncoloured vertices viable for the colour set
     * @param Y subgraph of uncoloured vertices not viable for the colour set
     * @param dX degrees of vertices contained in subgraph X
     * @param dY degrees of vertices contained in subgraph Y
     * @param NN neighbours and secondary neighbours of v
     */
    private void updateSetsAndDegrees(Vertex v, Map<Vertex, ArrayList<Vertex>> adjList,
                                      Set<Vertex> X, Set<Vertex> Y, int[] dX, int[] dY, HashSet<Vertex> NN)
    {
        // remove the vertex as it is coloured
        X.remove(v);

        numChecks += v.getDegree();
        // for each uncoloured neighbour of v remove it from X and add it to Y
        for(Vertex n: adjList.get(v))
        {
            if(n.getColour() == -1)
            {
                X.remove(n);
                Y.add(n);
            }
        }



        NN.clear();
        numChecks += v.getDegree();
        // for each neighbour and secondary neighbour of v add it to NN
        for(Vertex u: adjList.get(v))
        {
            if(u.getColour() == -1)
            {
                NN.add(u);
                numChecks += u.getDegree();
                for(Vertex w: adjList.get(u))
                {
                    if(w.getColour() == -1)
                    {
                        NN.add(w);
                    }
                }
            }
        }


        // update the degrees of remaining uncoloured vertices contained in NN
        for(Vertex u: NN)
        {
            int uIdx = u.getName() - 1;
            dX[uIdx] = 0;
            dY[uIdx] = 0;
            numChecks += u.getDegree();
            for(Vertex w: adjList.get(u))
            {
                if(w.getColour() == -1)
                {
                    if(X.contains(w)) dX[uIdx]++;
                    else if(Y.contains(w)) dY[uIdx]++;
                }
            }
        }
    }

    /**
     * Implementation of the RLF algorithm as designed by
     * <a href="https://nvlpubs.nist.gov/nistpubs/jres/84/jresv84n6p489_a1b.pdf">Leighton (1979)</a> for an AdjListGraph.
     *
     * @param g AdjListGraph
     * @return a Map containing colours and their corresponding sets of vertices
     */
    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g)
    {
        numChecks = 0;
        this.solution = new HashMap<>();
        Map<Vertex, ArrayList<Vertex>> adjList = g.getAdjList();
        /*
            X - subgraph of uncoloured vertices viable for the current colour set (initially all vertices)
            Y - subgraph of uncoloured vertices not viable for the colour set (initially empty)
            NN - neighbours and secondary neighbours of vertex being coloured (initially empty)
         */
        HashSet<Vertex> X = new HashSet<>(g.getVertices()), Y = new HashSet<>(), NN = new HashSet<>();
        /*
            dX - degrees of vertices contained in subgraph X
            dY - degrees of vertices contained in subgraph Y
         */
        int[] dX = g.getDegrees(), dY = new int[g.getOrder()];


        int col = 0;
        while(!X.isEmpty())
        {
            HashSet<Vertex> S = new HashSet<>();
            // choose first vertex
            Vertex firstV = chooseFirst(X, dX);
            // add to the colour set being constructed
            S.add(firstV);
            firstV.setColour(col);

            // move each vertex into X or Y and update dX and dY
            updateSetsAndDegrees(firstV, adjList, X, Y, dX, dY, NN);

            // construct the remainder of the colour set
            while (!X.isEmpty())
            {
                /*
                choose the next best colour -> the one with the most neighbours in Y with ties being broken by
                smaller degree
                 */
                Vertex nextV = chooseNext(X, dX, dY);
                // add to the colour set
                S.add(nextV);
                nextV.setColour(col);
                // again update X, Y, dX and dY
                updateSetsAndDegrees(nextV, adjList, X, Y, dX, dY, NN);
            }

            // once the colour set is constructed add it to the solution
            solution.put(col, S);

            // swap X and Y
            X.addAll(Y);
            Y.clear();

            col++;
        }
        return solution;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g)
    {
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

    @Override
    public String toString() {
        return "RLF";
    }
}

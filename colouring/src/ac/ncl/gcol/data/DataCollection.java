package ac.ncl.gcol.data;

import ac.ncl.gcol.algs.GraphColouring;
import ac.ncl.gcol.algs.GreedyGraphColouring;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.io.DIMACSReadWriter;

import java.io.IOException;

/**
 * A class to collect the required data from running Graph Colouring Algorithms on
 * a collection of Graphs in DIMACS format both known and random.
 */
public class DataCollection {
    public static void main(String[] args) {
        DIMACSReadWriter r = new DIMACSReadWriter();
        AdjMatrixGraph g;

        try {
            g = r.readGraphToAdjMatrix("colouring/src/ac/ncl/gcol/data/testgraphs/dsjc1000.5.col");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // g.printGraph();
        System.out.println(g.getMaxNode());
        System.out.println(g.getMaxDeg());

        GraphColouring greedy = new GreedyGraphColouring(false);
        greedy.colour(g);

        try {
            System.out.println(g.getColouring());
            int k = g.getColouring().size();
            g.printSolution();
            System.out.println(g.validSolution());
            System.out.println("Graph is " + k + "-colourable");
        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }




    }
}

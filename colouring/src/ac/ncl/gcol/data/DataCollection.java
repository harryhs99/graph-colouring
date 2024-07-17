package ac.ncl.gcol.data;

import ac.ncl.gcol.algs.GraphColouring;
import ac.ncl.gcol.algs.GreedyGraphColouring;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.io.DIMACSReadWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class to collect the required data from running Graph Colouring Algorithms on
 * a collection of Graphs in DIMACS format both known and random.
 */
public class DataCollection {
    public static void main(String[] args) {
        DIMACSReadWriter r = new DIMACSReadWriter();
        AdjListGraph g;

        try {
            g = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/r1000.5.col");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // g.printGraph();
       // System.out.println(Arrays.toString(g.getDegrees()));
        // System.out.println(g.getMaxNode());
       //  System.out.println(g.getMaxDeg());

        GraphColouring greedy = new GreedyGraphColouring(false);
        long start = System.nanoTime();
        greedy.colour(g);
        long end = System.nanoTime();

        long timeInNanos = end - start;
        float timeInMillis = (float) timeInNanos / 1000000;
        float timeInSeconds = timeInMillis / 1000;

        try {
            System.out.println(g.getColouring());
            int k = g.getColouring().size();
            System.out.println("Order: " + g.getOrder());
            System.out.println("Size: " + g.getSize());
            System.out.println("Density: " + g.getDensity());
            System.out.println("Valid?: " + g.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + greedy.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
            System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }




    }
}

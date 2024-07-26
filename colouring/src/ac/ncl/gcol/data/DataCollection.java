package ac.ncl.gcol.data;

import ac.ncl.gcol.algs.*;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Vertex;
import ac.ncl.gcol.io.DIMACSReadWriter;

import java.io.IOException;
import java.util.*;

/**
 * A class to collect the required data from running Graph Colouring Algorithms on
 * a collection of Graphs in DIMACS format both known and random.
 */
public class DataCollection {
    public static void main(String[] args) {
        DIMACSReadWriter r = new DIMACSReadWriter();
        AdjListGraph g;
        AdjMatrixGraph g3;
        AdjListGraph g1;
        AdjMatrixGraph wp;
        AdjListGraph wp2;

        try {
           g = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/testing/examplegraph.col");
       //     g1 = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/instances/knownX/myciel7.col");
        //   g1 = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/r1000.5.col");
         //  wp = r.readGraphToAdjMatrix("colouring/src/ac/ncl/gcol/data/testgraphs/r1000.5.col");
         //   wp2 = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/r1000.5.col");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
      //   wp2.printGraph();
       // System.out.println(Arrays.toString(wp2.getDegrees()));
        // System.out.println(g.getMaxNode());
       //  System.out.println(g.getMaxDeg());
      //  g.printGraph();
        // System.out.println(Arrays.toString(g.getDegrees()));





         long start, end, timeInNanos;
        GraphColouring rlf = new RLFGraphColouring();


        start = System.nanoTime();
        rlf.colour(g);
        end = System.nanoTime();

        timeInNanos = end - start;
     //   timeInMillis = (float) timeInNanos / 1000000;

        try {
            System.out.println("%%%%%%% RLF : AdjList %%%%%%%%%%%");
            System.out.println(g.getColouring());
            int k = g.getColouring().size();
            System.out.println("Order: " + g.getOrder());
            System.out.println("Size: " + g.getSize());
            System.out.println("Density: " + g.getDensity());
            System.out.println("Valid?: " + g.validSolution());
            System.out.println("K: " + k);
            //System.out.println("NumberOfChecks: " + rlf.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
          //  System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
            //   System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }







    }
}

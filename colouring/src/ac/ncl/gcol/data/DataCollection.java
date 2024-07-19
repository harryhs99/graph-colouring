package ac.ncl.gcol.data;

import ac.ncl.gcol.algs.DSaturGraphColouring;
import ac.ncl.gcol.algs.GraphColouring;
import ac.ncl.gcol.algs.GreedyGraphColouring;
import ac.ncl.gcol.algs.WelshPowellGraphColouring;
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
        AdjListGraph g1;
        AdjListGraph wp;

        try {
           g = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/graph.txt");
           g1 = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/graph.txt");
           wp = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/graph.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         // wp.printGraph();
       // System.out.println(Arrays.toString(g.getDegrees()));
        // System.out.println(g.getMaxNode());
       //  System.out.println(g.getMaxDeg());
      //  g.printGraph();
        // System.out.println(Arrays.toString(g.getDegrees()));





         long start, end, timeInNanos;
        GraphColouring dSatur = new DSaturGraphColouring();
        start = System.nanoTime();
        dSatur.colour(g);
        end = System.nanoTime();

        timeInNanos = end - start;
         float timeInMillis = (float) timeInNanos / 1000000;

        try {
            System.out.println("%%%%%%% DSATUR %%%%%%%%%%%");
            System.out.println(g.getColouring());
            int k = g.getColouring().size();
            System.out.println("Order: " + g.getOrder());
            System.out.println("Size: " + g.getSize());
            System.out.println("Density: " + g.getDensity());
            System.out.println("Valid?: " + g.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + dSatur.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
         //   System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }


        GreedyGraphColouring greedy = new GreedyGraphColouring(true);

        start = System.nanoTime();
        greedy.colour(g1);
        end = System.nanoTime();

        timeInNanos = end - start;
        timeInMillis = (float) timeInNanos / 1000000;


        try {
            System.out.println("%%%%%%% GREEDY: " + greedy.getGreedyType() + " %%%%%%%%%%%");
            System.out.println(g1.getColouring());
            int k = g1.getColouring().size();
            System.out.println("Order: " + g1.getOrder());
            System.out.println("Size: " + g1.getSize());
            System.out.println("Density: " + g1.getDensity());
            System.out.println("Valid?: " + g1.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + greedy.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
             System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
          //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }


        WelshPowellGraphColouring welshPowell = new WelshPowellGraphColouring();

        start = System.nanoTime();
        welshPowell.colour(wp);
        end = System.nanoTime();

        timeInNanos = end - start;
        timeInMillis = (float) timeInNanos / 1000000;


        try {
            System.out.println("%%%%%%% WELSH-POWELL %%%%%%%%%%%");
            System.out.println(wp.getColouring());
            int k = wp.getColouring().size();
            System.out.println("Order: " + wp.getOrder());
            System.out.println("Size: " + wp.getSize());
            System.out.println("Density: " + wp.getDensity());
            System.out.println("Valid?: " + wp.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + welshPowell.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
            //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }





    }
}

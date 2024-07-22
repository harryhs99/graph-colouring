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
        AdjMatrixGraph g3;
        AdjListGraph g1;
        AdjMatrixGraph wp;
        AdjListGraph wp2;

        try {
           g = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/instances/knownX/myciel7.col");
            g1 = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/testgraphs/instances/knownX/myciel7.col");
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
//        GraphColouring dSatur = new DSaturGraphColouring();
//        start = System.nanoTime();
//        dSatur.colour(g3);
//        end = System.nanoTime();

      //  timeInNanos = end - start;
         float timeInMillis;
         //= (float) timeInNanos / 1000000;
//
//        try {
//            System.out.println("%%%%%%% DSATUR: AdjMatrix %%%%%%%%%%%");
//            System.out.println(g3.getColouring());
//            int k = g3.getColouring().size();
//            System.out.println("Order: " + g3.getOrder());
//            System.out.println("Size: " + g3.getSize());
//            System.out.println("Density: " + g3.getDensity());
//            System.out.println("Valid?: " + g3.validSolution());
//            System.out.println("K: " + k);
//            System.out.println("NumberOfChecks: " + dSatur.getNumChecks());
//            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
//            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
//         //   System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );
//
//        } catch (SolutionNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        start = System.nanoTime();
//        dSatur.colour(g);
//        end = System.nanoTime();
//
//        timeInNanos = end - start;
//        timeInMillis = (float) timeInNanos / 1000000;
//
//        try {
//            System.out.println("%%%%%%% DSATUR : AdjList %%%%%%%%%%%");
//            System.out.println(g.getColouring());
//            int k = g.getColouring().size();
//            System.out.println("Order: " + g.getOrder());
//            System.out.println("Size: " + g.getSize());
//            System.out.println("Density: " + g.getDensity());
//            System.out.println("Valid?: " + g.validSolution());
//            System.out.println("K: " + k);
//            System.out.println("NumberOfChecks: " + dSatur.getNumChecks());
//            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
//            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
//            //   System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );
//
//        } catch (SolutionNotFoundException e) {
//            throw new RuntimeException(e);
//        }


        GreedyGraphColouring greedy = new GreedyGraphColouring(false);

        start = System.nanoTime();
        var solution = greedy.colour(g);
        end = System.nanoTime();

        timeInNanos = end - start;
        timeInMillis = (float) timeInNanos / 1000000;


        try {
            System.out.println("%%%%%%% GREEDY: " + greedy.getGreedyType() + " %%%%%%%%%%%");
            System.out.println(solution);
            System.out.println(g.getColouring());
            int k = solution.size();
            System.out.println("Order: " + g.getOrder());
            System.out.println("Size: " + g.getSize());
            System.out.println("Density: " + g.getDensity());
            System.out.println("Valid?: " + g.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + greedy.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
             System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
          //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }

        GreedyGraphColouring greedy2 = new GreedyGraphColouring(false);

        start = System.nanoTime();
        var solution2 = greedy2.colour(g1);
        end = System.nanoTime();

        timeInNanos = end - start;
        timeInMillis = (float) timeInNanos / 1000000;


        try {
            System.out.println("%%%%%%% GREEDY: " + greedy.getGreedyType() + " %%%%%%%%%%%");
            System.out.println(solution2);
            System.out.println(g1.getColouring());
            int k = solution2.size();
            System.out.println("Order: " + g.getOrder());
            System.out.println("Size: " + g.getSize());
            System.out.println("Density: " + g.getDensity());
            System.out.println("Valid?: " + g.validSolution());
            System.out.println("K: " + k);
            System.out.println("NumberOfChecks: " + greedy2.getNumChecks());
            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
            //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );

        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }
//        g.clearColouring();
//        start = System.nanoTime();
//        greedy.colour(g);
//        end = System.nanoTime();
//
//        timeInNanos = end - start;
//        timeInMillis = (float) timeInNanos / 1000000;
//
//
//        try {
//            System.out.println("%%%%%%% GREEDY: " + greedy.getGreedyType() + " %%%%%%%%%%%");
//            System.out.println(g.getColouring());
//            int k = g.getColouring().size();
//            System.out.println("Order: " + g.getOrder());
//            System.out.println("Size: " + g.getSize());
//            System.out.println("Density: " + g.getDensity());
//            System.out.println("Valid?: " + g.validSolution());
//            System.out.println("K: " + k);
//            System.out.println("NumberOfChecks: " + greedy.getNumChecks());
//            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
//            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
//            //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );
//
//        } catch (SolutionNotFoundException e) {
//            throw new RuntimeException(e);
//        }


//        WelshPowellGraphColouring welshPowell = new WelshPowellGraphColouring();
//
//        start = System.nanoTime();
//        welshPowell.colour(wp);
//        end = System.nanoTime();
//
//        timeInNanos = end - start;
//        timeInMillis = (float) timeInNanos / 1000000;
//
//
//        try {
//            System.out.println("%%%%%%% WELSH-POWELL : AdjMatrix %%%%%%%%%%%");
//            System.out.println(wp.getColouring());
//            int k = wp.getColouring().size();
//            System.out.println("Order: " + wp.getOrder());
//            System.out.println("Size: " + wp.getSize());
//            System.out.println("Density: " + wp.getDensity());
//            System.out.println("Valid?: " + wp.validSolution());
//            System.out.println("K: " + k);
//            System.out.println("NumberOfChecks: " + welshPowell.getNumChecks());
//            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
//            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
//            //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );
//
//        } catch (SolutionNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        start = System.nanoTime();
//        welshPowell.colour(wp2);
//        end = System.nanoTime();
//
//        timeInNanos = end - start;
//        timeInMillis = (float) timeInNanos / 1000000;
//
//        try {
//            System.out.println("%%%%%%% WELSH-POWELL: AdjList %%%%%%%%%%%");
//            System.out.println(wp2.getColouring());
//            int k = wp2.getColouring().size();
//            System.out.println("Order: " + wp2.getOrder());
//            System.out.println("Size: " + wp2.getSize());
//            System.out.println("Density: " + wp2.getDensity());
//            System.out.println("Valid?: " + wp2.validSolution());
//            System.out.println("K: " + k);
//            System.out.println("NumberOfChecks: " + welshPowell.getNumChecks());
//            System.out.println("TimeTaken(ns): " + timeInNanos + " nanoseconds");
//            System.out.println("TimeTaken(ms): " + timeInMillis + " milliseconds");
//            //  System.out.println("TimeTaken(s): " + timeInSeconds + " seconds" );
//
//        } catch (SolutionNotFoundException e) {
//            throw new RuntimeException(e);
//        }



    }
}

package ac.ncl.gcol.main;

import ac.ncl.gcol.algs.*;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.io.DIMACSReadWriter;

import java.io.IOException;
import java.util.HashMap;

public class DataCollection {

    public static void main(String[] args) {
        DIMACSReadWriter readWriter = new DIMACSReadWriter();
        int testRuns = 20;
        GreedyGraphColouring greedy = new GreedyGraphColouring();
        GreedyGraphColouring greedyShuffle = new GreedyGraphColouring(false);
        GreedyGraphColouring greedySorted = new GreedyGraphColouring(true);
        DSaturGraphColouring dSatur = new DSaturGraphColouring();
        WelshPowellGraphColouring welshPowell = new WelshPowellGraphColouring();
        HashMap<Integer, GraphColouring> algos = new HashMap<>();
        algos.put(0, greedy);
        algos.put(1, greedyShuffle);
        algos.put(2, greedySorted);
        algos.put(3, dSatur);
        algos.put(4, welshPowell);

        for(int i = 0; i < 5; i++)
        {
            System.out.println("-------------------------- " + algos.get(i).toString() + " -----------------------------");

            for(GraphInstances graph: GraphInstances.values()) {
                if(graph.chromaticNumber != -1) {


                    long totalTime = 0;
                    int totalK = 0;
                    System.out.println("%%%%%%%  GRAPH: " + graph.name + "  %%%%%%%");

                   // String X = graph.chromaticNumber != -1 ? String.valueOf(graph.chromaticNumber) : "???";
                    var graphCopies = generateGraphCopies(graph, testRuns, readWriter);


                    // System.out.println("X(G), k, diff, operations, time(ns), time(ms), time(s)");

                    for (int j = 0; j < testRuns; j++) {
                        long start, end, timeInNanos;
                        float timeInMillis, timeInSeconds;
                        start = System.nanoTime();
                        var solution = algos.get(i).colour(graphCopies.get(j));
                        end = System.nanoTime();
                        timeInNanos = end - start;
                        totalTime += timeInNanos;
                        int k = solution.size();
                        totalK += k;
                        // getInfo(graphCopies.get(j), algos.get(i), X);
                    }
                    float avgTime = (float) totalTime / testRuns;
                    float avgK = (float) totalK / testRuns;
                    System.out.println("X(G): " + graph.chromaticNumber + ", K (avg): " + avgK + ", Average time (ns): " + avgTime);
                }
            }

        }


    }

    private static HashMap<Integer, Graph> generateGraphCopies(GraphInstances graph, int testRuns, DIMACSReadWriter readWriter)
    {
        HashMap<Integer, Graph> graphCopies = new HashMap<>();

        try {
            for(int i = 0; i < testRuns; i++) {
                Graph g = readWriter.readGraphToAdjList(graph.fileName);
                graphCopies.put(i, g);
            }
        } catch (IOException e) {
            System.out.println("File " + graph.fileName + " not found please check the input file");
            e.printStackTrace();
            System.exit(1);
        }

        if (graphCopies.isEmpty()) {
            System.out.println("Problem reading in graph");
            System.exit(1);
        }
        return graphCopies;
    }


    private static void getInfo(Graph g, GraphColouring colouring, String X)
    {
        long start, end, timeInNanos;
        float timeInMillis, timeInSeconds;
        start = System.nanoTime();
        var solution = colouring.colour(g);
        end = System.nanoTime();
        timeInNanos = end - start;
        timeInMillis = (float) timeInNanos / 1000000;
        timeInSeconds = timeInMillis / 1000;
        int k = solution.size();
        int diff = 0;
        if(!X.equals("???")) diff = k - Integer.parseInt(X);

//        if(X.equals("???"))
//            System.out.println(X + ", " +  k + ", ???, " + colouring.getNumChecks() + ", " + timeInNanos + ", " + timeInMillis + ", " + timeInSeconds);
//        else
//            System.out.println(X + ", " +  k + ", " + diff + ", " + colouring.getNumChecks() + ", " + timeInNanos + ", " + timeInMillis + ", " + timeInSeconds);

    }
}

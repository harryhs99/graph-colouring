package ac.ncl.gcol.main;

import ac.ncl.gcol.algs.*;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.io.DIMACSReadWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class DataCollection {

    public static void main(String[] args) {
        DIMACSReadWriter readWriter = new DIMACSReadWriter();
        int testRuns = 20;
        GreedyGraphColouring greedy = new GreedyGraphColouring();
        GreedyGraphColouring greedyShuffle = new GreedyGraphColouring(false);
        GreedyGraphColouring greedySorted = new GreedyGraphColouring(true);
        DSaturGraphColouring dSatur = new DSaturGraphColouring();
        WelshPowellGraphColouring welshPowell = new WelshPowellGraphColouring();
        RLFGraphColouring rlf = new RLFGraphColouring();
        HashMap<Integer, GraphColouring> algos = new HashMap<>();
        algos.put(0, greedy);
        algos.put(1, greedyShuffle);
        algos.put(2, greedySorted);
        algos.put(3, dSatur);
        algos.put(4, welshPowell);
        algos.put(5, rlf);

        for(GraphInstances graph: GraphInstances.values())
        {
            if(graph.chromaticNumber != -1)
            {
                System.out.println("%%%%%%%  GRAPH: " + graph.name + "  %%%%%%%");

                for(int i = 0; i < 6; i++) {
                    var graphCopies = generateGraphCopies(graph, testRuns, readWriter);
                    long totalTime = 0;
                    int totalK = 0;
                    long totalOperations = 0;
                    System.out.println("-------- " + algos.get(i).toString() + " ----------");
                    for (int j = 0; j < testRuns; j++) {
                        long start, end, timeInNanos;
                        start = System.nanoTime();
                        var solution = algos.get(i).colour(graphCopies.get(j));
                        end = System.nanoTime();
                        timeInNanos = end - start;
                        totalTime += timeInNanos;
                        int k = solution.size();
                        totalK += k;
                        totalOperations += algos.get(i).getNumChecks();
                        // getInfo(graphCopies.get(j), algos.get(i), X);
                    }
                    float avgOperations = (float) totalOperations / testRuns;
                    float avgTimeNanos = (float) totalTime / testRuns;
                    float avgTimeMillis = avgTimeNanos / 1000000;
                    float avgTimeSeconds = avgTimeMillis / 1000;


                    float avgK = (float) totalK / testRuns;
                    System.out.println("X(G): " + graph.chromaticNumber + ", K (avg): " + avgK + ", Avg Ops: " + avgOperations +
                            ", Avg ns: " + avgTimeNanos + ", Avg ms: " + avgTimeMillis + ", Avg s: " + avgTimeSeconds);
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

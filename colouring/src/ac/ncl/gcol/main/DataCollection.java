package ac.ncl.gcol.main;

import ac.ncl.gcol.algs.*;
import ac.ncl.gcol.data.RandomGraphGenerator;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.io.DIMACSReader;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DataCollection {

    public static void main(String[] args) {

        DIMACSReader graphReader = new DIMACSReader();

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
        algos.put(3, welshPowell);
        algos.put(4, dSatur);
        algos.put(5, rlf);


        Scanner sc = new Scanner(System.in);
        boolean exit = false, showMenu = true;


        while(!exit)
        {
           if(showMenu) mainMenu();

           String input = sc.nextLine();
           boolean back = false;
           String[] validInput = new String[]{"1", "2", "3", "4", "q"};


           switch (input) {
               default:
                   if(!Arrays.asList(validInput).contains(input))
                       System.out.println("choose from the above options...");
                   showMenu = false;
                   break;
               case "1":
                   singleGraphColouring(sc, algos, graphReader);
                   break;
               case "2":
                   generateResults(sc, algos, graphReader);
                   break;
               case "3":
                   generateRandomGraph(sc);
                   break;
               case "4":
                   break;
               case "q":
                   exit = true;
                   break;
           }
        }
    }


    private static void mainMenu()
    {
        System.out.println("------------------------------------------------");
        System.out.println("                      MENU                      ");
        System.out.println("------------------------------------------------");
        System.out.println("[1] Test algorithms on 1 graph");
        System.out.println("[2] Generate results");
        System.out.println("[3] Generate random graph");
        System.out.println("[4] More info");
        System.out.println("[q] to exit");
        System.out.println("------------------------------------------------");
    }

    private static void singleGraphMenu()
    {
        System.out.println("------------------------------------------------");
        System.out.println("              Algorithm Selection               ");
        System.out.println("------------------------------------------------");
        System.out.println("[0] Greedy");
        System.out.println("[1] Shuffled Greedy");
        System.out.println("[2] Sorted Greedy");
        System.out.println("[3] Welsh-Powell");
        System.out.println("[4] DSatur");
        System.out.println("[5] RLF");
        System.out.println("[6] All of the above");
        System.out.println("[b] ack to main menu");
        System.out.println("------------------------------------------------");
    }

    private static void resultsMenu()
    {
        System.out.println("------------------------------------------------");
        System.out.println("         Results: Graph Type Selection          ");
        System.out.println("------------------------------------------------");
        System.out.println("[1] Randomly generated graphs");
        System.out.println("[2] DIMACS test instances");
        System.out.println("[3] Random DIMACS test instances");
        System.out.println("[b] ack to main menu");
        System.out.println("------------------------------------------------");
    }

    private static void resultsSubMenu()
    {
        System.out.println("------------------------------------------------");
        System.out.println("            Known Chromatic Number?             ");
        System.out.println("------------------------------------------------");
        System.out.println("[1] Known X(G)");
        System.out.println("[2] Unknown X(G)");
        System.out.println("[b] ack to main menu");
        System.out.println("------------------------------------------------");
    }


    private static void singleGraphColouring(Scanner sc, HashMap<Integer, GraphColouring> algos, DIMACSReader graphReader)
    {
        boolean done = false;

        while (!done)
        {
            System.out.print("Graph file path: ");
            String file = sc.nextLine();
            singleGraphMenu();
            System.out.print("Algorithm Choice: ");
            String[] validInput = new String[]{"0", "1", "2", "3", "4", "5", "6", "b"};
            String input = sc.nextLine();

            if(!Arrays.asList(validInput).contains(input))
            {
                System.out.println("invalid input, please try again...");
            }
            else if(input.equals("b"))
            {
                done = true;
            }
            else if(input.equals("6"))
            {
                int success = 0;
                for(GraphColouring alg : algos.values())
                {
                    Graph gCopy = null;
                    try {
                        gCopy = graphReader.readGraphToAdjList(file);
                    } catch (IOException e) {
                        System.out.println("Problem reading graph, please start again...");
                        break;
                    }
                    displayResults(alg, gCopy);
                    success++;
                }
                if(success == 6) done = true;
            }
            else
            {
                Graph g = null;
                GraphColouring alg = algos.get(Integer.parseInt(input));
                try {
                    g = graphReader.readGraphToAdjList(file);
                } catch (IOException e) {
                    System.out.println("Problem reading graph, please start again...");
                    continue;
                }
                displayResults(alg, g);
                done = true;
            }
        }
    }

    private static void displayResults(GraphColouring alg, Graph g)
    {
        long start = 0, end = 0;
        start = System.nanoTime();
        var solution = alg.colour(g);
        end = System.nanoTime();
        long timeInNanos = end - start;
        try{
            int k = solution.size();
            System.out.println("%%%%%% " + alg + " %%%%%%");
            System.out.print("Valid?: " + g.validSolution() + ", ");
            System.out.print("K: " + k + ", ");
            System.out.print("NumberOfChecks: " + alg.getNumChecks() + ", ");
            System.out.println("TimeTaken(ns): " + timeInNanos);
        } catch (SolutionNotFoundException e) {
            System.out.println("Solution not found");
        }
    }

    private static void generateResults(Scanner sc, HashMap<Integer, GraphColouring> algos,
                                        DIMACSReader graphReader)
    {
        String filePath = "colouring/src/ac/ncl/gcol/results/";
        boolean done = false;


        while(!done)
        {
            resultsMenu();
            String input = sc.nextLine();
            String[] validInput = new String[] {"1", "2", "3", "b"};
            switch (input)
            {
                case "1":
                    int testRuns = sc.nextInt();
                    break;
                case "2":
                    generateDIMACSResults("KnownXDIMACSGraphResults.csv",
                            "UnknownXDIMACSGraphResults.csv",
                            false, sc, algos, graphReader, filePath, done);
                    done = true;
                    break;
                case "3":
                    generateDIMACSResults("KnownXDIMACSRandomGraphResults.csv",
                            "UnknownXDIMACSRandomGraphResults.csv",
                            true, sc, algos, graphReader, filePath, done);
                    done = true;
                    break;
                case "b":
                    done = true;
                    break;
                default:
                    if(!Arrays.asList(validInput).contains(input)) System.out.println("invalid input, please try again...");
                    break;
            }
        }

    }

    private static void generateDIMACSResults(String fileName1, String fileName2, boolean random,
                                              Scanner sc, HashMap<Integer, GraphColouring> algos,
                                                      DIMACSReader graphReader, String filePath, boolean done)
    {
        resultsSubMenu();

        int testRuns;

        while(!done)
        {
            String input = sc.nextLine();
            if(input.equals("b"))
            {
                done = true;
            }
            else if(input.equals("1") || input.equals("2"))
            {
                String fileName = input.equals("1") ? fileName1 : fileName2;
                try
                {
                    PrintWriter pw = new PrintWriter(new File(filePath + fileName));

                    System.out.print("Number of Test Runs? ");
                    testRuns = sc.nextInt();

                    System.out.println("Running.....");
                    printColumnNames(pw);

                    if(!random) generateGraphInstanceResults(input, testRuns, pw, algos, graphReader);
                    else generateRandomGraphInstanceResults(input, testRuns, pw, algos, graphReader);

                    pw.close();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

                System.out.println("Results saved to: " + filePath + fileName);
                done = true;
            }
            else
            {
                System.out.println("invalid input, please try again...");
            }
        }

    }

    private static void printColumnNames(PrintWriter pw)
    {
        pw.println("graphName, X(G), " +
                "greedyK, greedyOps, greedyTime, " +
                "shuffGreedyK, shuffGreedyOps, shuffGreedyTime, " +
                "sortGreedyK, sortGreedyOps, sortGreedyTime, " +
                "welshPowellK, welshPowellOps, welshPowellTime, " +
                "dSaturK, dSaturOps, dSaturTime, " +
                "rlfK, rlfOps, rlfTime");
    }

    private static void generateGraphInstanceResults(String input, int testRuns, PrintWriter pw,
                                                     HashMap<Integer, GraphColouring> algos,
                                                     DIMACSReader graphReader) throws IOException
    {
        for(GraphInstances graph: GraphInstances.values())
        {
            String graphName = graph.name;
            String graphFile = graph.fileName;
            int X = graph.chromaticNumber;
            if(input.equals("1"))
            {
                if(graph.chromaticNumber != -1)
                    printGraphResults(graphName, graphFile, X, testRuns, pw, algos, graphReader);
            }
            else
            {
                if(graph.chromaticNumber == -1)
                    printGraphResults(graphName, graphFile, X, testRuns, pw, algos, graphReader);
            }
        }
    }

    private static void generateRandomGraphInstanceResults(String input, int testRuns, PrintWriter pw,
                                                     HashMap<Integer, GraphColouring> algos,
                                                     DIMACSReader graphReader) throws IOException
    {
        for(RandomGraphInstances graph: RandomGraphInstances.values())
        {
            String graphName = graph.name;
            String graphFile = graph.fileName;
            int X = graph.chromaticNumber;
            if(input.equals("1"))
            {
                if(graph.chromaticNumber != -1)
                    printGraphResults(graphName, graphFile, X, testRuns, pw, algos, graphReader);
            }
            else
            {
                if(graph.chromaticNumber == -1)
                    printGraphResults(graphName, graphFile, X, testRuns, pw, algos, graphReader);
            }
        }
    }


    private static void printGraphResults(String graphName, String graphFile, int X,
                                            int testRuns, PrintWriter pw, HashMap<Integer,
                                            GraphColouring> algos, DIMACSReader graphReader) throws IOException
    {
        long start = 0, end = 0;
        for (int j = 0; j < testRuns; j++) {
            pw.print(graphName + ", " + X + ", ");
            for (int i = 0; i < 6; i++)
            {
                Graph g = graphReader.readGraphToAdjList(graphFile);
                GraphColouring algo = algos.get(i);

                start = System.nanoTime();
                var solution = algo.colour(g);
                end = System.nanoTime();

                long time = end - start;
                int k = solution.size();

                if(i == 5) pw.print(k + ", " + algo.getNumChecks() + ", " + time);
                else pw.print(k + ", " + algo.getNumChecks() + ", " + time + ", ");
            }
            pw.println();
        }

    }


    private static HashMap<Integer, Graph> generateGraphInstanceCopies(GraphInstances graph, int testRuns,
                                                                       DIMACSReader readWriter)
    {
        String fileName = graph.fileName;
        return generateCopies(fileName, testRuns, readWriter);
    }

    private static HashMap<Integer, Graph> generateRandomGraphInstanceCopies(RandomGraphInstances graph, int testRuns,
                                                                             DIMACSReader readWriter)
    {
        String fileName = graph.fileName;
        return generateCopies(fileName, testRuns, readWriter);
    }

    private static HashMap<Integer, Graph> generateCopies(String fileName, int testRuns, DIMACSReader readWriter)
    {
        HashMap<Integer, Graph> graphCopies = new HashMap<>();

        try {
            for(int i = 0; i < testRuns; i++) {
                Graph g = readWriter.readGraphToAdjList(fileName);
                graphCopies.put(i, g);
            }
        } catch (IOException e) {
            System.out.println("File " + fileName + " not found please check the input file");
            e.printStackTrace();
            System.exit(1);
        }

        if (graphCopies.isEmpty()) {
            System.out.println("Problem reading in graph");
            System.exit(1);
        }
        return graphCopies;
    }


    private static void generateRandomGraph(Scanner sc)
    {
        RandomGraphGenerator rgg = new RandomGraphGenerator();

        System.out.print("Number of Nodes: ");
        int V = sc.nextInt();
        System.out.print("p-value: ");
        float p = sc.nextFloat();

        rgg.generateRandomGraph(V, p, "colouring/src/ac/ncl/gcol/data/gengraphs/postgen/");
    }




}

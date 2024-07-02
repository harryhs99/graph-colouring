package ac.ncl.gcalgs;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to handle the input and output of Graphs in DIMACS format
 *
 * @author Harry Hainsworth-Staples
 * Date: 26/06/2024
 */
public class DIMACSGraphIO {
    private PrintWriter PW;
    private Scanner SC;

    public DIMACSGraphIO()
    {
    }

    public DIMACSGraph readGraph(String inputFile) throws FileNotFoundException {
        this.SC = new Scanner(new File(inputFile));
        HashMap<Integer, List<Integer>> tempStore = new HashMap<>();
        int V = 0, E = 0;
        try
        {
            while(SC.hasNext())
            {

                String line = SC.nextLine();
                String[] split = line.split(" ");
                String start = split[0];
                if(!start.equals("e")) {
                    switch (start) {
                        case "c":
                            // ignore comment lines
                            break;
                        case "p":
                            // problem line
                            V = Integer.parseInt(split[2]);
                            E = Integer.parseInt(split[3]);
                            break;
                        default:
                            throw new RuntimeException("Illegal File Format: must be in DIMACS format.");
                    }
                } else
                {
                    if(V == 0 || E == 0) throw new RuntimeException("Illegal File Format: must be in DIMACS format.");

                    int src = Integer.parseInt(split[1]);
                    int dest = Integer.parseInt(split[2]);
                    if(!tempStore.containsKey(src)) tempStore.put(src, new LinkedList<>());
                    tempStore.get(src).add(dest);
                }
        }
        } catch (RuntimeException e)
        {
            throw e;
        }

        DIMACSGraph g = new DIMACSGraph(V, E);
        for(int i : tempStore.keySet())
        {
            for(int j : tempStore.get(i))
            {
                g.addEdge(i, j);
            }
        }

        if(g.getGraphSize() == 0 || g.getGraphOrder() == 0)
        {
            System.out.println("Either unconnected graph or one vertex: colouring is arbitrarily 1.");
        }
        return g;
    }

    public static void printSolution()
    {

    }

    public static void main(String[] args) {
        try {
            DIMACSGraphIO DM = new DIMACSGraphIO();
            DIMACSGraph g = DM.readGraph("/Users/harryhainsworth-staples/masters/CSC8099/Final_Project/project/graph-colouring/marco10.col");
            g.printNeighbours();
            g.printGraph();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }






}

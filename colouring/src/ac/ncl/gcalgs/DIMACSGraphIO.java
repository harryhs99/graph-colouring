package ac.ncl.gcalgs;

import java.io.*;
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
        DIMACSGraph g = new DIMACSGraph();
        try
        {
            while(SC.hasNext())
            {
                String[] line = SC.nextLine().split(" ");
                String start = line[0];
                switch(start)
                {
                    case "c":
                        // ignore comment lines
                        break;
                    case "p":
                        // problem line
                        int ord = Integer.parseInt(line[2]);
                        int size = Integer.parseInt(line[3]);
                        g.setGraphOrder(ord);
                        g.setGraphSize(size);
                        g.setAllVertices(ord);
                        break;
                    case "e":
                        // edge line
                        int src, dest;
                        src = Integer.parseInt(line[1]);
                        dest = Integer.parseInt(line[2]);
                        g.addEdge(src, dest);
                        break;
                    default:
                        throw new RuntimeException("Illegal File Format: must be in DIMACS format.");
                }
        }

        } catch (RuntimeException e)
        {
            throw e;
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
            System.out.println(g.getGraphOrder());
            System.out.println(g.getGraphSize());
            System.out.println(g.getAdjList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }






}

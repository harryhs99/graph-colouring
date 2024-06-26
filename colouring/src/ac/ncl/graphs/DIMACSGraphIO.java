package ac.ncl.graphs;

import java.io.*;
import java.util.Scanner;

/**
 * A class to handle the input and output of Graphs in DIMACS format
 *
 * @author Harry Hainsworth-Staples
 * Date: 26/06/2024
 */
public class DIMACSGraphIO {
    private String inFile;
    private String outFile;
    private static PrintWriter PW;
    private static Scanner SC;

    public DIMACSGraphIO(String outFile, String inFile) throws FileNotFoundException
    {
        this.inFile = inFile;
        this.outFile = outFile;
        PW = new PrintWriter(outFile);
        SC = new Scanner(new FileReader("/Users/harryhainsworth-staples/masters/CSC8099/Final_Project/project/graph-colouring/marco10.col"));
    }

    public static void readGraph()
    {
        int V;

        try
        {
            while(SC.hasNext())
            {
                String line = SC.nextLine();
                String start = line.split(" ")[0];
                switch(start)
                {
                    case "c":
                        // ignore comment lines
                        break;
                    case "p":
                        // problem line
                        System.out.println(line);
                        break;
                    case "e":
                        // edge line
                        System.out.println(line);
                        break;
                    default:
                        throw new RuntimeException("Illegal File Format: must be in DIMACS format.");
                }
        }

        } catch (RuntimeException e)
        {
            throw e;
        }

    }

    public static void printSolution()
    {

    }

    public static void main(String[] args) {
        try {
            DIMACSGraphIO DM = new DIMACSGraphIO("som", "som");
            readGraph();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }






}

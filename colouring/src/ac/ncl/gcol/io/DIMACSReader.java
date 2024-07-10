package ac.ncl.gcol.io;

import ac.ncl.gcol.algs.Greedy;
import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DIMACSReader {
    public DIMACSReader () {}

    public Graph readGraph(String inputFile)
    {
        // Array to store whether a vertex has been seen for any disconnected nodes
        boolean[] seenV = null;
        // V is number of vertices and E is the number of Edges
        int V = -1, E = -1;
        // Array to contain the edges of the graph
        Edge[] edges = null;
        // Array to store the degrees of each node
        int[] degrees = null;
        // Object to store all the read in graph data
        Graph graph;

        try{
            String filePath = new File(inputFile).getAbsolutePath();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null)
            {
                if(line.startsWith("c")) continue;
                else if (line.startsWith("p")) {
                    String[] graphInfo = line.split(" ");
                    V = Integer.parseInt(graphInfo[2]);
                    E = Integer.parseInt(graphInfo[3]);
                }
                break; // start reading in edges
            }

            if(V == -1 || E == -1) throw new IOException("Problem reading in file > Must be in DIMACS format. ");

            degrees = new int[V]; Arrays.fill(degrees, 0);
            edges = new Edge[E];
            seenV = new boolean[V + 1];
            ArrayList<Edge> seenE = new ArrayList<>();

            int counter = 0;
            while((line = br.readLine()) != null)
            {
                String[] edge = line.split(" ");

                Edge e = new Edge((Integer.parseInt(edge[1]) - 1),
                        (Integer.parseInt(edge[2]) - 1));

                // check if seen edge before
                if(seenE.contains(e)) continue;

                // if not seen before add to seen and to edges array
                seenE.add(e);
                edges[counter++] = e;

                // increase the degree of each node as an edge is added
                degrees[e.getU()]++;
                degrees[e.getV()]++;

                // add both nodes to the seen vertex array
                seenV[e.getU() + 1] = true;
                seenV[e.getV() + 1] = true;

            }

            String surplus = br.readLine();
            if(surplus != null) System.out.println("Warning: there appears to be surplus data in your file... please check number of edges.");


        }
        catch (FileNotFoundException e)
        {
            System.out.println("Problem finding file");
            System.exit(1);
        }
        catch (IOException ex)
        {
//            System.out.println("Problem reading in file... must be DIMACS format.");
            System.exit(1);
        }

        // check for any disconnected nodes
        for(int j = 1; j <= V; j++)
        {
            if(!seenV[j]) System.out.println(STR."Warning: vertex \{j} did not appear on an edge it will be considered a disconnected vertex.");
        }

        int maxDeg = 0;
        int maxNode = 0;

        for(int k = 0; k < degrees.length; k++ )
        {
            if(degrees[k] > maxDeg) {
                maxDeg = degrees[k];
                maxNode = k;
            }
        }

        graph = new Graph(V, E, edges, degrees, maxDeg, maxNode);

        return graph;
    }

    public static void main(String[] args) {
        DIMACSReader r = new DIMACSReader();
        Graph g = r.readGraph("colouring/src/ac/ncl/gcol/data/marco10.col");
        Graph g1 = r.readGraph("colouring/src/ac/ncl/gcol/data/marco10.col");
        int[][] AM = g.toAdjMatrix();
        LinkedList<Integer>[] AL = g.toAdjList();

        int[][] AM1 = g1.toAdjMatrix();
        LinkedList<Integer>[] AL1 = g1.toAdjList();

        Greedy greedy = new Greedy(AM, AL, g.getV());
        int[] sol = greedy.colour(true);
//        for(int i = 0; i < g.getV(); i++) System.out.println("v: " + (i + 1) + ", Colour: " + sol[i]);

        Greedy greedy1 = new Greedy(AM1, AL1, g1.getV());
        int[] sol1 = greedy1.colour(false);
//        for(int i = 0; i < g.getV(); i++) System.out.println("v: " + (i + 1) + ", Colour: " + sol1[i]);
        System.out.println("RandomValid?: " + greedy.checkValid() + ", InOrderValid?: " + greedy1.checkValid());
        System.out.println("RandomK: " + greedy.getK() + ", InOrderK: " + greedy1.getK());
    }
}

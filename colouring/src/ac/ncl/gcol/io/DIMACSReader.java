package ac.ncl.gcol.io;

import ac.ncl.gcol.algs.GraphColouring;
import ac.ncl.gcol.algs.Greedy;
import ac.ncl.gcol.exceptions.SolutionNotFoundException;
import ac.ncl.gcol.graph.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DIMACSReader {
    public DIMACSReader () {}

    public AdjListGraph readGraphToAdjList(String inputFile)
    {
        // Array to store whether a vertex has been seen for any disconnected nodes
        boolean[] seenV = null;
        // V is number of vertices and E is the number of Edges
        int V = -1, E = -1;
        // Array to contain the edges of the graph
        ArrayList<Edge> edges;
        // Array to contain all the vertices of the graph
        ArrayList<Vertex> vertices;
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

            if(V == -1 || E == -1) throw new IOException();

            degrees = new int[V]; Arrays.fill(degrees, 0);
            edges = new ArrayList<>();
            vertices = new ArrayList<>();
            seenV = new boolean[V + 1];

            int counter = 0;
            while((line = br.readLine()) != null)
            {
                String[] edge = line.split(" ");

                Vertex u = new Vertex(Integer.parseInt(edge[1]));
                Vertex v = new Vertex(Integer.parseInt(edge[2]));

                if(!vertices.contains(u)) vertices.add(u);
                if(!vertices.contains(v)) vertices.add(v);

                Edge e = new Edge(u, v);

                edges.add(e);

                // increase the degree of each node as an edge is added
                degrees[e.getSrc().getName() - 1]++;

                // add both nodes to the seen vertex array
                seenV[u.getName()] = true;
                seenV[v.getName()] = true;

            }

            String surplus = br.readLine();
            if(surplus != null) System.out.println("Warning: there appears to be surplus data in your file... please check number of edges.");


        } catch (FileNotFoundException e)
        {
            System.out.println("File not found: please check input file.");
            return null;
        } catch (IOException e) {
            System.out.println("Problem reading in file: Must be in DIMACS format.");
            return null;
        }

        // check for any disconnected nodes
        for(int j = 1; j <= V; j++)
        {

            if(!seenV[j]) {
                System.out.println(STR."Warning: vertex \{j} did not appear on an edge it will be considered a disconnected vertex.");
                Vertex v = new Vertex(j);
                vertices.add(v);
            }
        }

        // sort to be in order
        Collections.sort(vertices);

        int maxDeg = 0;
        int maxNode = 0;

        for(int k = 0; k < degrees.length; k++ )
        {
            if(degrees[k] > maxDeg) {
                maxDeg = degrees[k];
                maxNode = k + 1;
            }
        }

        graph = new AdjListGraph(V, E, edges, vertices,  degrees, maxDeg, maxNode);

        return (AdjListGraph) graph;

    }



    public static void main(String[] args) {
        DIMACSReader r = new DIMACSReader();
        AdjListGraph g = r.readGraphToAdjList("colouring/src/ac/ncl/gcol/data/graph.txt");
       g.printGraph();
//        System.out.println(Arrays.toString(g.getDegrees()));
//        System.out.println(g.getMaxNode());
//        System.out.println(g.getMaxDeg());

        GraphColouring greedy = new Greedy(true);
        int k = 0;
         k = greedy.colour(g);

        try {
            g.printSolution();
            System.out.println(g.validSolution());
        } catch (SolutionNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Graph is " + k + "-colourable");



    }
}

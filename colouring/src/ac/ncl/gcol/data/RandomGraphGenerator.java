package ac.ncl.gcol.data;

import ac.ncl.gcol.graph.Edge;
import ac.ncl.gcol.graph.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomGraphGenerator {
    public void generateRandomGraph(int V, float p, String filePath)
    {
        if(p < 0 || p > 1) throw new IllegalArgumentException("p must be between 0 and 1");

        String graphName = "rg" + V + "_" + p + ".col";
        PrintWriter graphFile = null;

        try
        {
            graphFile = new PrintWriter(new File(filePath + graphName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        Random rand = new Random();
        int[][] adjMat = new int[V][V];
        int edges = 0, jStart = 0;
        int[] degrees = new int[V];

        for(int i = 0; i < V; i++)
        {
            for(int j = jStart; j < V; j++)
            {
                if(rand.nextFloat() < p && i != j)
                {
                   adjMat[i][j] = 1;
                   adjMat[j][i] = 1;
                   degrees[i]++;
                   degrees[j]++;
                   edges++;
                }
            }
            jStart++;
        }

        long totalDegs = 0;
        int maxDeg = 0, minDeg = Integer.MAX_VALUE;

        for(int z = 0; z < V; z++) {
            if (degrees[z] > maxDeg) maxDeg = degrees[z];
            if (degrees[z] < minDeg) minDeg = degrees[z];
            totalDegs += degrees[z];
        }


        float density = (float) (2 * edges)/(V * (V - 1));
        float avgDegs = (float) totalDegs / V;

        graphFile.println("c File: " + graphName);
        graphFile.println("c ");
        graphFile.println("c This is a randomly generated graph G(n, p) where: ");
        graphFile.println("c n (num vertices): " + V);
        graphFile.println("c p (edge probability): " + p);
        graphFile.println("c ");
        graphFile.println("c Graph Properties: ");
        graphFile.println("c Order: " + V);
        graphFile.println("c Size: " + edges);
        graphFile.println("c Density: " + density);
        graphFile.println("c Maximum Degree: " + maxDeg);
        graphFile.println("c Minimum Degree: " + minDeg);
        graphFile.println("c Average Degree: " + avgDegs);
        graphFile.println("c ");
        graphFile.println("p edge " + V + " " + edges);

        int vStart = 0;
        for(int u = 0; u < V; u++)
        {
            for(int v = vStart; v < V; v++)
            {
                if(adjMat[u][v] == 1)
                {
                    graphFile.println("e " + (u + 1) + " " + (v + 1));
                }
            }
            vStart++;
        }

        graphFile.close();

    }

    public static void main(String[] args) {
        String filePath = "colouring/src/ac/ncl/gcol/data/gengraphs/pregen/";
        RandomGraphGenerator r = new RandomGraphGenerator();
        // generate extreme sparse graphs
        r.generateRandomGraph( 50, 0.1F, filePath);
        r.generateRandomGraph(100, 0.1F, filePath);
        r.generateRandomGraph(250, 0.1F, filePath);
        r.generateRandomGraph(500, 0.1F, filePath);
        r.generateRandomGraph(750, 0.1F, filePath);
        r.generateRandomGraph(1000, 0.1F, filePath);
        r.generateRandomGraph(1500, 0.1F, filePath);
        // generate mid sparse graphs
        r.generateRandomGraph( 50, 0.3F, filePath);
        r.generateRandomGraph(100, 0.3F, filePath);
        r.generateRandomGraph(250, 0.3F, filePath);
        r.generateRandomGraph(500, 0.3F, filePath);
        r.generateRandomGraph(750, 0.3F, filePath);
        r.generateRandomGraph(1000, 0.3F, filePath);
        r.generateRandomGraph(1500, 0.3F, filePath);
        // generate mid density graphs
        r.generateRandomGraph( 50, 0.5F, filePath);
        r.generateRandomGraph(100, 0.5F, filePath);
        r.generateRandomGraph(250, 0.5F, filePath);
        r.generateRandomGraph(500, 0.5F, filePath);
        r.generateRandomGraph(750, 0.5F, filePath);
        r.generateRandomGraph(1000, 0.5F, filePath);
        r.generateRandomGraph(1500, 0.5F, filePath);
        // generate mid-high density graphs
        r.generateRandomGraph( 50, 0.7F, filePath);
        r.generateRandomGraph(100, 0.7F, filePath);
        r.generateRandomGraph(250, 0.7F, filePath);
        r.generateRandomGraph(500, 0.7F, filePath);
        r.generateRandomGraph(750, 0.7F, filePath);
        r.generateRandomGraph(1000, 0.7F, filePath);
        r.generateRandomGraph(1500, 0.7F, filePath);
        // generate high density graphs
        r.generateRandomGraph( 50, 0.9F, filePath);
        r.generateRandomGraph(100, 0.9F, filePath);
        r.generateRandomGraph(250, 0.9F, filePath);
        r.generateRandomGraph(500, 0.9F, filePath);
        r.generateRandomGraph(750, 0.9F, filePath);
        r.generateRandomGraph(1000, 0.9F, filePath);
        r.generateRandomGraph(1500, 0.9F, filePath);
    }

}

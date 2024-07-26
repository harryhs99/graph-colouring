package ac.ncl.gcol.algs;

import ac.ncl.gcol.graph.AdjListGraph;
import ac.ncl.gcol.graph.AdjMatrixGraph;
import ac.ncl.gcol.graph.Graph;
import ac.ncl.gcol.graph.Vertex;

import java.util.*;

public class RLFGraphColouring extends AbstractGraphColouring {

    private Vertex chooseFirst(Set<Vertex> X, int[] NinX)
    {
        Vertex v = null;
        int max = -1;
        for(Vertex u: X)
        {
            int uIdx = u.getName() - 1;
            if(NinX[uIdx] > max)
            {
                max = NinX[uIdx];
                v = u;
            }
        }
        return v;
    }

    private Vertex chooseNext(Set<Vertex> X, int[] NinY, int[] NinX)
    {
        Vertex next = null;
        int max = -1, min = Integer.MAX_VALUE;
        for(Vertex u: X)
        {
            int uIdx = u.getName() - 1;
            if((NinY[uIdx] > max) || ((NinY[uIdx] == max) && (NinX[uIdx] < min)))
            {
                max = NinY[uIdx];
                min = NinX[uIdx];
                next = u;
            }
        }
        return next;
    }

    private void updateSetsAndDegrees(Vertex v, Map<Vertex, ArrayList<Vertex>> adjList,
                                      Set<Vertex> X, Set<Vertex> Y, int[] NinX, int[] NinY, Set<Vertex> D2)
    {
        X.remove(v);

        for(Vertex n: adjList.get(v))
        {
            if(n.getColour() == -1)
            {
                X.remove(n);
                Y.add(n);
            }
        }

        for(Vertex u: adjList.get(v))
        {
            if(u.getColour() == -1)
            {
                D2.add(u);
                for(Vertex w: adjList.get(u))
                {
                    if(w.getColour() == -1)
                    {
                        D2.add(w);
                    }
                }
            }
        }

        for(Vertex u: D2)
        {

            int uIdx = u.getName() - 1;
            NinX[uIdx] = 0;
            NinY[uIdx] = 0;
            for(Vertex w: adjList.get(u))
            {
                if(w.getColour() == -1)
                {
                    if(X.contains(w)) NinX[uIdx]++;
                    else if(Y.contains(w)) NinY[uIdx]++;
                }
            }
        }




    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjList(AdjListGraph g)
    {
        this.solution = new HashMap<>();
        HashSet<Vertex> X = new HashSet<>(g.getVertices()), Y = new HashSet<>(), D2 = new HashSet<>();
        int[] NinX = g.getDegrees(), NinY = new int[g.getOrder()];
        Arrays.fill(NinY, 0);
        Map<Vertex, ArrayList<Vertex>> adjList = g.getAdjList();
        int col = 0;

        while(!X.isEmpty())
        {

            HashSet<Vertex> S = new HashSet<>();
            Vertex firstV = chooseFirst(X, NinX);
            S.add(firstV);
            firstV.setColour(col);

            updateSetsAndDegrees(firstV, adjList, X, Y, NinX, NinY, D2);

            while (!X.isEmpty())
            {
                Vertex nextV = chooseNext(X, NinY, NinX);
                S.add(nextV);
                nextV.setColour(col);
                updateSetsAndDegrees(nextV, adjList, X, Y, NinX, NinY, D2);
            }

            solution.put(col, S);

            X.addAll(Y);
            Y.clear();
            col++;
        }
        return solution;
    }

    private HashMap<Integer, HashSet<Vertex>> colourAdjMatrix(AdjMatrixGraph g)
    {
        return null;
    }

    @Override
    public HashMap<Integer, HashSet<Vertex>> colour(Graph g) {
        HashMap<Integer, HashSet<Vertex>> solution;
        if(g instanceof AdjListGraph) solution = colourAdjList((AdjListGraph) g);
        else solution = colourAdjMatrix((AdjMatrixGraph) g);
        g.setColouring(solution);
        return solution;
    }

    @Override
    public String toString() {
        return "RLF";
    }
}

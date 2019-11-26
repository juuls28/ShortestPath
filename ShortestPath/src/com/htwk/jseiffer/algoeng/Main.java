package com.htwk.jseiffer.algoeng;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GraphImport importer = new GraphImport("data\\ny-4882,4.dimacs");
        Graph graph = importer.readGraph();

        System.out.println(graph);

        List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
        System.out.println("Amount of pairs: " + permutationList.size());
    }
}

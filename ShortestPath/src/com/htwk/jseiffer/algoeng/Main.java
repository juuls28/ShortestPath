package com.htwk.jseiffer.algoeng;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        GraphImport importer = new GraphImport("data\\ny-4882,10.dimacs");
        Graph graph = importer.readGraph();

        System.out.println(graph);

        List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
        System.out.println("Amount of pairs: " + permutationList.size());

        Map<Vertex,Integer> results =Algorithms.dijkstra(graph, graph.getVertexes().get(1));
        System.out.println("Results of: " + graph.getVertexes().get(1));
        System.out.println(results);
    }
}

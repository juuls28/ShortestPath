package com.htwk.jseiffer.algoeng;

public class Main {

    public static void main(String[] args) {
        System.out.println("HelloWorld");
        GraphImport importer = new GraphImport("data\\ny-335,4.dimacs");
        Graph graph = importer.readGraph();

        System.out.println(graph);
    }
}

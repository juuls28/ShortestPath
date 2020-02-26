package com.htwk.jseiffer.algoeng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
//        String data = "data/california/roadNet-CA.txt";
//        GraphImport importer = new GraphImport(data);
//        List<Graph> graphs = new ArrayList<>();
//        graphs.add(importer.readGraph(108));
//
//        graphs.add(importer.readGraph(136));
//
//        graphs.add(importer.readGraph(171));
//
//        graphs.add(importer.readGraph(214));
//
//        graphs.add(importer.readGraph(268));
//
//        graphs.add(importer.readGraph(335));
//
//        graphs.add(importer.readGraph(419));
//
//        graphs.add(importer.readGraph(524));
//
//        graphs.add(importer.readGraph(655));
//
//        graphs.add(importer.readGraph(819));
//
//        graphs.add(importer.readGraph(1024));
//
//        graphs.add(importer.readGraph(1280));
//
//                for (Graph graph:graphs) {
//
//            log.info("Size: " + graph.getVertexes().size());
//
//            List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
//            log.info("Amount of pairs: " + permutationList.size());
//
//            int overallC = 0;
//            for(int i = 0; i < 20; i++) {
//                final long timeStart = System.currentTimeMillis();
//                Algorithms.nDijkstra(graph);
//                final long timeEnd = System.currentTimeMillis();
//                System.out.println((timeEnd - timeStart));
//                overallC += (timeEnd - timeStart);
//            }
//            log.info("Mean Time Dijkstra: " + overallC/20);
//
//        }


        List<String> dataset = new ArrayList<>();
        dataset.add("data/benchmark/ny-524,10.dimacs");
        dataset.add("data/benchmark/ny-655,10.dimacs");
        dataset.add("data/benchmark/ny-819,10.dimacs");
        dataset.add("data/benchmark/ny-1024,10.dimacs");
        dataset.add("data/benchmark/ny-1280,10.dimacs");


        for (String data:dataset) {
            GraphImport importer = new GraphImport(data);
            Graph graph = importer.readGraph(10000);

            log.info("Data: " + data);

            List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
            log.info("Amount of pairs: " + permutationList.size());

            int overallA = 0;
            for(int i = 0; i < 20; i++) {
                final long timeStart = System.currentTimeMillis();
                Algorithms.nDijkstraParallel(graph, 8);
                final long timeEnd = System.currentTimeMillis();
                System.out.println((timeEnd - timeStart));
                overallA += (timeEnd - timeStart);
            }
            log.info("Mean Time Dijkstra Parallel: " + overallA/20);

            int overallB = 0;
            for(int i = 0; i < 20; i++) {
                final long timeStart = System.currentTimeMillis();
                Algorithms.floydWarshall(graph);
                final long timeEnd = System.currentTimeMillis();
                System.out.println((timeEnd - timeStart));
                overallB += (timeEnd - timeStart);
            }
            log.info("Mean Time Floyd-Wahrshall: " + overallB/20);

            int overallC = 0;
            for(int i = 0; i < 20; i++) {
                final long timeStart = System.currentTimeMillis();
                Algorithms.nDijkstra(graph);
                final long timeEnd = System.currentTimeMillis();
                System.out.println((timeEnd - timeStart));
                overallC += (timeEnd - timeStart);
            }
            log.info("Mean Time Dijkstra: " + overallC/20);

        }

    }
}

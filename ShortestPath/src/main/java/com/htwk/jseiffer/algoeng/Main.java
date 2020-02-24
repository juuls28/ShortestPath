package com.htwk.jseiffer.algoeng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        //String data = "data/benchmark/ny-819,10.dimacs";
        List<String> dataset = new ArrayList<>();
        dataset.add("data/benchmark/ny-1280,10.dimacs");
        dataset.add("data/benchmark/ny-1600,10.dimacs");
        dataset.add("data/benchmark/ny-2000,10.dimacs");
        dataset.add("data/benchmark/ny-2500,10.dimacs");
        dataset.add("data/benchmark/ny-3125,10.dimacs");
        dataset.add("data/benchmark/ny-3906,10.dimacs");
        dataset.add("data/benchmark/ny-4882,10.dimacs");


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
                //Algorithms.nDijkstra(graph);
                final long timeEnd = System.currentTimeMillis();
                //log.info("Time: " + (timeEnd - timeStart));
                overallA += (timeEnd - timeStart);
            }
            log.info("Mean Time Dijkstra Parallel: " + overallA/20);

            int overallB = 0;
            for(int i = 0; i < 20; i++) {
                final long timeStart = System.currentTimeMillis();
                Algorithms.floydWarshall(graph);
                final long timeEnd = System.currentTimeMillis();
                //log.info("Time: " + (timeEnd - timeStart));
                overallB += (timeEnd - timeStart);
            }
            log.info("Mean Time Floyd-Wahrshall: " + overallB/20);

            int overallC = 0;
            for(int i = 0; i < 20; i++) {
                final long timeStart = System.currentTimeMillis();
                Algorithms.nDijkstra(graph);
                final long timeEnd = System.currentTimeMillis();
                //log.info("Time: " + (timeEnd - timeStart));
                overallC += (timeEnd - timeStart);
            }
            log.info("Mean Time Dijkstra: " + overallC/20);

        }

    }
}

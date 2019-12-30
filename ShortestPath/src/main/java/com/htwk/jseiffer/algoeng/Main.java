package com.htwk.jseiffer.algoeng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String data = "data\\ny-108,1.dimacs";
        GraphImport importer = new GraphImport(data);
        Graph graph = importer.readGraph();

        log.info("Data: " + data);

        List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
       log.info("Amount of pairs: " + permutationList.size());

       Algorithms.floydWarshall(graph);
       Algorithms.nDijkstra(graph);

       Algorithms.compareMatrix(Algorithms.floydWarshall(graph), Algorithms.nDijkstra(graph));

//       for(int i = 0; i < 20; i++) {
//           final long timeStart = System.currentTimeMillis();
//           Algorithms.nDijkstraParallel(graph, 20);
//           final long timeEnd = System.currentTimeMillis();
//           log.info("Time: " + (timeEnd - timeStart));
//       }
    }
}

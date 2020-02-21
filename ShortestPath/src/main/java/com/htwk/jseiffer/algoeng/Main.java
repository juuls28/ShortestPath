package com.htwk.jseiffer.algoeng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String data = "C:\\Users\\Julius\\Documents\\HTWK\\ShortestPath\\ShortestPath\\data\\california\\roadNet-CA.txt";
        int graphSize = 200;
        GraphImport importer = new GraphImport(data);
        Graph graph = importer.readGraph(graphSize);

        //graph.optimizeGraph();

        log.info("Data: " + data);

        List<Vertex[]> permutationList = Algorithms.getPermutation(graph.getVertexes(),Vertex.class);
       log.info("Amount of pairs: " + permutationList.size());

       //Algorithms.floydWarshall(graph);
       //Algorithms.nDijkstra(graph);

        Map<Vertex, Integer> settle= Algorithms.dijkstra(graph, graph.getVertexes().get(1));
        System.out.println(graph.getVertexes().get(1));
        System.out.println(settle);

       //Algorithms.compareMatrix(Algorithms.floydWarshall(graph), Algorithms.nDijkstra(graph));

//       for(int i = 0; i < 20; i++) {
//           final long timeStart = System.currentTimeMillis();
//           Algorithms.nDijkstraParallel(graph, 20);
//           final long timeEnd = System.currentTimeMillis();
//           log.info("Time: " + (timeEnd - timeStart));
//       }
    }
}

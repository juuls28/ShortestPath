package com.htwk.jseiffer.algoeng;

import java.util.List;

public class DijkstraThread implements Runnable {
    Graph graph;
    List<Vertex> nodes;

    public DijkstraThread(Graph graph, List<Vertex> nodes) {
        this.graph = graph;
        this.nodes = nodes;
    }

    @Override
    public void run() {
        for(Vertex v: nodes){
            Algorithms.dijkstra(graph, v);
        }
    }
}

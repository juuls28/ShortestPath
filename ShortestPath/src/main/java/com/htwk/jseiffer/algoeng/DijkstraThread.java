package com.htwk.jseiffer.algoeng;

import java.util.List;

public class DijkstraThread implements Runnable {
    Graph graph;
    List<Vertex> nodes;

    public DijkstraThread(Graph graph, List<Vertex> nodes) {
        this.graph = graph;
        this.nodes = nodes;
        //System.out.println(this.printInfo());
    }

    @Override
    public void run() {
        for(Vertex v: nodes){
            Algorithms.dijkstra(graph, v);
        }
    }

    public String printInfo(){
        return "Startnode: " + nodes.get(0) + "Endnode: " + nodes.get(nodes.size()-1);
    }
}

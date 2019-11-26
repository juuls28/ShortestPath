package com.htwk.jseiffer.algoeng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
    }

    public List<Vertex> getVertexes(){
        List<Vertex> vertexes = new ArrayList<>();
        vertexes.addAll(adjVertices.keySet());

        return vertexes;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjVertices=" + adjVertices +
                '}';
    }
}

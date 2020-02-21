package com.htwk.jseiffer.algoeng;

import org.junit.Test;

import java.util.*;

public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void addEdge(String label1, String label2, int cost) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2, cost);
        adjVertices.get(v1).add(v2);
    }

    public List<Vertex> getVertexes(){
        List<Vertex> vertexes = new ArrayList<>();
        vertexes.addAll(adjVertices.keySet());

        return vertexes;
    }

    public List<Vertex> getNeighbours(Vertex v){
        return adjVertices.get(v);
    }

    void optimizeGraph(){
        Map<Integer, Integer> changes = new HashMap<>();
        int counter = 0;
        //give Vertexes a new id
        for (Vertex v : this.adjVertices.keySet()) {
            changes.put(v.getNumber(),counter);
            v.setNumber(counter);
            counter ++;
        }
        //change the ids of the neighbours
        for (List<Vertex> n : this.adjVertices.values()) {
            for(Vertex vi : n){
                if(changes.containsKey(vi.getNumber())){
                    vi.setNumber(changes.get(vi.getNumber()));
                }else{
                    if(n.remove(vi)){
                        System.out.println("removed Vertex: " + vi.getName());
                    }else{
                        System.err.println("Could not removed Vertex: " + vi.getName());
                    }
                }

            }
        }

    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjVertices=" + adjVertices +
                '}';
    }
}

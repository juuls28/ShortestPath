package com.htwk.jseiffer.algoeng;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Algorithms{
    public static <T> List<T[]> getPermutation(List<T> input, Class<T> c){
        List<T[]> permutations = new ArrayList<>();
        try {

            while (!input.isEmpty()) {
                T current = input.remove(0);
                for (T element : input) {
                    T[] temp = (T[]) Array.newInstance(c, 2);
                    temp[0] = current;
                    temp[1] = element;
                    permutations.add(temp);
                }
            }
        }catch(NullPointerException e){
            System.err.println("Deine scheiß Liste ist leer!");
        }
        return permutations;
    }

    public static Map<Vertex, Integer> dijkstra(Graph graph, Vertex startnode){
        Map<Vertex, Integer> score = new HashMap<>();
        Map<Vertex, Integer> setteled = new HashMap<>();

        //Initialize score with infinity
        for(int i = 0; i < graph.getVertexes().size(); i++){
            score.put(graph.getVertexes().get(i), Integer.MAX_VALUE);
        }
        //Initialize first node
        score.replace(startnode, 0);

        while(setteled.size()!=graph.getVertexes().size()){
            //search for node with lowest score
            Map.Entry<Vertex, Integer> min = null;
            for(Map.Entry<Vertex, Integer> entry:score.entrySet()){
                if(min==null||min.getValue() > entry.getValue()){
                    min = entry;
                }
            }
            Vertex u = min.getKey();
            int uScore = min.getValue();

            for(int i = 0; i<graph.getNeighbours(u).size(); i++){
                Vertex v = graph.getNeighbours(u).get(i);
                if(setteled.containsKey(v)){
                    //Vertex already found
                }else{
                    int vCost = v.getCost()+uScore;
                    if(vCost < score.get(v)){
                        //If new lower cost is detected, update score
                        score.replace(v, vCost);
                    }
                }
            }
            setteled.put(u, uScore);
            score.remove(u);
        }
        return setteled;

    }

    public static void nDijkstra(Graph graph){
        List<Vertex> nodes = graph.getVertexes();

        for(Vertex v: nodes){
            dijkstra(graph, v);
        }
    }

    public static void nDijkstraParallel(Graph graph, int threads){
        List<Vertex> nodes = graph.getVertexes();
        int nodeIntervall = nodes.size()/threads;

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for(int i = 0; i<threads; i++){
            Runnable worker;
            if(i == threads-1){
                worker = new DijkstraThread(graph, nodes.subList(i*nodeIntervall,nodes.size()));
            }else {
                worker = new DijkstraThread(graph, nodes.subList(i * nodeIntervall, (i + 1) * nodeIntervall));
            }

            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all Threads");
    }

    public static void floydWarshall(Graph graph){
        int[][] distMatr = new int[graph.getVertexes().size()][graph.getVertexes().size()];
        //Calculate distance matrix

    }

}

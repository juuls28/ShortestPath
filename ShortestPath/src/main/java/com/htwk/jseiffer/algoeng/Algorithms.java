package com.htwk.jseiffer.algoeng;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Algorithms{
    static <T> List<T[]> getPermutation(List<T> input, Class<T> c){
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

    static Map<Vertex, Integer> dijkstra(Graph graph, Vertex startnode){
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
            if(!(graph.getNeighbours(u).size()==0)) {
                for (int i = 0; i < graph.getNeighbours(u).size(); i++) {
                    Vertex v = graph.getNeighbours(u).get(i);
                    if (setteled.containsKey(v)) {
                        //Vertex already found
                    } else {
                        int vCost = v.getCost() + uScore;
                        if (vCost < score.get(v)) {
                            //If new lower cost is detected, update score
                            score.replace(v, vCost);
                        }
                    }
                }
            }
            //add node to setteled nodes
            setteled.put(u, uScore);
            //remove node with lowest score
            score.remove(u);
        }
        return setteled;

    }

    public static int[][] nDijkstra(Graph graph){
        List<Vertex> nodes = graph.getVertexes();
        int[][] distMatr = new int[graph.getVertexes().size()+1][graph.getVertexes().size()+1];

        //Initialize Matrix with infinity
        for (int j = 0; j< distMatr.length; j++) {
            for (int i = 0; i < distMatr.length; i++){
                distMatr[i][j] = Integer.MAX_VALUE;
            }
        }

        for(Vertex v: nodes){
            distMatr[v.getNumber()][v.getNumber()] = 0;
            Map<Vertex, Integer> tempMap = dijkstra(graph, v);
            for (Map.Entry<Vertex, Integer> e: tempMap.entrySet()) {
                distMatr[v.getNumber()][e.getKey().getNumber()]=e.getValue();
            }
        }
        //System.out.println("DIJKSTRA:");
        //printMatrix(distMatr);

        return distMatr;
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
        //System.out.println("Finished all Threads");
    }

    static int[][] floydWarshall(Graph graph){

        int[][] distMatr = new int[graph.getVertexes().size()+1][graph.getVertexes().size()+1];
        //Initialize Matrix with infinity
        for (int j = 0; j< distMatr.length; j++) {
            for (int i = 0; i < distMatr.length; i++){
                distMatr[i][j] = Integer.MAX_VALUE;
            }
        }

        //Calculate distance matrix
        for(Vertex v : graph.getVertexes()){
            int index = v.getNumber();
            //set weight from node to it self on 0
            distMatr[index][index] = 0;
            //set weight from node v to all neighbours on cost of the edges
            if(graph.getNeighbours(v).size()==0){
                //let them be infinity
            }else {
                for (Vertex n : graph.getNeighbours(v)) {
                    distMatr[v.getNumber()][n.getNumber()] = n.getCost();
                }
            }
        }

        //Floyd-Warshall-Algorithmn
        for(int k = 1; k < distMatr.length; k++){
            for(int i = 1; i < distMatr.length; i++){
                for(int j = 1; j < distMatr.length; j++) {
                    if (distMatr[i][k] < Integer.MAX_VALUE && distMatr[k][j] < Integer.MAX_VALUE) {
                        int dist = distMatr[i][k] + distMatr[k][j];
                        if(dist < distMatr[i][j]){
                            distMatr[i][j]=dist;
                        }
                    }
                }
            }
        }

        return distMatr;

    }



    private static void printMatrix(int[][] mat){
        for (int j = 0; j< mat.length; j++) {
            for (int i = 0; i < mat.length; i++){
                int value = mat[i][j];
                String output = "FAIL";
                if(Math.abs(value) == Integer.MAX_VALUE || value < 0
                ){
                    output = "INF";
                }else{
                    output = String.valueOf(value);
                }

                System.out.print(output + "\t");
            }
            System.out.println();
        }
    }

    static void compareMatrix(int[][] m1, int[][]m2){
        if(m1.length != m2.length){
            System.out.println("Not same rank");
            return;
        }
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1.length; j++){
                if(m1[i][j]!=m2[i][j]){
                    System.out.println("Not the same matrix");
                    return;
                }
            }
        }
        System.out.println("Is the same matrix");
    }



}

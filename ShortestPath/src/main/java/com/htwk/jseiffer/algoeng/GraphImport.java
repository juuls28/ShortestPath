package com.htwk.jseiffer.algoeng;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GraphImport {
    String path;

    public GraphImport(String path) {
        this.path = path;
    }

    public Graph readGraph(int maxVertex){
        int counter = 0;
        Graph graph = new Graph();
        File file = new File(path);
        String line = "";
        List<String> compVertex = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            br.readLine();
            if(br.readLine().startsWith("#")){
                br.readLine();
            }



            while((line = br.readLine()) != null && counter <= maxVertex-1){


                String[] arguments = line.split("\\s+");

                //Case of benchmark-data
                if(arguments.length == 4) {
                    graph.addVertex(arguments[1]);
                    graph.addVertex(arguments[2]);
                    graph.addEdge(arguments[1], arguments[2], Integer.valueOf(arguments[3]));
                }
                //Case of CA-streetdata
                else if(arguments.length == 2){

                    String vertexA = arguments[0];
                    String vertexB = arguments[1];
                    String vertexANew ="";
                    String vertexBNew ="";
                    //check for new vertex A
                    if(compVertex.contains(vertexA)){
                        vertexANew = String.valueOf(compVertex.indexOf(vertexA));
                    }else{
                        compVertex.add(vertexA);
                        vertexANew = String.valueOf(compVertex.indexOf(vertexA));
                        counter++;
                    }

                    //check for new vertex B
                    if(compVertex.contains(vertexB)){
                        vertexBNew = String.valueOf(compVertex.indexOf(vertexB));
                    }else{
                        compVertex.add(vertexB);
                        vertexBNew = String.valueOf(compVertex.indexOf(vertexB));
                        counter++;
                    }
                    graph.addVertex(vertexANew);
                    graph.addVertex(vertexBNew);
                    graph.addEdge(vertexANew,vertexBNew, 1);
                    graph.addEdge(vertexBNew,vertexANew, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println(line);
        }

        return graph;
    }
}

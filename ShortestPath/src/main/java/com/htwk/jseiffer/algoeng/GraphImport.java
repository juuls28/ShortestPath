package com.htwk.jseiffer.algoeng;

import java.io.*;

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
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            br.readLine();
            br.readLine();

            while(br.readLine().startsWith("#")){
                //jump over
            }

            while((line = br.readLine()) != null && counter <= maxVertex){
                String[] arguments = line.split("\\s+");

                //Case of benchmark-data
                if(arguments.length == 4) {
                    graph.addVertex(arguments[1]);
                    graph.addVertex(arguments[2]);
                    graph.addEdge(arguments[1], arguments[2], Integer.valueOf(arguments[3]));
                }
                //Case of CA-streetdata
                else if(arguments.length == 2){
                    graph.addVertex(arguments[0]);
                    graph.addVertex(arguments[1]);
                    graph.addEdge(arguments[0],arguments[1], 1);
                }
                counter++;
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

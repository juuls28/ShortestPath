package com.htwk.jseiffer.algoeng;

import java.io.*;

public class GraphImport {
    String path;

    public GraphImport(String path) {
        this.path = path;
    }

    public Graph readGraph(){
        Graph graph = new Graph();
        File file = new File(path);
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            br.readLine();
            br.readLine();

            while((line = br.readLine()) != null){
                String[] arguments = line.split(" ");
                graph.addVertex(arguments[1]);
                graph.addVertex(arguments[2]);
                graph.addEdge(arguments[1],arguments[2], Integer.valueOf(arguments[3]));
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

package com.htwk.jseiffer.algoeng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Algorithms {
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

    public static void dijkstra(Graph graph, Vertex[] vertexes){
        //TODO
    }
}

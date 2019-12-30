package com.htwk.jseiffer.algoeng;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmsTest {

    @Test
    public void getPermutation() {
        List<Integer> list = new ArrayList<>();
        List<Integer[]> permutations = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        permutations.add(new Integer[]{1,2});
        permutations.add(new Integer[]{1,3});
        permutations.add(new Integer[]{1,4});
        permutations.add(new Integer[]{2,3});
        permutations.add(new Integer[]{2,4});
        permutations.add(new Integer[]{3,4});

        assertArrayEquals(permutations.toArray(), Algorithms.getPermutation(list, Integer.class).toArray());
    }

    @Test
    public void dijkstra() {
    }

    @Test
    public void nDijkstra() {
    }

    @Test
    public void nDijkstraParallel() {
    }

    @Test
    public void floydWarshall() {
    }

    @Test
    public void compareMatrix() {
    }
}
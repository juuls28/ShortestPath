package com.htwk.jseiffer.algoeng;

import java.util.Objects;

public class Vertex {
    private String name;
    private int cost = 0;

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getNumber(){
        return Integer.valueOf(name);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}

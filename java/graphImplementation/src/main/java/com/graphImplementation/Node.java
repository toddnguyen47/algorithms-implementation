package com.graphImplementation;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
  public T val;
  public List<Node<T>> neighbors;
  public boolean visited;

  public Node(T val) {
    this.val = val;
    this.neighbors = new ArrayList<>();
    this.visited = false;
  }

  public void addNeighbor(Node<T> neighbor) {
    this.neighbors.add(neighbor);
  }
}

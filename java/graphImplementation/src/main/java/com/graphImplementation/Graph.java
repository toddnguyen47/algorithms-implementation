package com.graphImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph<T> {
  private List<Node<T>> listOfNodes;
  private HashMap<T, Node<T>> hmapOfNodes;

  public Graph() {
    listOfNodes = new ArrayList<>();
    hmapOfNodes = new HashMap<>();
  }

  public int getNumberOfNodes() {
    return this.listOfNodes.size();
  }

  public boolean addNode(Node<T> node) {
    if (hmapOfNodes.containsKey(node.val))
      return false;
    listOfNodes.add(node);
    hmapOfNodes.put(node.val, node);
    return true;
  }

  public Iterator<Node<T>> iterateListOfNodes() {
    return listOfNodes.iterator();
  }

  public Node<T> getNode(T val) {
    return this.hmapOfNodes.get(val);
  }
}

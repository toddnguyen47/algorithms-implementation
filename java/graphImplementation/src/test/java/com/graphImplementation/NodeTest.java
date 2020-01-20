package com.graphImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {
  private Node<Integer> node;
  private int initNodeVal = 5;

  @BeforeEach
  public void setup() {
    this.node = new Node<>(initNodeVal);
  }

  @Test
  public void constructNodeWithVal() {
    Node<Integer> n = new Node<>(initNodeVal);
    assertEquals(5, n.val);
  }

  @Test
  public void emptyNeighborsWhenCreated() {
    Node<Integer> n = new Node<>(initNodeVal);
    assertEquals(0, n.neighbors.size());
  }

  @Test
  public void addOneNeighbor() {
    int val = 10;
    Node<Integer> neighborNode = new Node<Integer>(val);
    node.addNeighbor(neighborNode);
    
    assertEquals(node.neighbors.size(), 1);
    assertEquals(node.neighbors.get(0), neighborNode);
    assertEquals(node.neighbors.get(0).val, val);
  }
}

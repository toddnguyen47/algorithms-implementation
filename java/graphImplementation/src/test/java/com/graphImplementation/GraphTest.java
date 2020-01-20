package com.graphImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {
  private Graph<Integer> graph;

  @BeforeEach
  public void setup() {
    graph = new Graph<Integer>();
  }

  @Test
  public void testEmptyNodeList() {
    assertEquals(0, graph.getNumberOfNodes());
  }

  @Test
  public void addOneNode() {
    graph.addNode(new Node<Integer>(5));
    assertEquals(1, graph.getNumberOfNodes());
  }

  @Test
  public void addDuplicateNode_SizeIsOneStill() {
    Node<Integer> node = new Node<Integer>(5);
    graph.addNode(node);
    graph.addNode(node);

    assertEquals(1, graph.getNumberOfNodes());
  }

  @Test
  public void addDuplicateNodeSameVal_SizeIsOneStill() {
    int val = 5;
    Node<Integer> n1 = new Node<Integer>(val);
    Node<Integer> n2 = new Node<Integer>(val);
    graph.addNode(n1);
    graph.addNode(n2);

    assertEquals(1, graph.getNumberOfNodes());
  }

  @Test
  public void getCertainNode() {
    Node<Integer> node = new Node<Integer>(5);
    graph.addNode(node);

    Iterator<Node<Integer>> iter = graph.iterateListOfNodes();
    while (iter.hasNext())
      assertEquals(node, iter.next());
  }

  @Test
  public void addTwoNodes_ShouldGetTwoNodesInSameOrder() {
    Node<Integer> node1 = new Node<Integer>(5);
    Node<Integer> node2 = new Node<Integer>(10);
    List<Node<Integer>> l1 = new ArrayList<>();
    l1.add(node1);
    l1.add(node2);

    graph.addNode(node1);
    graph.addNode(node2);

    Iterator<Node<Integer>> iter = graph.iterateListOfNodes();
    int index = 0;
    while (iter.hasNext())
      assertEquals(l1.get(index++), iter.next());
  }

  @Test
  public void getSpecificNodeBasedOnVal() {
    int val = 5;
    Node<Integer> node = new Node<> (val);
    graph.addNode(node);
    
    assertEquals(node, graph.getNode(val));
  }
}

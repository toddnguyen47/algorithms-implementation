package com.doublyLinkedList;

import java.util.ArrayList;
import java.util.Iterator;

public class DoublyLinkedList<T> {
  private class Node {
    public Node prev;
    public Node next;
    public T val;

    public Node(T _val) {
      val = _val;
    }
  }

  private int size;
  private Node dummyHead;
  private Node dummyTail;

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public T getFirst() {
    return this.dummyHead.next.val;
  }

  public T getLast() {
    return this.dummyTail.prev.val;
  }

  public Iterator<T> iterateHead() {
    return iterateElements(dummyHead);
  }

  public Iterator<T> iterateTail() {
    return iterateElements(dummyTail);
  }

  private Iterator<T> iterateElements(Node startingPtr) {
    ArrayList<T> iter = new ArrayList<>();
    while (startingPtr != null) {
      if (startingPtr != dummyHead && startingPtr != dummyTail)
        iter.add(startingPtr.val);
      startingPtr = startingPtr.next;
    }
    return iter.iterator();
  }

  public DoublyLinkedList() {
    this.size = 0;
    this.dummyHead = new Node(null);
    this.dummyTail = new Node(null);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  /** Add to the beginning of the Doubly Linked List. */
  public void addFirst(T val) {
    Node node = new Node(val);
    node.next = dummyHead.next;
    node.prev = dummyHead;

    dummyHead.next.prev = node;
    dummyHead.next = node;
    this.size += 1;
  }

  /** Add to the end of the Doubly Linked List. */
  public void addLast(T val) {
    Node node = new Node(val);
    node.prev = dummyTail.prev;
    node.next = dummyTail;

    dummyTail.prev.next = node;
    dummyTail.prev = node;
    this.size += 1;
  }

  private Node removeNode(Node n) {
    n.prev.next = n.next;
    n.next.prev = n.prev;
    n.next = null;
    n.prev = null;
    return n;
  }

  public T removeFirst() {
    T val = null;
    if (this.size > 0) {
      Node tempHead = this.removeNode(dummyHead.next);
      this.size -= 1;
      val = tempHead.val;
    }
    return val;
  }

  public T removeLast() {
    T val = null;
    if (this.size > 0) {
      Node tempTail = this.removeNode(dummyTail.prev);
      this.size -= 1;
      val = tempTail.val;
    }
    return val;
  }
}

package com.doublyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDoublyLinkedList {
  private DoublyLinkedList<Integer> dLinkedList;

  @BeforeEach
  public void setup() {
    dLinkedList = new DoublyLinkedList<>();
  }

  @Test
  public void addFirstWhenEmpty() {
    assertTrue(dLinkedList.isEmpty());
    int val = 2;
    dLinkedList.addFirst(val);
    assertFalse(dLinkedList.isEmpty());
    assertEquals(1, dLinkedList.size());

    Iterator<Integer> iter = dLinkedList.iterateHead();
    while (iter.hasNext()) {
      assertEquals(val, iter.next());
    }
  }

  @Test
  public void addWhenThereIsOneElement() {
    dLinkedList.addFirst(1);
    dLinkedList.addFirst(2);

    assertEquals(2, dLinkedList.getFirst());
    assertEquals(1, dLinkedList.getLast());
    assertEquals(2, dLinkedList.size());
  }

  @Test
  public void addFirstFiveElements() {
    int maxLen = 5;
    int inputs[] = new int[maxLen];
    for (int i = 0; i < maxLen; i++) {
      inputs[i] = i;
      dLinkedList.addFirst(i);
    }

    Iterator<Integer> iter = dLinkedList.iterateHead();
    int val = maxLen - 1;
    while (iter.hasNext())
      assertEquals(val--, iter.next());
  }

  @Test
  public void removeFirstWhenEmpty() {
    assertTrue(dLinkedList.isEmpty());
    assertNull(dLinkedList.removeFirst());
    assertEquals(0, dLinkedList.size());
  }

  @Test
  public void removeFirstWithOneElem() {
    dLinkedList.addFirst(5);
    dLinkedList.removeFirst();
    assertEquals(0, dLinkedList.size());
    Iterator<Integer> iter = dLinkedList.iterateHead();
    int count = 0;
    while (iter.hasNext()) {
      count += 1;
      iter.next();
    }
    assertEquals(0, count);
  }

  @Test
  public void addOneAtHeadThenRemoveFirst() {
    int expectedVal = 2;
    dLinkedList.addFirst(expectedVal);
    int val = dLinkedList.removeFirst();
    assertEquals(0, dLinkedList.size());
    assertTrue(dLinkedList.isEmpty());
    assertEquals(val, expectedVal);
  }

  @Test
  public void addOneAtTailWhenEmpty() {
    assertTrue(dLinkedList.isEmpty());
    dLinkedList.addLast(5);
    assertFalse(dLinkedList.isEmpty());
    assertEquals(1, dLinkedList.size());
    assertEquals(5, dLinkedList.getLast());
  }

  @Test
  public void addOneAtTailWhenThereIsAlreadyOneElem() {
    dLinkedList.addLast(5);
    assertFalse(dLinkedList.isEmpty());
    assertEquals(1, dLinkedList.size());

    dLinkedList.addLast(2);
    assertEquals(2, dLinkedList.size());
    assertEquals(2, dLinkedList.getLast());
    assertEquals(5, dLinkedList.getFirst());
  }

  @Test
  public void addFiveToTail() {
    int maxLen = 5;
    int inputs[] = new int[maxLen];
    for (int i = 0; i < maxLen; i++) {
      inputs[i] = i;
      dLinkedList.addLast(i);
    }

    Iterator<Integer> iter = dLinkedList.iterateTail();
    int val = maxLen - 1;
    while (iter.hasNext())
      assertEquals(val--, iter.next());
  }

  @Test
  public void removeTailWhenEmpty() {
    assertTrue(dLinkedList.isEmpty());
    assertNull(dLinkedList.removeLast());
    assertEquals(0, dLinkedList.size());
    assertTrue(dLinkedList.isEmpty());
  }

  @Test
  public void removeTailWithOneElem() {
    assertTrue(dLinkedList.isEmpty());
    dLinkedList.addLast(2);
    assertEquals(1, dLinkedList.size());
    assertEquals(2, dLinkedList.removeLast());
    assertEquals(0, dLinkedList.size());
    assertTrue(dLinkedList.isEmpty());
    assertNull(dLinkedList.getLast());
  }

  @Test
  public void removeTailWithTwoElems() {
    assertTrue(dLinkedList.isEmpty());
    dLinkedList.addLast(2);
    dLinkedList.addLast(5);
    assertEquals(2, dLinkedList.size());
    assertEquals(5, dLinkedList.getLast());
    assertEquals(5, dLinkedList.removeLast());
    assertEquals(1, dLinkedList.size());
  }

  @Test
  public void addHeadThenRemoveTail() {
    assertTrue(dLinkedList.isEmpty());
    int maxLen = 10;
    for (int i = 0; i < maxLen; i++) {
      dLinkedList.addFirst(i);
    }
    for (int i = 0; i < maxLen; i++) {
      int val = dLinkedList.removeLast();
      assertEquals(i, val);
    }
  }

  @Test
  public void addTailThenRemoveHead() {
    int maxLen = 10;
    for (int i = 0; i < maxLen; i++) {
      dLinkedList.addLast(i);
    }

    for (int i = 0; i < maxLen; i++) {
      int val = dLinkedList.removeFirst();
      assertEquals(i, val);
    }
  }
}

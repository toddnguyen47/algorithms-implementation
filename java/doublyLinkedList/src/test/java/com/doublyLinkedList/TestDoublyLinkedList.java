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
    int val = 2;
    dLinkedList.addFirst(val);
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
}

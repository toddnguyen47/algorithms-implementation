package com.mergeSort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMergeSortBottomUp {
  private MergeSort mergeSort;

  @BeforeEach
  public void setup() {
    mergeSort = new BottomUpMergeSort();
  }

  @Test
  public void sortNothing() {
    int input[] = new int[] {};
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] {}, output);

    output = mergeSort.sort(null);
    assertArrayEquals(null, output);
  }

  @Test
  public void sortOneElem() {
    int input[] = new int[] { 1 };
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] { 1 }, output);
  }

  @Test
  public void sortTwoElemsUnsorted() {
    int input[] = new int[] { 2, 1 };
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] { 1, 2 }, output);
  }

  @Test
  public void sortTwoElemsSortedAlready() {
    int input[] = new int[] { 1, 2 };
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] { 1, 2 }, output);
  }

  @Test
  public void sortFiveElems() {
    int input[] = new int[] { 10, 2, 6, 7, 3 };
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] { 2, 3, 6, 7, 10 }, output);
  }
}

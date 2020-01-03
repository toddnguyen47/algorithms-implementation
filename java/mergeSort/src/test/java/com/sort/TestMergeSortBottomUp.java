package com.sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sort.BottomUpMergeSort;
import com.sort.SortingAlgos;

import java.util.Random;
import java.util.Arrays;

public class TestMergeSortBottomUp {
  private SortingAlgos mergeSort;

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

  @Test
  public void sortSixElems() {
    int input[] = new int[] { 10, 2, 6, 7, 3, 8 };
    int output[] = mergeSort.sort(input);
    assertArrayEquals(new int[] { 2, 3, 6, 7, 8, 10 }, output);
  }

  @Test
  public void sortTwentyRandomElems() {
    int len = 20;
    int input[] = new int[len];
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 0; i < len; i++) {
      input[i] = rand.nextInt(100);
    }

    int a[] = Arrays.copyOf(input, len);
    Arrays.sort(a);
    assertArrayEquals(a, mergeSort.sort(input));
  }

  @Test
  public void sortOneHundredRandomElems() {
    int len = 100;
    int input[] = new int[len];
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 0; i < len; i++) {
      input[i] = rand.nextInt(100);
    }

    int a[] = Arrays.copyOf(input, len);
    Arrays.sort(a);
    assertArrayEquals(a, mergeSort.sort(input));
  }

  @Test
  public void sortOneMilSortedRandomElems() {
    int len = 1000000;
    int[] input = generateRandomInputArray(len);

    int a[] = Arrays.copyOf(input, len);
    Arrays.sort(a);
    assertArrayEquals(a, mergeSort.sort(a));
  }

  private int[] generateRandomInputArray(int len) {
    int input[] = new int[len];
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 0; i < len; i++) {
      input[i] = rand.nextInt(len << 1);
    }
    return input;
  }
}

package com.sort;

import com.sort.mergesort.MergeSortBottomUp;
import com.sort.mergesort.MergeSortTopDown;

public class SortingFactory {
  private static BubbleSort bubbleSort = null;
  private static BubbleSortImproved bubbleSortImproved = null;
  private static Quicksort quicksort = null;
  private static MergeSortBottomUp mergeSortBottomUp = null;
  private static MergeSortTopDown mergeSortTopDown = null;

  public static BubbleSort createBubbleSort() {
    if (bubbleSort == null) {
      bubbleSort = new BubbleSort();
    }
    return bubbleSort;
  }

  public static BubbleSortImproved createBubbleSortImproved() {
    if (bubbleSortImproved == null) {
      bubbleSortImproved = new BubbleSortImproved();
    }
    return bubbleSortImproved;
  }

  public static Quicksort createQuicksort() {
    if (quicksort == null) {
      quicksort = new Quicksort();
    }
    return quicksort;
  }

  public static MergeSortBottomUp createMergeSortBottomUp() {
    if (mergeSortBottomUp == null) {
      mergeSortBottomUp = new MergeSortBottomUp();
    }
    return mergeSortBottomUp;
  }

  public static MergeSortTopDown createMergeSortTopDown() {
    if (mergeSortTopDown == null) {
      mergeSortTopDown = new MergeSortTopDown();
    }
    return mergeSortTopDown;
  }
}

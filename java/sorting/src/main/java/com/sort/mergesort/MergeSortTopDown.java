package com.sort.mergesort;

import com.sort.SortingAlgos;

public class MergeSortTopDown implements SortingAlgos {

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length == 0) {
      return input;
    }
    int[] helperArray = new int[input.length];
    mergeSort(input, helperArray, 0, input.length - 1);
    return input;
  }

  /**
   * We need to pass in a helper array, otherwise we'll make a new copy of helper
   * array each time and it WILL overflow the stack
   * 
   * @param input
   * @param helper
   * @param lo
   * @param hi
   */
  private void mergeSort(int[] input, int[] helper, final int lo, final int hi) {
    if (lo < hi) {
      final int size = hi - lo;
      final int mi = lo + (size / 2);
      // sort left and right side
      mergeSort(input, helper, lo, mi);
      mergeSort(input, helper, mi + 1, hi);
      // merge the two halves
      merge(input, helper, lo, mi, hi);
    }
  }

  private void merge(int[] input, int[] helper, final int lo, final int mi, final int hi) {
    int leftPtr = lo;
    int rightPtr = mi + 1;

    // Copy current input into helper array. Keep array size the same length as
    // input for simplicity
    for (int i = lo; i <= hi; i++) {
      helper[i] = input[i];
    }

    int currentIndex = lo;
    while (leftPtr <= mi && rightPtr <= hi) {
      if (helper[leftPtr] <= helper[rightPtr]) {
        input[currentIndex] = helper[leftPtr];
        leftPtr += 1;
      } else {
        input[currentIndex] = helper[rightPtr];
        rightPtr += 1;
      }
      currentIndex += 1;
    }

    // Let's say we have [4, 21, 43] and [34, 56]
    // We only have to add in the left side as the right side (56 in this case) is
    // already present
    while (leftPtr <= mi) {
      input[currentIndex] = helper[leftPtr];
      leftPtr += 1;
      currentIndex += 1;
    }
  }
}

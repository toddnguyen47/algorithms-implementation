package com.sort.mergesort;

import com.sort.SortingAlgos;

/**
 * Bottom up merge sort implementation.
 * 
 * Ref: https://algs4.cs.princeton.edu/22mergesort/
 * 
 * @author Todd Nguyen
 *
 */
public class MergeSortBottomUp implements SortingAlgos {

  @Override
  public int[] sort(int[] input) {
    if (input == null) {
      return input;
    }

    int inputLen = input.length;
    int helperArray[] = new int[inputLen];
    // Iterate through sizes, increasing by a power of 2 each time. O(lg n)
    // We will stop the loop when half of the size is larger than the whole
    // input size, e.g. that means the array should already be sorted
    for (int size = 1; size / 2 < inputLen; size *= 2) {
      // Iterate through each element and sort the smaller array
      // O(n)
      for (int lo = 0; lo < inputLen; lo += size) {
        int hi = Math.min(inputLen - 1, lo + size - 1);
        // mi has to be calculated using the size, NOT hi. Otherwise some halves
        // will not be counted
        int mi = Math.min(inputLen - 1, lo + (size / 2) - 1);
        // Merge the two halves
        merge(input, helperArray, lo, mi, hi);
      }
    }

    return input;
  }

  private void merge(int[] input, int[] helperArray, int lo, int mi, int hi) {
    // Copy into helperArray
    for (int i = lo; i <= hi; i++) {
      helperArray[i] = input[i];
    }

    int leftPtr = lo;
    int rightPtr = mi + 1;

    int currentIndex = lo;
    while (leftPtr <= mi && rightPtr <= hi) {
      if (helperArray[leftPtr] <= helperArray[rightPtr]) {
        input[currentIndex] = helperArray[leftPtr];
        leftPtr += 1;
      } else {
        input[currentIndex] = helperArray[rightPtr];
        rightPtr += 1;
      }
      currentIndex += 1;
    }

    // Let's say we have [4, 21, 43] and [34, 56]
    // We only have to add in the left side as the right side (56 in this case) is
    // already present
    while (leftPtr <= mi) {
      input[currentIndex] = helperArray[leftPtr];
      currentIndex += 1;
      leftPtr += 1;
    }
  }
}

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

    int nLen = input.length;
    int temp[] = new int[nLen];

    // halfWidth in this case is the halfWidth of the array that WILL be merged
    // e.g. if we are merging a[] and b[], halfWidth is the len() of a[] and b[]
    for (int halfWidth = 1; halfWidth < nLen; halfWidth *= 2) {
      int width = halfWidth * 2;
      for (int lo = 0; lo < nLen; lo += width) {
        int hi = Math.min(lo + width - 1, nLen - 1);
        // We cannot use Math.floor((lo + hi) / 2) as that would assume that
        // the width is (hi - lo), while in fact the width is halfWidth * 2
        // e.g. if there are 20 elements, on the last merge,
        // lo: 0, mid: 15, hi: 19 with (mid = lo + halfWidth - 1)
        // If we use Math.floor((lo + hi) / 2), then
        // lo: 0, mid: 9, hi: 19
        // which will NOT work since our left and right indices are NOT properly aligned
        int mid = lo + halfWidth - 1;
        input = this.merge(input, temp, lo, mid, hi);
      }
    }

    return input;
  }

  private int[] merge(int[] input, int[] temp, int lo, int mid, int hi) {
//    System.out.println(String.format("%d, %d, %d", lo, mid, hi));
    // Copy the section to temp first
    for (int i = lo; i <= hi; i++) {
      temp[i] = input[i];
    }

    int left = lo;
    int right = mid + 1;

    // Merge back to input
    for (int i = lo; i <= hi; i++) {
      // If at the end, copy the rest back into input
      if (left > mid) {
        input[i] = temp[right];
        right += 1;
      } else if (right > hi) {
        input[i] = temp[left];
        left += 1;
      }
      // We aren't at the end yet, compare and obtain the
      // element that's smallest
      else if (temp[left] < temp[right]) {
        input[i] = temp[left];
        left += 1;
      } else {
        input[i] = temp[right];
        right += 1;
      }
    }

    return input;
  }
}

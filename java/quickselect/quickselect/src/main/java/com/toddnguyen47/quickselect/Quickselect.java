package com.toddnguyen47.quickselect;

public class Quickselect {
  public int findKthSmallestElement(int[] input, int k) {
    return this.findKthSmallestElementHelper(input, k, 0, input.length - 1);
  }

  private int findKthSmallestElementHelper(int[] input, int k, int lo, int hi) {
    // If there is only one element
    if (lo == hi)
      // Return that element
      return input[lo];

    int pivotIndex = this.hoarePartition(input, k, lo, hi);
    // We found it!
    if (pivotIndex == k)
      return input[pivotIndex];
    // Else, if pivotIndex > k, search LEFT
    else if (pivotIndex > k)
      return this.findKthSmallestElementHelper(input, k, lo, pivotIndex);
    // Search right
    else
      return this.findKthSmallestElementHelper(input, k, pivotIndex + 1, hi);
  }

  private int hoarePartition(int[] input, int k, int lo, int hi) {
    int pivotValue = input[(lo + hi) >> 1];
    int pivotIndex = lo;

    while (true) {
      while (input[lo] < pivotValue)
        lo += 1;
      while (input[hi] > pivotValue)
        hi -= 1;
      if (lo >= hi) {
        pivotIndex = hi;
        break;
      }

      this.swap(input, lo, hi);
      lo += 1;
      hi -= 1;
    }

    return pivotIndex;
  }

  private void swap(int[] input, int indexA, int indexB) {
    int temp = input[indexA];
    input[indexA] = input[indexB];
    input[indexB] = temp;
  }
}

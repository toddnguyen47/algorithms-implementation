package com.sort;

public class Quicksort implements SortingAlgos {

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length < 2)
      return input;

    this.sortRecursively(input, 0, input.length - 1);
    return input;
  }

  private void sortRecursively(int[] input, int lo, int hi) {
    // Base case: if size of "partition" is 0 or 1
    if ((hi - lo) < 1)
      return;

    input = moveMedianPivotToLastPos(input, lo, hi);
    int pivotIndex = this.lomutoPartition(input, lo, hi);

    // Pivot is in the correct index!
    // Sort left and right subarray
    this.sortRecursively(input, lo, pivotIndex - 1);
    this.sortRecursively(input, pivotIndex + 1, hi);
  }

  private int lomutoPartition(int[] input, int lo, int hi) {
    int pivotIndex = hi;
    int left = lo;

    while (pivotIndex > left) {
      // Move left index until the element is greater than the element of the
      // pivotIndex
      while (input[left] < input[pivotIndex])
        left++;

      // Found something! We are going to do 2 swaps while decrementing the pivotIndex
      if (pivotIndex > left) {
        input = this.swap(input, pivotIndex, left);
        pivotIndex -= 1;
        input = this.swap(input, pivotIndex, left);
      }
    }

    return pivotIndex;
  }

  /** Ref: https://en.wikipedia.org/wiki/Quicksort#Implementation_issues */
  private int[] moveMedianPivotToLastPos(int[] input, int lo, int hi) {
    int middleIndex = lo + ((hi - lo) >> 1);
    if (input[middleIndex] < input[lo])
      input = swap(input, middleIndex, lo);
    if (input[hi] < input[lo])
      input = swap(input, hi, lo);
    if (input[middleIndex] < input[hi])
      input = swap(input, middleIndex, hi);

    return input;
  }

  private int[] swap(int[] input, int indexA, int indexB) {
    int temp = input[indexA];
    input[indexA] = input[indexB];
    input[indexB] = temp;
    return input;
  }
}

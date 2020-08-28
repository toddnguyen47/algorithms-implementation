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

    this.moveMedianPivotToLastPos(input, lo, hi);
    int pivotIndex = this.hoarePartition(input, lo, hi);

    // Pivot is in the correct index!
    // Sort left and right subarray
    this.sortRecursively(input, lo, pivotIndex);
    this.sortRecursively(input, pivotIndex + 1, hi);
  }

  /**
   * Ref: https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
   * 
   * @param input
   * @param lo
   * @param hi
   * @return
   */
  private int hoarePartition(int[] input, int lo, int hi) {
    int pivotIndex = (lo + hi) >> 1;
    int pivotValue = input[pivotIndex];
    int left = lo;
    int right = hi;
    while (true) {
      while (input[left] < pivotValue)
        left += 1;
      while (input[right] > pivotValue)
        right -= 1;
      // Everything is in order, return the new pivot index.
      // At this pivot index, everything to the left is smaller than input[pivotIndex]
      // Everything to the right is larger than input[pivotIndex]
      if (left >= right) {
        pivotIndex = right;
        break;
      }

      // WRONG positions!
      // elem at `left` index is larger than input[pivotIndex]
      // elem at `right` index is smaller than input[pivotIndex]
      this.swap(input, left, right);
      left += 1;
      right -= 1;
    }

    return pivotIndex;
  }

  /**
   * Reference: https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
   * 
   * @param input Input array
   * @param lo
   * @param hi
   * @return pivotIndex
   */
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
        this.swap(input, pivotIndex, left);
        pivotIndex -= 1;
        this.swap(input, pivotIndex, left);
      }
    }

    return pivotIndex;
  }

  /** Reference: https://en.wikipedia.org/wiki/Quicksort#Implementation_issues */
  private void moveMedianPivotToLastPos(int[] input, int lo, int hi) {
    int middleIndex = lo + ((hi - lo) >> 1);
    if (input[middleIndex] < input[lo])
      this.swap(input, middleIndex, lo);
    if (input[hi] < input[lo])
      this.swap(input, hi, lo);
    if (input[middleIndex] < input[hi])
      this.swap(input, middleIndex, hi);
  }

  private void swap(int[] input, int indexA, int indexB) {
    int temp = input[indexA];
    input[indexA] = input[indexB];
    input[indexB] = temp;
  }
}

package com.sort.partition;

import com.sort.Swap;

public class HoarePartition implements Partition {
  private Swap swapObj_;

  public HoarePartition(Swap swap) {
    this.swapObj_ = swap;
  }

  @Override
  public int partition(int[] input, int lo, int hi) {
    return this.partitionHelper(input, lo, hi);
  }

  private int partitionHelper(int[] input, int lo, int hi) {
    int pivotIndex = (lo + hi) >> 1;
    int pivotValue = input[pivotIndex];
    int left = lo - 1;
    int right = hi + 1;

    while (true) {
      left += 1;
      // Iterate left until we find a value that's greater than pivot
      while (input[left] < pivotValue)
        left += 1;

      right -= 1;
      // Iterate right until we find a value that's smaller than pivot
      while (input[right] > pivotValue)
        right -= 1;

      // Left and right crossed each other! break out
      if (left >= right) {
        pivotIndex = right;
        break;
      }

      // WRONG positions!
      // elem at `left` index is larger than input[pivotIndex]
      // elem at `right` index is smaller than input[pivotIndex]
      this.swapObj_.swap(input, left, right);
    }

    return pivotIndex;
  }
}

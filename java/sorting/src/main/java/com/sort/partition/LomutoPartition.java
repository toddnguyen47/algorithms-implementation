package com.sort.partition;

import com.sort.Swap;

public class LomutoPartition implements Partition {
  private Swap swapObj_;

  public LomutoPartition(Swap swap) {
    this.swapObj_ = swap;
  }

  @Override
  public int partition(int[] input, int lo, int hi) {
    this.moveMedianPivotToLastPosition(input, lo, hi);
    int pivotValue = input[hi];
    int desiredPivotIndex = lo;

    // Elements [lo, desiredPivotIndex - 1] are < than pivot
    // Elements [desiredPivotIndex, j] are >= pivot
    for (int j = lo; j <= hi; j++) {
      if (input[j] < pivotValue) {
        this.swapObj_.swap(input, desiredPivotIndex, j);
        desiredPivotIndex += 1;
      }
    }
    // Now desiredPivotIndex is where the pivot should be
    this.swapObj_.swap(input, desiredPivotIndex, hi);

    return desiredPivotIndex;
  }

  // **************************************************************************
  // | PRIVATE FUNCTIONS
  // **************************************************************************

  private void moveMedianPivotToLastPosition(int[] input, int lo, int hi) {
    int mid = (lo + hi) >> 1;
    // Move lowest element to the lo index
    if (input[mid] < input[lo])
      this.swapObj_.swap(input, mid, lo);
    if (input[hi] < input[lo])
      this.swapObj_.swap(input, hi, lo);
    // Now smallest element is in [lo]. We must now put the largest element in [mid]
    if (input[hi] > input[mid])
      this.swapObj_.swap(input, hi, mid);
    // This guarantees that the median of the three elements is in the [hi] position
  }

}

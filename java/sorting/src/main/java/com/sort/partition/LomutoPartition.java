package com.sort.partition;

import com.sort.Swap;

public class LomutoPartition implements Partition {
  private Swap swapObj;

  public LomutoPartition(Swap swap) {
    this.swapObj = swap;
  }

  @Override
  public int partition(int[] input, int lo, int hi) {
    moveMedianPivotToLastPosition(input, lo, hi);
    int desiredIndex = lo;
    int pivotValue = input[hi];

    for (int i = lo; i < hi; i++) {
      if (input[i] < pivotValue) {
        swapObj.swap(input, i, desiredIndex);
        desiredIndex += 1;
      }
    }

    // Now move the pivot to its desired location
    swapObj.swap(input, desiredIndex, hi);

    return desiredIndex;
  }

  // **************************************************************************
  // | PRIVATE FUNCTIONS
  // **************************************************************************

  private void moveMedianPivotToLastPosition(int[] input, int lo, int hi) {
    if (lo < hi) {
      int mi = lo + ((hi - lo) / 2);
      // Move lowest number to lo
      if (input[mi] < input[lo]) {
        swapObj.swap(input, mi, lo);
      }
      if (input[hi] < input[lo]) {
        swapObj.swap(input, hi, lo);
      }
      // Move highest to mi now. That way, median should be at hi
      if (input[hi] > input[mi]) {
        swapObj.swap(input, hi, mi);
      }
    }
  }

}

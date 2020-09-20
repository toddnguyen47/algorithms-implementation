package com.sort;

import com.sort.partition.Partition;
import com.sort.partition.PartitionFactory;

public class Quicksort implements SortingAlgos {

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length < 2)
      return input;

//    this.sortRecursively(input, 0, input.length - 1, PartitionFactory.createLomutoPartition());
    this.sortRecursively(input, 0, input.length - 1, PartitionFactory.createHoarePartition());
    return input;
  }

  private void sortRecursively(int[] input, int lo, int hi, Partition partitionObj) {
    // Base case: if size of "partition" is 0 or 1
    if ((hi - lo) < 1)
      return;

    int pivotIndex = partitionObj.partition(input, lo, hi);

    // Pivot is in the correct index!
    // Sort left and right subarray
    this.sortRecursively(input, lo, pivotIndex, partitionObj);
    this.sortRecursively(input, pivotIndex + 1, hi, partitionObj);
  }
}

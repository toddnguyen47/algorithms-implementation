package com.sort;

import com.sort.partition.Partition;

public class Quicksort implements SortingAlgos {
  private Partition partition;

  public Quicksort(Partition partition) {
    this.partition = partition;
  }

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length < 2) {
      return input;
    }

    sortRecursively(input, 0, input.length - 1);

    return input;
  }

  private void sortRecursively(int[] input, int lo, int hi) {
    if (lo > hi) {
      return;
    }
    // Partition and get the index of pivot
    int index = partition.partition(input, lo, hi);
    // Sort left and right
    sortRecursively(input, lo, index - 1);
    sortRecursively(input, index + 1, hi);
  }
}

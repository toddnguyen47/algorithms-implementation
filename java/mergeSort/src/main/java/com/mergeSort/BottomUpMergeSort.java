package com.mergeSort;

/**
 * Try to do this in-place.
 */
public class BottomUpMergeSort implements MergeSort {

  @Override
  public int[] sort(int[] input) {
    if (input == null)
      return null;

    // For each width, starting from 1 to the whole array.
    // Double the width each time. O(lg n)
    for (int width = 1, inputLen = input.length; width < inputLen; width = width * 2) {
      // For each subarray, continuing until you reach the array's end
      for (int j = 0; j < inputLen; j = j + 2 * width) {
        int left = j;
        int middle = j + width;
        int right = j + (2 * width);

        input = merge(input, left, middle, right);
      }
    }

    return input;
  }

  private int[] merge(int[] input, int left, int middle, int right) {
    // nl = new left, nr = new right
    int nl = left;
    int nr = middle;

    // Merge!
    while (nl < middle || nr < right) {
      // Both sides have elements left
      if (nl < middle && nr < right) {
        if (input[nl] > input[nr]) {
          input = this.swap(input, nl, nr);
          nl += 1;
        } else
          nr += 1;
      }
      // Iterate left
      else if (nr >= right)
        nl += 1;
      // Iterate right
      else if (nl >= middle)
        nr += 1;
      else
        throw new RuntimeException("Should not reach this block");
    }

    return input;
  }

  @Override
  public int[] swap(int[] input, int indexA, int indexB) {
    int temp = input[indexA];
    input[indexA] = input[indexB];
    input[indexB] = temp;
    return input;
  }
}

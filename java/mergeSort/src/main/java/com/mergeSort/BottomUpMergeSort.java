package com.mergeSort;

/**
 * Try to do this in-place.
 */
public class BottomUpMergeSort implements MergeSort {

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length < 2)
      return input;

    int l = 0;
    int r = input.length;

    // nl = new left; nm = new middle; nr = new right
    for (int blocksize = 1; blocksize < r; blocksize = blocksize << 1) {
      int width = blocksize << 1;
      for (int nl = l; nl < r; nl = nl + width) {
        int nm = Math.min(r, nl + blocksize);
        int nr = Math.min(r, nl + width);
        input = merge(input, nl, nm, nr);
      }
    }

    return input;
  }

  private int[] merge(int[] input, int left, int middle, int right) {
    int nl = left;
    int nr = middle;
    int output[] = new int[right - left];
    int index = 0;

    while (nl < middle && nr < right) {
      if (input[nl] < input[nr])
        output[index++] = input[nl++];
      else
        output[index++] = input[nr++];
    }

    // Add the rest in
    while (nl < middle)
      output[index++] = input[nl++];
    while (nr < right)
      output[index++] = input[nr++];

    // Copy it back into input
    nl = left;
    for (int i = 0; i < index; i++)
      input[nl++] = output[i];

    return input;
  }
}

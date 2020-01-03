package com.mergeSort;

/**
 * Try to do this in-place.
 */
public class BottomUpMergeSort implements MergeSort {

  @Override
  public int[] sort(int[] input) {
    if (input.length < 2)
      return input;

    int l = 0;
    int r = input.length;

    // nl = new left; nm = new middle; nr = new right
    for (int blocksize = 1; blocksize < r; blocksize = blocksize << 1) {
      int width = blocksize << 1;
      for (int nl = l; nl < r; nl = nl + width) {
        int nm = nl + blocksize;
        int nr = Math.min(r, nl + width);
        input = merge(input, nl, nm, nr);
      }
    }

    return input;
  }

  private int[] merge(int[] input, int left, int middle, int right) {
    int nl = left;
    int nr = middle;
    int a[] = new int[right - left];
    int index = 0;

    while (nl < middle && nr < right) {
      if (a[nl] < a[nr])
        a[index++] = input[nl++];
      else
        a[index++] = input[nr++];
    }

    // Add the rest in
    while (nl < middle)
      a[index++] = input[nl++];
    while (nr < right)
      a[index++] = input[nr++];

    // Copy it back into input
    nl = left;
    for (int i = 0; i < index; i++)
      input[nl++] = a[i];

    return input;
  }
}

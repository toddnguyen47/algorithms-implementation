package com.sort;

public class BubbleSort implements SortingAlgos {

  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length == 0)
      return input;

    while (true) {
      boolean swapped = false;
      int length = input.length;
      for (int i = 1; i < length; i++) {
        if (input[i] < input[i - 1]) {
          input = swap(input, i, i - 1);
          swapped = true;
        }
      }
      // Finish sorting, so no swapping happened
      if (!swapped)
        break;
    }
    return input;
  }

  private int[] swap(int[] input, int indexA, int indexB) {
    int temp = input[indexA];
    input[indexA] = input[indexB];
    input[indexB] = temp;
    return input;
  }
}

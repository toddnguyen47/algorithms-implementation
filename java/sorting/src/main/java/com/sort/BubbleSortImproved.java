package com.sort;

public class BubbleSortImproved extends BubbleSort {
  /**
   * After every pass, all elements after the last swap are sorted and do not need
   * to be checked again
   * 
   * @param input The array of integers to sort
   * @return A sorted array
   */
  @Override
  public int[] sort(int[] input) {
    if (input == null || input.length < 2)
      return input;
    int len = input.length;
    while (len > 1) {
      int newn = 0;
      for (int i = 1; i < len; i++) {
        if (input[i - 1] > input[i]) {
          input = swap(input, i - 1, i);
          newn = i;
        }
      }
      len = newn;
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

package com.sort;

public class Swap {
  public void swap(int[] input, int indexA, int indexB) {
    if (indexA != indexB) {
      // int temp = input[indexA];
      // input[indexA] = input[indexB];
      // input[indexB] = temp;
      // Swap in place
      // First, find the difference
      input[indexA] = input[indexA] ^ input[indexB];
      // Switch indexB with indexA
      input[indexB] = input[indexB] ^ input[indexA];
      // Switch indexA with indexB
      input[indexA] = input[indexA] ^ input[indexB];
    }
  }
}

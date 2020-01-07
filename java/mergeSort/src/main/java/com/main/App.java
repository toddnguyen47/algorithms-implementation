package com.main;

import java.util.Random;

import com.sort.BottomUpMergeSort;
import com.sort.BubbleSort;
import com.sort.Quicksort;
import com.sort.SortingAlgos;

public class App {
  public static void main(String[] args) {
    App app = new App();
    app.execute();

    System.out.println("Finished!");
  }

  private Random rand = null;

  public App() {
    rand = new Random(System.currentTimeMillis());
  }

  public void execute() {
//    int len = (1 << 24) + ((1 << 24) - 1);
    int len = 100000;
    int randomInput[] = generateRandomInputArray(len);
    System.out.println("Sorting " + len + " elements");
    executeSortingRandomly(randomInput);
  }

  private void executeSortingRandomly(int[] randomInput) {
    int[] randomOrder = shuffleDurstenfeld(new int[] { 0, 1, 2 });
    for (int i : randomOrder) {
      switch (i) {
      case 0:
        executeBubbleSort(randomInput.clone());
        break;
      case 1:
        executeMergeSort(randomInput.clone());
        break;
      case 2:
        executeQuickSort(randomInput.clone());
        break;
      }
    }
  }

  private void executeBubbleSort(int[] input) {
    System.out.print("Using Bubble Sort | ");
    executeSorting(new BubbleSort(), input);
  }

  private void executeMergeSort(int[] input) {
    System.out.print("Using Merge Sort | ");
    executeSorting(new BottomUpMergeSort(), input);
  }

  private void executeQuickSort(int[] input) {
    System.out.print("Using Quicksort | ");
    executeSorting(new Quicksort(), input);
  }

  private void executeSorting(SortingAlgos sortingAlgo, int[] input) {
    long startTime = System.currentTimeMillis();
    sortingAlgo.sort(input);
    long endTime = System.currentTimeMillis();
    System.out.println("Time Taken: " + (endTime - startTime) + " ms");
  }

  private int[] generateRandomInputArray(int len) {
    int input[] = new int[len];
    for (int i = 0; i < len; i++) {
      input[i] = rand.nextInt(Math.max(100, len >> 1));
    }
    return input;
  }

  /**
   * Ref:
   * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
   * 
   * @param input
   * @return
   */
  private int[] shuffleDurstenfeld(int[] input) {
    for (int i = input.length - 1; i >= 1; i--) {
      int randIndex = rand.nextInt(i + 1);
      // Swap
      int t = input[i];
      input[i] = input[randIndex];
      input[randIndex] = t;
    }

    return input;
  }
}

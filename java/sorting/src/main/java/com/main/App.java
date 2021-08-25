package com.main;

import java.util.Arrays;
import java.util.Random;

import com.sort.SortingAlgos;
import com.sort.SortingFactory;

public class App {
  public static void main(String[] args) {
    App app = new App();
    try {
      app.execute();
    } catch (NoEnumCaseException e) {
      e.printStackTrace();
    }

    System.out.println("Finished!");
  }

  private Random rand = null;

  private enum Algorithm {
    BUBBLE_SORT, MERGE_SORT_BOTTOM_UP, MERGE_SORT_TOP_DOWN, QUICKSORT
  };

  private class NoEnumCaseException extends Exception {
    private static final long serialVersionUID = -5838819035646043590L;
  }

  public App() {
    rand = new Random(System.currentTimeMillis());
  }

  public void execute() throws NoEnumCaseException {
//    int len = (1 << 24) + ((1 << 24) - 1);
    int len = 100_000;
    int randomInput[] = generateRandomInputArray(len);
    System.out.println("Sorting " + len + " elements");
    executeSortingRandomly(randomInput);
  }

  private void executeSortingRandomly(int[] randomInput) throws NoEnumCaseException {
    int[] randomOrder = shuffleDurstenfeld(new int[] { 0, 1, 2, 3 });
    for (int i : randomOrder) {
      Algorithm algo = Algorithm.values()[i];

      switch (algo) {
      case BUBBLE_SORT:
        executeBubbleSort(randomInput.clone());
        break;
      case MERGE_SORT_BOTTOM_UP:
        executeMergeSortBottomUp(randomInput.clone());
        break;
      case MERGE_SORT_TOP_DOWN:
        executeMergeSortTopDown(randomInput.clone());
        break;
      case QUICKSORT:
        executeQuickSort(randomInput.clone());
        break;
      default:
        throw new NoEnumCaseException();
      }
    }
  }

  private void executeBubbleSort(int[] input) {
    System.out.print("Using Bubble Sort | ");
    executeSorting(SortingFactory.createBubbleSortImproved(), input);
  }

  private void executeMergeSortBottomUp(int[] input) {
    System.out.print("Using Merge Sort Bottom Up | ");
    executeSorting(SortingFactory.createMergeSortBottomUp(), input);
  }

  private void executeMergeSortTopDown(int[] input) {
    System.out.print("Using Merge Sort Top Down | ");
    executeSorting(SortingFactory.createMergeSortTopDown(), input);
  }

  private void executeQuickSort(int[] input) {
    System.out.print("Using Quicksort | ");
    executeSorting(SortingFactory.createQuicksort(), input);
  }

  private void executeSorting(SortingAlgos sortingAlgo, int[] input) {
    long startTime = System.currentTimeMillis();
    int[] output = sortingAlgo.sort(input);
    long endTime = System.currentTimeMillis();
    Arrays.sort(input);
    for (int i = 0, i2 = output.length; i < i2; i++)
      assert (input[i] == output[i]);
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

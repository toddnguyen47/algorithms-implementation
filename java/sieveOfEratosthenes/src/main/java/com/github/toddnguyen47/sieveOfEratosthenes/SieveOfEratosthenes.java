package com.github.toddnguyen47.sieveOfEratosthenes;

import java.util.LinkedList;

public class SieveOfEratosthenes {
  private int minNum = 2;
  private LinkedList<Integer> nums;

  public SieveOfEratosthenes() {
    this.nums = new LinkedList<>();
  }

  public LinkedList<Integer> getPrimes(int max) {
    this.initializeLinkedList(max);
    return this.nums;
  }

  private void initializeLinkedList(int max) {
    this.nums.addLast(minNum);
    for (int i = minNum + 1; i <= max; i = i + 2) {
      this.nums.addLast(i);
    }
  }

  private void removePrimeMultiples(int currentNumber) {
    for (int i = 1; i < this.nums.size(); i++) {
      if (this.nums.get(i) % currentNumber == 0)
        this.nums.remove(i);
    }
  }
}

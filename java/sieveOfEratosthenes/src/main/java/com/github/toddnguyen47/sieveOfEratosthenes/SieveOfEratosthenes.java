package com.github.toddnguyen47.sieveOfEratosthenes;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes {
  class SieveNumber {
    int value;
    boolean disabled;

    public SieveNumber(int val) {
      this.value = val;
      this.disabled = false;
    }
  }

  private int minNum = 2;
  private List<SieveNumber> nums;

  public SieveOfEratosthenes() {
    this.nums = new ArrayList<>();
  }

  public List<Integer> getPrimes(int max) {
    this.initializeList(max);
    for (int i = this.minNum; i <= max; i++)
      removePrimeMultiples(i);
    return obtainEnabledNumbers();
  }

  private List<Integer> obtainEnabledNumbers() {
    List<Integer> list = new ArrayList<>();
    for (SieveNumber num : this.nums) {
      if (!num.disabled)
        list.add(num.value);
    }
    return list;
  }

  private void initializeList(int max) {
    this.nums.add(new SieveNumber(minNum));
    for (int i = minNum + 1; i <= max; i = i + 2) {
      this.nums.add(new SieveNumber(i));
    }
  }

  private void removePrimeMultiples(int currentIndex) {
    for (int i = currentIndex + 1; i < this.nums.size(); i++) {
      SieveNumber curNum = this.nums.get(i);
      if (curNum.value % currentIndex == 0)
        curNum.disabled = true;
    }
  }
}

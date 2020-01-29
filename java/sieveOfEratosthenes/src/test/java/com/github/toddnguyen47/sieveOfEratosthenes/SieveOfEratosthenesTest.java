package com.github.toddnguyen47.sieveOfEratosthenes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SieveOfEratosthenesTest {
  private SieveOfEratosthenes sieve;

  private Integer[] getArrOfPrimes(int max) {
    List<Integer> listOfPrimes = this.sieve.getPrimes(max);
    Integer[] arrOfPrimes = listOfPrimes.stream().toArray(Integer[]::new);
    return arrOfPrimes;
  }

  @BeforeEach
  public void setup() {
    this.sieve = new SieveOfEratosthenes();
  }

  @Test
  public void getListOfPrimes_MaxTwo() {
    Integer[] arrOfPrimes = getArrOfPrimes(2);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2 });
  }

  @Test
  public void getListOfPrimes_MaxFour_ReturnTwoAndThree() {
    Integer[] arrOfPrimes = getArrOfPrimes(4);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2, 3 });
  }

  @Test
  public void getListOfPrimes_MaxSix_ReturnTwoThreeAndFive() {
    Integer[] arrOfPrimes = getArrOfPrimes(6);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2, 3, 5 });
  }

  @Test
  public void getListOfPrimes_MaxNine_ReturnTwoThreeFiveSeven() {
    Integer[] arrOfPrimes = getArrOfPrimes(9);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2, 3, 5, 7 });
  }

  @Test
  public void max21_returnEightNumbers() {
    Integer[] arrOfPrimes = getArrOfPrimes(21);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19 });
  }

  @Test
  public void max30_returnTenNumbers() {
    Integer[] arrOfPrimes = getArrOfPrimes(30);
    assertArrayEquals(arrOfPrimes, new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 });
  }
}

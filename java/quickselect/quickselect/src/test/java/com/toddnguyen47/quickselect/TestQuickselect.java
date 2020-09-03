package com.toddnguyen47.quickselect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestQuickselect {
  private Quickselect quickselect_;
  private int[] sortedElemsNoDuplicate_ = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
  private int[] randomElemsNoDuplicate_ = { 3, 7, 6, 10, 8, 5, 2, 9, 1, 4 };
  private int[] sortedElemesDuplicates_ = { 1, 1, 1, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 8, 8, 9, 10,
      10 };
  private int[] randomElemsDuplicates_ = { 1, 1, 7, 8, 8, 4, 7, 1, 3, 8, 6, 5, 4, 10, 10, 2, 4, 9 };

  @BeforeEach
  public void setUp() {
    this.quickselect_ = new Quickselect();
  }

  @Test
  public void testObjectCreation() {
    assertNotNull(this.quickselect_);
  }

  @Test
  public void testQuickSelectOnArrayOfOneElement_ReturnOne() {
    int input[] = new int[] { 1 };
    int k = 0;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 1;
    assertEquals(expected, result);
  }

  @Test
  public void testQuickSelectOnArrayOfOneElementDifferentElement() {
    int input[] = new int[] { 2 };
    int k = 0;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 2;
    assertEquals(expected, result);
  }

  @Test
  public void testOnArrayOfTwoElements_KIsZero() {
    int input[] = new int[] { 1, 2 };
    int k = 0;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 1;
    assertEquals(expected, result);
  }

  @Test
  public void testOnArrayOfTwoElementsUnsorted_KIsZero() {
    int input[] = new int[] { 2, 1 };
    int k = 0;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 1;
    assertEquals(expected, result);
  }

  @Test
  public void testOnArrayOfTwoElementsUnsorted_KIsOne() {
    int input[] = new int[] { 2, 1 };
    int k = 1;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 2;
    assertEquals(expected, result);
  }

  @Test
  public void testOnArrayOfTwoElementsSorted_KIsOne() {
    int input[] = new int[] { 1, 2 };
    int k = 1;
    int result = this.quickselect_.findKthSmallestElement(input, k);

    int expected = 2;
    assertEquals(expected, result);
  }

  @Test
  public void testArrayNoDuplicate_K0() {
    int k = 0;
    assertRandomArrayNoDuplicates(k);
  }

  @Test
  public void testArrayNoDuplicate_K3() {
    int k = 3;
    assertRandomArrayNoDuplicates(k);
  }

  @Test
  public void testArrayNoDuplicate_K7() {
    int k = 7;
    assertRandomArrayNoDuplicates(k);
  }

  @Test
  public void testArrayNoDuplicate_K10() {
    int k = this.randomElemsNoDuplicate_.length - 1;
    assertRandomArrayNoDuplicates(k);
  }

  @Test
  public void testArrayDuplicates_K0() {
    assertRandomArrayDuplicates(0);
  }

  @Test
  public void testArrayDuplicates_K6() {
    assertRandomArrayDuplicates(6);
  }

  @Test
  public void testArrayDuplicates_K11() {
    assertRandomArrayDuplicates(11);
  }

  @Test
  public void testArrayDuplicates_KEndLength() {
    assertRandomArrayDuplicates(this.randomElemsDuplicates_.length - 1);
  }

  // -------------------------------------------------------------
  // PRIVATE FUNCTIONS
  // -------------------------------------------------------------
  private void assertRandomArrayNoDuplicates(int k) {
    int result = this.quickselect_.findKthSmallestElement(this.randomElemsNoDuplicate_, k);
    int expected = this.sortedElemsNoDuplicate_[k];
    assertEquals(expected, result);
  }

  private void assertRandomArrayDuplicates(int k) {
    int result = this.quickselect_.findKthSmallestElement(this.randomElemsDuplicates_, k);
    int expected = this.sortedElemesDuplicates_[k];
    assertEquals(expected, result);
  }
}

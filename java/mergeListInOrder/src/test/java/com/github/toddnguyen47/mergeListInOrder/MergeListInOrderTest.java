package com.github.toddnguyen47.mergeListInOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MergeListInOrderTest {
  private MergeListInOrder _mergeListInOrder;
  private LinkedList<Integer> _left;
  private LinkedList<Integer> _right;
  private List<List<Integer>> _results;
  private List<List<Integer>> _expectedResults;

  private void _addToExpectedResults(Integer... args) {
    _expectedResults.add(Arrays.asList(args));
  }

  @BeforeEach
  public void setup() {
    this._mergeListInOrder = new MergeListInOrder();
    this._left = new LinkedList<>();
    this._right = new LinkedList<>();
    this._expectedResults = new LinkedList<>();
  }

  @Test
  public void addTwoNullLists_ReturnNull() {
    _results = _mergeListInOrder.merge(null, null);
    assertNull(this._results);
  }

  /**
   * Ref: https://stackoverflow.com/a/43280251
   */
  @Test
  public void addOneNullLeft_ShouldReturnOtherList() {
    _left.add(1);
    _addToExpectedResults(1);
    
    _results = _mergeListInOrder.merge(_left, _right);
    assertThat(_results, is(_expectedResults));
  }

  @Test
  public void addOneNullRight_ShouldReturnOtherList() {
    _right.add(1);
    _addToExpectedResults(1);

    _results = _mergeListInOrder.merge(_left, _right);
    assertThat(_results, is(_expectedResults));
  }

  @Test
  public void oneElemEachSide_ShouldReturnTwoElements() {
    _left.add(1);
    _right.add(10);
    _addToExpectedResults(1, 10);
    _addToExpectedResults(10, 1);

    _results = _mergeListInOrder.merge(_left, _right);

    assertEquals(_expectedResults.size(), _results.size());
    assertThat(_results, is(_expectedResults));
  }

  @Test
  public void addTwoElemOnLeftOneElemOnRight_ShouldReturnThreeElements() {
    _left.add(1);
    _left.add(5);
    _right.add(10);
    _addToExpectedResults(1, 5, 10);
    _addToExpectedResults(1, 10, 5);
    _addToExpectedResults(10, 1, 5);

    _results = _mergeListInOrder.merge(_left, _right);

    assertEquals(_expectedResults.size(), _results.size());
    assertThat(_results, is(_expectedResults));
  }

  @Test
  public void addTwoElemOnRightOneElemOnLeft_ShouldReturnThreeElements() {
    _left.add(5);
    _right.add(10);
    _right.add(15);
    _addToExpectedResults(5, 10, 15);
    _addToExpectedResults(10, 5, 15);
    _addToExpectedResults(10, 15, 5);

    _results = _mergeListInOrder.merge(_left, _right);

    assertEquals(_expectedResults.size(), _results.size());
    assertThat(_results, is(_expectedResults));
  }

  @Test
  public void addTwoElemsLeftThreeElemsRight_ShouldReturn() {
    _left.add(10);
    _left.add(5);

    _right.add(20);
    _right.add(15);
    _right.add(25);

    _addToExpectedResults(10, 5, 20, 15, 25);
    _addToExpectedResults(10, 20, 5, 15, 25);
    _addToExpectedResults(10, 20, 15, 5, 25);
    _addToExpectedResults(10, 20, 15, 25, 5);
    _addToExpectedResults(20, 10, 5, 15, 25);
    _addToExpectedResults(20, 10, 15, 5, 25);
    _addToExpectedResults(20, 10, 15, 25, 5);
    _addToExpectedResults(20, 15, 10, 5, 25);
    _addToExpectedResults(20, 15, 10, 25, 5);
    _addToExpectedResults(20, 15, 25, 10, 5);

    _results = _mergeListInOrder.merge(_left, _right);

    assertEquals(_expectedResults.size(), _results.size());
    assertThat(_results, is(_expectedResults));
  }
}

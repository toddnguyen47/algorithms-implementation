package com.github.toddnguyen47.mergeListInOrder;

import java.util.LinkedList;
import java.util.List;

public class MergeListInOrder {
  public List<List<Integer>> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
    if (left == null && right == null) {
      return null;
    }

    List<List<Integer>> results = new LinkedList<>();
    LinkedList<Integer> result = new LinkedList<>();
    _mergeHelper(left, right, results, result);

    return results;
  }

  private void _mergeHelper(LinkedList<Integer> left, LinkedList<Integer> right, List<List<Integer>> results,
      LinkedList<Integer> currentResult) {
    if (left.isEmpty()) {
      LinkedList<Integer> resultClone = new LinkedList<Integer>(currentResult);
      resultClone.addAll(right);
      results.add(resultClone);
    }
    else if (right.isEmpty()) {
      LinkedList<Integer> resultClone = new LinkedList<Integer>(currentResult);
      resultClone.addAll(left);
      results.add(resultClone);
    }
    else {
      // We still have elements in both lists left
      int leftValue = left.removeFirst();
      currentResult.addLast(leftValue);
      _mergeHelper(left, right, results, currentResult);
      currentResult.removeLast();
      left.addFirst(leftValue);

      int rightValue = right.removeFirst();
      currentResult.addLast(rightValue);
      _mergeHelper(left, right, results, currentResult);
      currentResult.removeLast();
      right.addFirst(rightValue);
    }
  }
}

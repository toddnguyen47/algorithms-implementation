package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.List;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day3Trees implements ISolution {
  private List<String> _lines = new ArrayList<>();

  public void execute(List<String> lines) {
    this._lines = lines;
    this.travelVariousPathsMultiplied();
  }

  private void travelVariousPathsMultiplied() {
    int r1d1 = this.travel(1, 1);
    int r3d1 = this.travel3RightDown1();
    int r5d1 = this.travel(5, 1);
    int r7d1 = this.travel(7, 1);
    int r1d2 = this.travel(1, 2);
    long product1 = r1d1 * r3d1;
    long product2 = r5d1 * r7d1 * r1d2;
    CustomPrint.print("Product1: %d, Product2: %d", product1, product2);
    CustomPrint.print("Multiplied: %d", product1 * product2);
  }

  private int travel3RightDown1() {
    return this.travel(3, 1);
  }

  private int travel(int rightDelta, int downDelta) {
    int treesEncountered = 0;
    int row = 0;
    int col = 0;
    int linesSize = this._lines.size();
    int colSize = this._lines.get(0).length();

    while (row < linesSize) {
      String curRow = this._lines.get(row);
      char curSquare = curRow.charAt(col);
      if (curSquare == '#') {
        treesEncountered += 1;
      }
      row += downDelta;

      // Wrap column
      col += rightDelta;
      col = col % colSize;
    }

    CustomPrint.print("Right: %d, Down: %d, Trees Encountered: %d", rightDelta, downDelta,
        treesEncountered);
    return treesEncountered;
  }
}

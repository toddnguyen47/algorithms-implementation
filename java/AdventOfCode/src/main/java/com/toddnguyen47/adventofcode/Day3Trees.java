package com.toddnguyen47.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day3Trees implements ISolution {
  private List<String> _lines = new ArrayList<>();

  public void execute(List<String> lines) {
    this._lines = lines;
    this.travel3RightDown1();
  }

  private void travel3RightDown1() {
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
      row += 1;

      // Wrap column
      col += 3;
      col = col % colSize;
    }

    CustomPrint.print("Trees Encountered: %d", treesEncountered);
  }
}

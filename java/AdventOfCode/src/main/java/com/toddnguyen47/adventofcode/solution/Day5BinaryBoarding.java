package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day5BinaryBoarding implements ISolution {
  private List<String> _lines = new ArrayList<>();
  private int _startIndexRow = 0;
  private int _startIndexCol = 7;

  @Override
  public void execute(List<String> lines) {
    this._lines = lines;
    this.findMissingId();
  }

  @SuppressWarnings("unused")
  private int findMissingId() {
    Set<Integer> setOfIds = new HashSet<>();
    int maxId = 0;

    for (String line : this._lines) {
      IdWrapper idWrapper = this.parseId(line);
      maxId = Math.max(maxId, idWrapper.id);
      setOfIds.add(idWrapper.id);
    }

    int boardingPassId = 0;
    for (int id = 0; id < maxId; id++) {
      if (!setOfIds.contains(id)) {
        int idBefore = id - 1;
        int idAfter = id + 1;
        if (setOfIds.contains(idBefore) && setOfIds.contains(idAfter)) {
          boardingPassId = id;
        }
      }
    }

    CustomPrint.print("Boarding Pass ID: %d", boardingPassId);
    return boardingPassId;
  }

  @SuppressWarnings("unused")
  private int findHighestId() {
    int highestId = 0;

    for (String line : this._lines) {
      IdWrapper idWrapper = this.parseId(line);
      highestId = Math.max(highestId, idWrapper.id);
    }

    CustomPrint.print("Highest ID: %d", highestId);
    return highestId;
  }

  private IdWrapper parseId(String line) {
    int row = this.findRow(line);
    int col = this.findCol(line);
    return new IdWrapper(row, col);
  }

  private int findRow(String line) {
    return this.parseBinaryData(line, this._startIndexRow, this._startIndexRow + 6, 'F', 'B', 0,
        127);
  }

  private int findCol(String line) {
    return this.parseBinaryData(line, this._startIndexCol, this._startIndexCol + 2, 'L', 'R', 0, 7);
  }

  private int parseBinaryData(String line, int startIndex, int endIndex, char lowerChar,
      char higherChar, int minRange, int maxRange) {
    for (int i = startIndex; i < endIndex; i++) {
      char c1 = line.charAt(i);
      int halfway = (minRange + maxRange) >> 1;
      // Lower half
      if (c1 == lowerChar) {
        maxRange = halfway;
      }
      // Higher half
      else if (c1 == higherChar) {
        minRange = halfway + 1;
      }
      // Should never happen!
      else {
        System.out.println(String.format("Character is not '%s' or '%s'!", lowerChar, higherChar));
        // Should throw error but I'm lazy to declare a try catch statement later
        System.exit(1);
      }
    }

    // Last character will decide if we take the min or max
    char lastChar = line.charAt(endIndex);
    if (lastChar == lowerChar) {
      return minRange;
    } else if (lastChar == higherChar) {
      return maxRange;
    } else {
      System.out.println(String.format("Character is not '%s' or '%s'!", lowerChar, higherChar));
      return -1;
    }
  }

  private class IdWrapper {
    public int row = 0;
    public int col = 0;
    public int id = 0;

    public IdWrapper(int row, int col) {
      this.row = row;
      this.col = col;
      this.id = this.row * 8 + this.col;
    }

    @Override
    public String toString() {
      return String.format("Row: %d, Col: %d, ID: %d", this.row, this.col, this.id);
    }
  }
}

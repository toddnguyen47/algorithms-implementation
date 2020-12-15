package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.List;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day2PasswordPolicy implements ISolution {
  private List<String> _lines = new ArrayList<>();

  public void execute(List<String> lines) {
    this._lines = lines;
    this.passwordPosition();
  }

  private void passwordPosition() {
    int count = 0;
    for (String line : this._lines) {
      String[] split = line.split(":");
      String[] beforeColon = split[0].split(" ");
      String[] times = beforeColon[0].trim().split("-");
      int pos1 = Integer.valueOf(times[0]);
      int pos2 = Integer.valueOf(times[1]);
      char letter = beforeColon[1].trim().charAt(0);
      String password = split[1].trim();

      boolean valid1 = password.charAt(pos1 - 1) == letter;
      boolean valid2 = password.charAt(pos2 - 1) == letter;

      // Check if only ONE of the booleans is valid
      if ((valid1 && !valid2) || (!valid1 && valid2)) {
        count += 1;
      }
    }
    CustomPrint.print("Count: %d", count);
  }

  private void bruteForceCharRange() {
    int count = 0;
    for (String line : this._lines) {
      String[] split = line.split(":");
      String[] beforeColon = split[0].split(" ");
      String[] times = beforeColon[0].trim().split("-");
      int min1 = Integer.valueOf(times[0]);
      int max1 = Integer.valueOf(times[1]);
      String letter = beforeColon[1].trim();
      String password = split[1].trim();

      int charCount = 0;
      for (char c1 : password.toCharArray()) {
        String sc1 = String.valueOf(c1);
        if (sc1.equals(letter)) {
          charCount += 1;
        }
      }

      if (min1 <= charCount && charCount <= max1) {
        count += 1;
      }
    }

    CustomPrint.print("Count: %d", count);
  }
}

package com.toddnguyen47.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicy {
  private List<String> _lines = new ArrayList<>();

  public void execute(List<String> lines) {
    this._lines = lines;
    this.bruteForce();
  }

  private void bruteForce() {
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

package com.toddnguyen47.adventofcode;

public class CustomPrint {
  public static void print(String formatString, Object... args) {
    System.out.println(String.format(formatString, args));
  }
}

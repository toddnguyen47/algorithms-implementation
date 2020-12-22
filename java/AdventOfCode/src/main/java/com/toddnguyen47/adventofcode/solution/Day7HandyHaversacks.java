package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day7HandyHaversacks implements ISolution {
  private List<String> _lines = new ArrayList<>();
  private Pattern _bagsPattern = Pattern.compile("([ \\t]*\\d+)(\\D+)bag[s]*[\\S ]*");
  private String _noOtherBagsStr = "no other";

  @Override
  public void execute(List<String> lines) {
    this._lines = lines;
    this.bruteForceHashMap();
  }

  private void bruteForceHashMap() {
    Map<String, List<Dependency>> hmap = this.bruteForceParse();

    String keyToFind = "shiny gold";
    int count = 0;
    for (List<Dependency> value : hmap.values()) {
      for (Dependency dep : value) {
        if (dep.bagColor.contentEquals(keyToFind)) {
          count += 1;
        }
      }
    }
    CustomPrint.print("Found: %d", count);
  }

  private Map<String, List<Dependency>> bruteForceParse() {
    Map<String, List<Dependency>> hmap = new HashMap<>();
    for (String line : this._lines) {
      String splitArr[] = line.split("contain");
      String key = splitArr[0].trim();
      key = key.replace("bags", "").trim();
      List<Dependency> value = this.parseDependencyString(splitArr[1]);
      hmap.put(key, value);
    }

    return hmap;
  }

  private List<Dependency> parseDependencyString(String dependencyStr) {
    dependencyStr = dependencyStr.trim();
    List<Dependency> dependencies = new ArrayList<>();

    String dependenciesArr[] = dependencyStr.split(",");
    for (String depStr2 : dependenciesArr) {
      Matcher m = this._bagsPattern.matcher(depStr2);
      Dependency dependency = new Dependency(this._noOtherBagsStr, 0);
      // If m matches, there are required bags!
      if (m.matches()) {
        int numBagsRequired = Integer.valueOf(m.group(1).trim());
        String bagColor = m.group(2).trim();
        dependency = new Dependency(bagColor, numBagsRequired);
      }
      // If m does not match, no other bags are required!
      else {
        // Double checking
        if (depStr2.contentEquals(this._noOtherBagsStr)) {
          dependency = new Dependency(this._noOtherBagsStr, 0);
        }
      }
      dependencies.add(dependency);
    }

    return dependencies;
  }

  private class Dependency {
    public String bagColor = "";
    public int numBagsRequired = 0;

    public Dependency(String bagColor, int numBagsRequired) {
      this.bagColor = bagColor;
      this.numBagsRequired = numBagsRequired;
    }

    @Override
    public String toString() {
      return String.format("Bag Color: %s, Number of Bags Required: %d", this.bagColor,
          this.numBagsRequired);
    }
  }
}

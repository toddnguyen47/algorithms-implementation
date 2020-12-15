package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day4Passport implements ISolution {
  private List<Passport> _listOfPassports = new ArrayList<>();
  private List<String> _lines = new ArrayList<>();

  @Override
  public void execute(List<String> lines) {
    this._lines = lines;
    this.parseLinesForPassports();
    this.parsePassports();
  }

  private void parseLinesForPassports() {
    List<String> passportLines = new ArrayList<>();
    Iterator<String> iter = this._lines.iterator();
    while (iter.hasNext()) {
      String line = iter.next();
      boolean isEmptyLine = line.trim().length() == 0;
      if (!isEmptyLine) {
        passportLines.add(line);
      }

      if (isEmptyLine || !iter.hasNext()) {
        if (passportLines.size() != 0) {
          Passport passport = new Passport(passportLines);
          this._listOfPassports.add(passport);
          passportLines = new ArrayList<>();
        }
      }
    }
  }

  private void parsePassports() {
    int count = 0;
    Iterator<Passport> iter = this._listOfPassports.iterator();
    while (iter.hasNext()) {
      Passport passport = iter.next();
      if (passport.isPassportValid()) {
        count += 1;
      }
    }
    CustomPrint.print("Valid Passwords: '%d'", count);
  }

  private class Passport {
    public Map<String, String> infoMap = new HashMap<>();
    public final String BYR = "byr";
    public final String IYR = "iyr";
    public final String EYR = "eyr";
    public final String HGT = "hgt";
    public final String HCL = "hcl";
    public final String ECL = "ecl";
    public final String PID = "pid";
    public final String CID = "cid";

    public Passport(List<String> passportLines) {
      for (String s1 : passportLines) {
        String[] tokens = s1.split("\\s");
        for (String token : tokens) {
          String[] splitTemp = token.split(":");
          this.infoMap.put(splitTemp[0], splitTemp[1]);
        }
      }
    }

    @Override
    public String toString() {
      return String.format(
          "byr: '%s', iyr: '%s', eyr: '%s', hgt: '%s'\n"
              + "hcl: '%s', ecl: '%s', passportId: '%s', countryId: '%s'",
          this.infoMap.getOrDefault(this.BYR, ""), this.infoMap.getOrDefault(this.IYR, ""),
          this.infoMap.getOrDefault(this.EYR, ""), this.infoMap.getOrDefault(this.HGT, ""),
          this.infoMap.getOrDefault(this.HCL, ""), this.infoMap.getOrDefault(this.ECL, ""),
          this.infoMap.getOrDefault(this.PID, ""), this.infoMap.getOrDefault(this.CID, ""));
    }

    public boolean isPassportValid() {
      return this.infoMap.containsKey(this.BYR) && this.infoMap.containsKey(this.IYR)
          && this.infoMap.containsKey(this.EYR) && this.infoMap.containsKey(this.HGT)
          && this.infoMap.containsKey(this.HCL) && this.infoMap.containsKey(this.ECL)
          && this.infoMap.containsKey(this.PID);
    }
  }
}

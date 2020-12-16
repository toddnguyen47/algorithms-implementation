package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println(passport);
        System.out.println("---");
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

    private Pattern digitPattern = Pattern.compile("\\d+");
    private Pattern nondigitPattern = Pattern.compile("\\D+");
    private Pattern colorPattern = Pattern.compile("#[0-9a-f]{6}");
    private Pattern pidPattern = Pattern.compile("\\d{9}");

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
      return String.format("%s:%s %s:%s %s:%s %s:%s\n" + "%s:%s %s:%s %s:%s %s:%s", this.BYR,
          this.infoMap.getOrDefault(this.BYR, ""), this.IYR,
          this.infoMap.getOrDefault(this.IYR, ""), this.EYR,
          this.infoMap.getOrDefault(this.EYR, ""), this.HGT,
          this.infoMap.getOrDefault(this.HGT, ""), this.HCL,
          this.infoMap.getOrDefault(this.HCL, ""), this.ECL,
          this.infoMap.getOrDefault(this.ECL, ""), this.PID,
          this.infoMap.getOrDefault(this.PID, ""), this.CID,
          this.infoMap.getOrDefault(this.CID, ""));
    }

    public boolean isPassportValid() {
      return this.isBirthYearValid() && this.isIssueYearValid() && this.isExpirationYearValid()
          && this.isHeightValid() && this.isHairColorValid() && this.isEyeColorValid()
          && this.isPid();
    }

    private boolean isBirthYearValid() {
      if (!this.infoMap.containsKey(this.BYR))
        return false;

      int birthYear = Integer.valueOf(this.infoMap.get(this.BYR));
      return 1920 <= birthYear && birthYear <= 2002;
    }

    private boolean isIssueYearValid() {
      if (!this.infoMap.containsKey(this.IYR))
        return false;

      int issueYear = Integer.valueOf(this.infoMap.get(this.IYR));
      return 2010 <= issueYear && issueYear <= 2020;
    }

    private boolean isExpirationYearValid() {
      if (!this.infoMap.containsKey(this.EYR))
        return false;

      int expirationYear = Integer.valueOf(this.infoMap.get(this.EYR));
      return 2020 <= expirationYear && expirationYear <= 2030;
    }

    private boolean isHeightValid() {
      if (!this.infoMap.containsKey(this.HGT))
        return false;

      String height = this.infoMap.get(this.HGT);
      Matcher digitMatcher = this.digitPattern.matcher(height);
      if (!digitMatcher.find())
        return false;
      int heightInt = Integer.valueOf(digitMatcher.group(0));

      Matcher heightUnitMatcher = this.nondigitPattern.matcher(height);
      if (!heightUnitMatcher.find())
        return false;
      String unit = heightUnitMatcher.group(0);

      if (unit.equals("cm")) {
        return 150 <= heightInt && heightInt <= 193;
      } else if (unit.equals("in")) {
        return 59 <= heightInt && heightInt <= 76;
      } else {
        return false;
      }
    }

    private boolean isHairColorValid() {
      if (!this.infoMap.containsKey(this.HCL))
        return false;

      String hairColor = this.infoMap.get(this.HCL);
      Matcher m = this.colorPattern.matcher(hairColor);
      return m.find();
    }

    private boolean isEyeColorValid() {
      if (!this.infoMap.containsKey(this.ECL))
        return false;

      String eyeColor = this.infoMap.get(this.ECL);
      return eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn")
          || eyeColor.equals("gry") || eyeColor.equals("grn") || eyeColor.equals("hzl")
          || eyeColor.equals("oth");
    }

    private boolean isPid() {
      if (!this.infoMap.containsKey(this.PID))
        return false;

      String pid = this.infoMap.get(this.PID);
      Matcher m = this.pidPattern.matcher(pid);
      return m.find();
    }
  }
}

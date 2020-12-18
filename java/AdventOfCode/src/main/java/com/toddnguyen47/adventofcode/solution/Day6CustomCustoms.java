package com.toddnguyen47.adventofcode.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.toddnguyen47.adventofcode.CustomPrint;

public class Day6CustomCustoms implements ISolution {
  private List<String> _lines = new ArrayList<>();
  private List<Answer> _listOfAnswers = new ArrayList<>();

  @Override
  public void execute(List<String> lines) {
    this._lines = lines;
    this._listOfAnswers = new ArrayList<>();
    this.separateIntoAnswers();
    this.getYesAnswers();
  }

  private int getYesAnswers() {
    int totalCount = 0;
    for (Answer answer : this._listOfAnswers) {
      Set<Character> uniqueYesAnswers = new HashSet<>();
      for (String line : answer.listOfStrings) {
        for (Character c1 : line.toCharArray()) {
          uniqueYesAnswers.add(c1);
        }
      }
      totalCount += uniqueYesAnswers.size();
    }

    CustomPrint.print("Total Count: %d", totalCount);
    return totalCount;
  }

  private void separateIntoAnswers() {
    List<String> answers = new ArrayList<>();
    Iterator<String> iter = this._lines.iterator();
    while (iter.hasNext()) {
      String line = iter.next().trim();
      if (!line.contentEquals("")) {
        answers.add(line);
      }
      if (line.contentEquals("") || !iter.hasNext()) {
        if (!answers.isEmpty()) {
          this._listOfAnswers.add(new Answer(answers));
          answers = new ArrayList<>();
        }
      }
    }
  }

  private class Answer {
    public List<String> listOfStrings = new ArrayList<>();

    public Answer(List<String> listOfStrings) {
      this.listOfStrings = listOfStrings;
    }

    @Override
    public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("Answers:\n");
      for (String line : this.listOfStrings) {
        sb.append(line + "\n");
      }
      return sb.toString();
    }
  }
}

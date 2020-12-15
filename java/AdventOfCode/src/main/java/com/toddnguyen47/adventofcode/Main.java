package com.toddnguyen47.adventofcode;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.execute();
  }

  private String _fileName;
  private List<String> _lines = new ArrayList<>();

  public Main() {
    this._fileName = "input.txt";
  }

  public void execute() {
    this.readFile();
    this.execAlgorithm();
  }

  // ##########################################################################
  // PRIVATE FUNCTIONS
  // ##########################################################################
  private void readFile() {
    Path path = FileSystems.getDefault().getPath(this._fileName);
    this._lines = new ArrayList<>();
    try {
      this._lines = Files.readAllLines(path);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void execAlgorithm() {
    PasswordPolicy pwPolicy = new PasswordPolicy();
    pwPolicy.execute(this._lines);
  }
}

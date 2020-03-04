package edu.cnm.deepdive.officehours;

import java.util.Locale.Category;

public enum Subject {
  ANDROID_PROJECT("A"),
  CAPSTONE_PROJECT("C"),
  TUTORING("T"),
  GRADES("G"),
  OTHER("O");

  private String code;

  private Subject(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}

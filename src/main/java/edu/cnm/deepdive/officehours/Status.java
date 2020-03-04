package edu.cnm.deepdive.officehours;

public enum Status {
  NO_SHOW("N"),
  PENDING("P"),
  LATE("L"),
  CANCELLED("X"),
  ARRIVED("K");

  private String code;

  private Status(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}

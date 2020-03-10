package edu.cnm.deepdive.officehours.controller.rest.exception;

public class SearchTermTooShortException extends IllegalArgumentException {

  public SearchTermTooShortException() {
    super();
  }

  public SearchTermTooShortException(String s) {
    super(s);
  }

  public SearchTermTooShortException(String message, Throwable cause) {
    super(message, cause);
  }

  public SearchTermTooShortException(Throwable cause) {
    super(cause);
  }

}

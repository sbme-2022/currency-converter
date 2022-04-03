package com.sbme;

public class WrongAmountExceptionException extends RuntimeException {

  private static final long serialVersionUID = 9022930530036718243L;

  public WrongAmountExceptionException(String message) {
    super(message);
  }

}

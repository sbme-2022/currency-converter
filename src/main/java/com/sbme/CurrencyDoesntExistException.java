package com.sbme;

public class CurrencyDoesntExistException extends RuntimeException {

  private static final long serialVersionUID = 7454480460711227018L;

  public CurrencyDoesntExistException(String currency) {
    super("The given currency " + currency + " does not exist");
  }
}

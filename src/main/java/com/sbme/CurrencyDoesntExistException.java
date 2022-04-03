package com.sbme;

public class CurrencyDoesntExistException extends RuntimeException {

  public CurrencyDoesntExistException(String currency) {
    super("The given currency " + currency + " does not exist");
  }
}

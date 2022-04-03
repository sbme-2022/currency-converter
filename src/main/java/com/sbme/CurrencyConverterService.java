package com.sbme;

import java.math.BigDecimal;

public interface CurrencyConverterService {

  /**
   *
   * @param amount the amount to convert
   * @param fromCurrency the currency to convert from
   * @param toCurrency the currency to convert to
   * @return the value conversion
   * @throws CurrencyDoesntExistException if any of the given currencies does not exist
   * @throws WrongAmountExceptionException if the amount given is zero or less
   */
  BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency)
      throws CurrencyDoesntExistException, WrongAmountExceptionException;
}

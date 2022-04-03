package com.sbme;

import java.math.BigDecimal;

public class CurrencyConverterServiceImp implements CurrencyConverterService {

  private static final int ONE = 1;
  private final transient CurrencyConverterDao converterDao;

  public CurrencyConverterServiceImp(CurrencyConverterDao currencyConverterDao) {
    this.converterDao = currencyConverterDao;
  }

  @Override
  public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency)
      throws CurrencyDoesntExistException, WrongAmountExceptionException {
    if (amount.compareTo(BigDecimal.ZERO) < ONE) {
      throw new WrongAmountExceptionException("Amount must be greater than zero");
    }
    BigDecimal fromCurrencyValue = converterDao.getUsdToCurrencyRate(fromCurrency);
    BigDecimal toCurrencyValue = converterDao.getUsdToCurrencyRate(toCurrency);
    return amount.divide(fromCurrencyValue).multiply(toCurrencyValue);
  }
}

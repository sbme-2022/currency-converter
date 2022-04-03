package com.sbme;

import java.math.BigDecimal;

public class CurrencyConverterServiceImp implements CurrencyConverterService {

  private final CurrencyConverterDao converterDao;

  public CurrencyConverterServiceImp(CurrencyConverterDao currencyConverterDao) {
    this.converterDao = currencyConverterDao;
  }

  @Override
  public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency)
      throws CurrencyDoesntExistException, WrongAmountExceptionException {
    if (amount.compareTo(BigDecimal.ZERO) < 1) {
      throw new WrongAmountExceptionException("Amount must be greater than zero");
    }
    BigDecimal fromCurrencyValue = converterDao.getUsdToCurrencyRate(fromCurrency);
    BigDecimal toCurrencyValue = converterDao.getUsdToCurrencyRate(toCurrency);
    return amount.divide(fromCurrencyValue).multiply(toCurrencyValue);
  }
}

package com.sbme;

import java.math.BigDecimal;

public interface CurrencyConverterDao {
  BigDecimal getUsdToCurrencyRate(String currency) throws CurrencyDoesntExistException;
}

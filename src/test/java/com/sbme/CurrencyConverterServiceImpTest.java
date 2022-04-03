package com.sbme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class CurrencyConverterServiceImpTest {

  public static final String CUR_1 = "CUR1";
  public static final String CUR_2 = "CUR2";
  public static final String NOT_EXIST_CUR = "CUR_X";
  private CurrencyConverterService testedInstance;
  private CurrencyConverterDao mockedDao = mock(CurrencyConverterDao.class);

  @BeforeAll
  void setup() {
    when(mockedDao.getUsdToCurrencyRate(CUR_1)).thenReturn(BigDecimal.valueOf(10));
    when(mockedDao.getUsdToCurrencyRate(CUR_2)).thenReturn(BigDecimal.valueOf(5));
    when(mockedDao.getUsdToCurrencyRate(NOT_EXIST_CUR)).thenThrow(new CurrencyDoesntExistException(NOT_EXIST_CUR));
    testedInstance = new CurrencyConverterServiceImp(mockedDao);
  }

  @Test
  void convert() {
    assertThat("HeLLo").isEqualToIgnoringCase("hellO");
    assertThat(testedInstance.convert(BigDecimal.valueOf(7), CUR_1, CUR_2))
        .isEqualTo(BigDecimal.valueOf(3.5));
    assertThat(testedInstance.convert(BigDecimal.valueOf(18), CUR_2, CUR_1))
        .isEqualTo(BigDecimal.valueOf(36.0));
    verify(mockedDao, times(2)).getUsdToCurrencyRate(CUR_1);
    verify(mockedDao, times(2)).getUsdToCurrencyRate(CUR_2);
  }

  @Test
  void convertMustThrowCurrencyDoesNotExistException() {
    assertThatThrownBy(() -> testedInstance.convert(BigDecimal.valueOf(7), CUR_1, NOT_EXIST_CUR))
        .hasMessage("The given currency " + NOT_EXIST_CUR + " does not exist");
    verify(mockedDao, times(1)).getUsdToCurrencyRate(CUR_1);
    verify(mockedDao, times(0)).getUsdToCurrencyRate(CUR_2);
  }

  @Test
  void convertShouldNotAcceptZeroAmount() {
    assertThatThrownBy(() -> testedInstance.convert(BigDecimal.valueOf(-1), CUR_1, CUR_2))
        .hasMessage("Amount must be greater than zero");
    verify(mockedDao, times(0)).getUsdToCurrencyRate(CUR_1);
    verify(mockedDao, times(0)).getUsdToCurrencyRate(CUR_2);
  }
}

package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.airatgaliev.clinic.exceptions.DateTimeFormatException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeConverterTest {

  private final LocalDate today = LocalDate.now();
  private final int year = today.getYear();
  private final int month = today.getMonthValue();
  private final int dayOfMonth = today.getDayOfMonth();

  @Test
  public void convertStringToLocalDateTimeCorrectly() {
    LocalDateTime localDateTime = DateTimeConverter
        .convertStringToDateTime("1/11/2019 1:00 pm", LocalDate.of(2019, 1, 11));
    assertEquals(localDateTime, LocalDateTime.of(2019, 1, 11, 13, 0));
  }

  @Test
  public void convertStringTodayToLocalDateTimeCorrectly() {
    LocalDateTime localDateTime = DateTimeConverter
        .convertStringToDateTime("today 1:00 pm", LocalDate.of(year, month, dayOfMonth));
    assertEquals(localDateTime, LocalDateTime.of(year, month, dayOfMonth, 13, 0));
  }

  @Test
  public void incorrectConvertStringToLocalDateTimeCorrectly() {
    Throwable error = assertThrows(DateTimeFormatException.class, () -> DateTimeConverter
        .convertStringToDateTime("1/11/2019 100 pm", LocalDate.of(2019, 1, 11)));
    assertEquals("Unable to create date time from: [1/11/2019 100 pm], please enter format "
            + "[M/d/yyyy h:mm a], Text '1/11/2019 100 PM' could not be parsed at index 13",
        error.getMessage());
  }

  @Test
  public void incorrectConvertStringTodayToLocalDateTimeCorrectly() {
    Throwable error = assertThrows(DateTimeFormatException.class, () -> DateTimeConverter
        .convertStringToDateTime("toda 1:00 pm", LocalDate.of(year, month, dayOfMonth)));
    assertEquals("Unable to create date time from: [toda 1:00 pm], please enter format "
            + "[M/d/yyyy h:mm a], Text 'TODA 1:00 PM' could not be parsed at index 0",
        error.getMessage());
  }
}
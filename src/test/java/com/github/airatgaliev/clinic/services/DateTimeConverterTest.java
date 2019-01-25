package com.github.airatgaliev.clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.airatgaliev.clinic.exceptions.DateTimeFormatException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("DateTimeConverter should")
class DateTimeConverterTest {

  private final LocalDate today = LocalDate.now();
  private final int year = today.getYear();
  private final int month = today.getMonthValue();
  private final int dayOfMonth = today.getDayOfMonth();

  @Nested
  @DisplayName("convert string to LocalDateTime")
  class StringToLocalDateTimeTests {

    @Test
    @DisplayName("correctly")
    public void convertStringToLocalDateTimeCorrectly() {
      LocalDateTime localDateTime = DateTimeConverter
          .convertStringToDateTime("1/11/2019 1:00 pm", LocalDate.of(2019, 1, 11));
      assertEquals(localDateTime, LocalDateTime.of(2019, 1, 11, 13, 0));
    }

    @Test
    @DisplayName("incorrectly")
    public void convertStringToLocalDateTimeIncorrectly() {
      Throwable error = assertThrows(DateTimeFormatException.class, () -> DateTimeConverter
          .convertStringToDateTime("1/11/2019 100 pm", LocalDate.of(2019, 1, 11)));
      assertEquals("Unable to create date time from: [1/11/2019 100 pm], please enter format "
              + "[M/d/yyyy h:mm a], Text '1/11/2019 100 PM' could not be parsed at index 13",
          error.getMessage());
    }
  }

  @Nested
  @DisplayName("convert string 'today' to localDateTime")
  class TodayStringToLocalDateTimeTests {

    @Test
    @DisplayName("correctly")
    public void convertStringTodayToLocalDateTimeCorrectly() {
      LocalDateTime today = DateTimeConverter
          .convertStringToDateTime("today 1:00 pm", LocalDate.of(year, month, dayOfMonth));
      assertEquals(today, LocalDateTime.of(year, month, dayOfMonth, 13, 0));
    }

    @Test
    @DisplayName("incorrectly")
    public void convertStringTodayToLocalDateTimeIncorrectly() {
      Throwable error = assertThrows(DateTimeFormatException.class, () -> DateTimeConverter
          .convertStringToDateTime("toda 1:00 pm", LocalDate.of(year, month, dayOfMonth)));
      assertEquals("Unable to create date time from: [toda 1:00 pm], please enter format "
              + "[M/d/yyyy h:mm a], Text 'TODA 1:00 PM' could not be parsed at index 0",
          error.getMessage());
    }
  }
}
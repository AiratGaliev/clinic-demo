package com.github.airatgaliev.clinic.services;

import com.github.airatgaliev.clinic.exceptions.DateTimeFormatException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {

  public static LocalDateTime convertStringToDateTime(String localDate, LocalDate today) {
    LocalDateTime localDateTime;
    try {
      if (localDate.toLowerCase().startsWith("today")) {
        String[] parts = localDate.split(" ", 2);
        LocalTime time = LocalTime
            .parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
        localDateTime = LocalDateTime.of(today, time);
      } else {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("M/d/yyyy h:mm a", Locale.US);
        localDateTime = LocalDateTime.parse(localDate.toUpperCase(), dateTimeFormatter);
      }
    } catch (Exception e) {
      throw new DateTimeFormatException(
          "Unable to create date time from: [" + localDate
              + "], please enter format [M/d/yyyy h:mm a], " + e.getMessage());
    }
    return localDateTime;
  }
}
